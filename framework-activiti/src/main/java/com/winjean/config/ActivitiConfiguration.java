package com.winjean.config;

import com.winjean.form.UsersFormType;
import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;

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

        spec.setActivityFontName("宋体").setLabelFontName("宋体").setAnnotationFontName("宋体");

        spec.setCustomFormTypes(Arrays.asList(new UsersFormType()));

        //记录的历史的详细级别
//        spec.setHistoryLevel(HistoryLevel.FULL);

//        自动部署流程
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
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

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

//    @Bean
//    public BeanPostProcessor activitiConfigurer() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof SpringProcessEngineConfiguration) {
//                    List<AbstractFormType> customFormTypes = Arrays.asList(new UsersFormType());
//                    ((SpringProcessEngineConfiguration)bean).setCustomFormTypes(customFormTypes);
//                }
//                return bean;
//            }
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof SpringProcessEngineConfiguration) {
//                    List<AbstractFormType> customFormTypes = Arrays.asList(new UsersFormType());
//                    ((SpringProcessEngineConfiguration)bean).setCustomFormTypes(customFormTypes);
//                }
//                return bean;
//            }
//        };
//    }
}
