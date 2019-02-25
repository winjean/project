package com.winjean.config;

import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(){
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(platformTransactionManager);
        spec.setDatabaseSchemaUpdate("true");

        spec.setActivityFontName("宋体");
        spec.setLabelFontName("宋体");
        spec.setAnnotationFontName("宋体");


        //记录的历史的详细级别
//        spec.setHistoryLevel(HistoryLevel.FULL);

//        try{
//            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:processes/*.bpmn");
//            spec.setDeploymentResources(resources);
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        return spec;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine(){
        ProcessEngines.getDefaultProcessEngine();

        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return processEngineFactoryBean;
    }

    @Bean
    public RepositoryService repositoryService() throws Exception{
        return processEngine().getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception{
        return processEngine().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception{
        return processEngine().getObject().getTaskService();
    }

    @Bean
    public HistoryService historyService() throws Exception{
        return processEngine().getObject().getHistoryService();
    }

    @Bean
    public IdentityService identityService() throws Exception{
        return processEngine().getObject().getIdentityService();
    }

    @Bean
    public FormService formService() throws Exception{
        return processEngine().getObject().getFormService();
    }
}
