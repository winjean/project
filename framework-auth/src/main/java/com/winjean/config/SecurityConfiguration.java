package com.winjean.config;

import com.winjean.filter.JwtAuthenticationFilter;
import com.winjean.filter.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 * 
 * @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER) 在springboot1.5.8的时候该注解是可以用的
 *                                                  具体看源码
 *
 */
@Configuration
//@Order(55)
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	private UserDetailsService userServiceimpl;

	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;


	// 该方法是登录的时候会进入
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userServiceimpl).passwordEncoder(bCryptPasswordEncoder);
    }

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
						"swagger-ui.html",
						"**/swagger-ui.html",
						"/favicon.ico",
						"/**/*.css",
						"/**/*.js",
						"/**/*.png",
						"/**/*.gif",
						"/swagger-resources/**",
						"/v2/**",
						"/**/*.ttf"
				);
//
//		web.ignoring().antMatchers("/v2/api-docs",
//				"/swagger-resources/configuration/ui",
//				"/swagger-resources",
//				"/swagger-resources/configuration/security",
//				"/swagger-ui.html");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        JwtLoginFilter filter = new JwtLoginFilter(authenticationManager());
        filter.setFilterProcessesUrl("/user/login");

		http
				//权限不足处理类
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler)

				//认证失败处理类
				.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)

				.and().cors()
				// 由于使用的是JWT，我们这里不需要csrf
				.and().csrf().disable()

				.authorizeRequests()
				.antMatchers("/user/login").permitAll()
				.anyRequest().authenticated()
//				.and().formLogin().loginPage("/user/login").permitAll()

				.and().addFilter(filter)
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))

				// 基于token，所以不需要session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// 禁用缓存
				.and().headers().cacheControl()


		;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
