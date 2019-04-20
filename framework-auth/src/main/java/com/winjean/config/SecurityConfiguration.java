package com.winjean.config;

import com.winjean.filter.JwtAuthenticationFilter;
import com.winjean.filter.JwtLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
@Order(55)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	private UserDetailsService userServiceimpl;

	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	// 该方法是登录的时候会进入
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.userDetailsService(userServiceimpl).passwordEncoder(bCryptPasswordEncoder);
//    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/user/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JwtLoginFilter(authenticationManager()))
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
