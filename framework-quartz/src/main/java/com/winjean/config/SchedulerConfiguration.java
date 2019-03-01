package com.winjean.config;

import com.winjean.job.CustomJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/26 11:38
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Configuration
public class SchedulerConfiguration {

    @Autowired
    private CustomJobFactory jobFactory;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        //获取配置属性
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        Properties pro = propertiesFactoryBean.getObject();
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        factory.setQuartzProperties(pro);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
