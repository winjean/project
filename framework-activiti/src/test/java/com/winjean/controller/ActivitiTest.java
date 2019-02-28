package com.winjean.controller;

import com.winjean.ActivitiApplication;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/20 11:33
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@SpringBootTest(classes = ActivitiApplication.class,  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActivitiTest implements JavaDelegate {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private FormService formService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private SpringProcessEngineConfiguration processEngineConfiguration;

//    @Autowired
//    private timeout

    @Test
    @Ignore
    public void deploymentWithClasspathResourceTest(){
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .name("外部表单")//声明流程的名称
                .addClasspathResource("activiti/external-form.bpmn")//加载资源文件，一次只能加载一个文件
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    @Test
    @Ignore
    public void deploymentWithInputStreamTest() throws Exception{
        //获取资源相对路径
        String bpmnPath = "activiti/process-external-form.bpmn";
        String pngPath = "activiti/process-external-form.png";

        //读取资源作为一个输入流
        FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
        FileInputStream pngfileInputStream = new FileInputStream(pngPath);

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addInputStream("helloworld.bpmn",bpmnfileInputStream)
                .addInputStream("helloworld.png", pngfileInputStream)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    @Test
    @Ignore
    public void deploymentWithStringTest() throws Exception{
        //字符串
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions>...</definitions>";

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addString("process-external-form.bpmn",text)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    @Test
    public void deploymentWithZipInputStreamTest() throws Exception{
        //从classpath路径下读取资源文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("activiti/activiti.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addZipInputStream(zipInputStream)//使用zip方式部署，将*.bpmn和*.png压缩成zip格式的文件
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    @Test
    public void startProcessByRuntimeServiceTest() {
        String processDefinitionKey = "process-external-form";
        identityService.setAuthenticatedUserId("winjean");
        Map<String, Object> vars = new HashMap<>(4);
        vars.put("name", "win");
        vars.put("age", "11");
        vars.put("applyUser", "winjean");
        ProcessDefinition pd =repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult();
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(),vars);
        System.out.println(pi.getId() +" "+ pi.getName());
    }

    @Test
    public void startProcessByFormServiceTest() {
        String processDefinitionKey = "process-external-form";
        identityService.setAuthenticatedUserId("winjean");
        Map<String, String> vars = new HashMap<>(4);
        vars.put("name", "win");
        vars.put("age", "11");
        vars.put("applyUser", "winjean");
        ProcessDefinition pd =repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult();
        ProcessInstance pi = formService.submitStartFormData(pd.getId(),vars);
        System.out.println(pi.getId() +" "+ pi.getName());
    }

    @Test
    public void completeTaskByTaskServiceTest() {
        String processInstanceId = "2501";
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        Assert.assertTrue(tasks.size() > 0);

        for(Task task : tasks){

            Map<String, Object> vars = new HashMap<>(4);
            vars.put("p_pass", "true");
            taskService.complete(task.getId(), vars);
            System.out.println( "[" + task.getName() + "]完成!");
        }
    }

    @Test
    public void completeTaskByFormServiceTest() {
        String processInstanceId = "2501";
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        Assert.assertTrue(tasks.size() > 0);

        for(Task task : tasks){
            taskService.unclaim(task.getId());
            taskService.claim(task.getId(),"winjean");
            System.out.println( "[" + task.getName() + "]签收完成!");

            Map<String, String> vars = new HashMap<>(4);
            vars.put("p_pass", "true");
            formService.submitTaskFormData(task.getId(), vars);
            System.out.println( "[" + task.getName() + "]完成!");
        }
    }

    @Test
    @Ignore
    public void getFlowElementTest(){

        String procDefId = "dispatch:1:4";
        //获取BpmnModel对象
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);
        //获取Process对象
        Process process = bpmnModel.getProcesses().get(bpmnModel.getProcesses().size()-1);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();

        for(FlowElement flowElement: flowElements){

            if(flowElement instanceof StartEvent){
                StartEvent startEvent = (StartEvent)flowElement;
                System.out.println("StartEvent: " + startEvent.getId()+ " " +startEvent.getName());
            } else if (flowElement instanceof org.activiti.bpmn.model.Task){
                org.activiti.bpmn.model.Task task = (org.activiti.bpmn.model.Task)flowElement;
                System.out.println("Task: " + task.getId()+ " " +task.getName());
            }else if (flowElement instanceof Gateway){
                Gateway gateway = (Gateway)flowElement;
                System.out.println("Gateway: " + gateway.getId()+ " " +gateway.getName());
            }else if(flowElement instanceof EndEvent){
                EndEvent endEvent = (EndEvent)flowElement;
                System.out.println("EndEvent: " + endEvent.getId()+ " " +endEvent.getName());
            }else if(flowElement instanceof SequenceFlow){
                SequenceFlow sequenceFlow = (SequenceFlow)flowElement;
                System.out.println("SequenceFlow: " + sequenceFlow.getId());
            }else if(flowElement instanceof SubProcess){
                SubProcess subProcess = (SubProcess)flowElement;
                System.out.println("SubProcess: " + subProcess.getId());
            }else{
                System.out.println(flowElement.getId()+ " " +flowElement.getName());
            }
        }
    }

    @Test
    public void getFormInfoTest(){
        String processDefinitionId = "process-external-form:1:10";

        String startFormKey = formService.getStartFormKey(processDefinitionId);
        Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
        System.out.println("startFormKey:" + startFormKey +",\n renderedStartForm: "+renderedStartForm);


        //正在运行的task的form
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().list();
        String taskId = historicTaskInstances.get(0).getId();

        String taskDefinitionKey = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult().getTaskDefinitionKey();
        String taskFormKey = formService.getTaskFormKey(processDefinitionId,taskDefinitionKey);
        System.out.println("taskDefinitionKey:" + taskDefinitionKey+", taskFormKey: "+taskFormKey);

        Task task = taskService.createTaskQuery().singleResult();
        Object renderedTaskForm = formService.getRenderedTaskForm(task.getId());
        System.out.println( "renderedTaskForm:" + renderedTaskForm);

//        StartFormData startFormData = formService.getStartFormData(processDefinitionId);
//        List<FormProperty> formProperties = startFormData.getFormProperties();
//        for(FormProperty formProperty : formProperties){
//            System.out.println(formProperty.getId() +" "+ formProperty.getName() + " " + formProperty.getValue());
//        }

//        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
//        String formKey = taskFormData.getFormKey();
//        List<FormProperty> formProperties = taskFormData.getFormProperties();
//        for(FormProperty formProperty : formProperties){
//            System.out.println(formProperty.getId() +" "+ formProperty.getName() + " " + formProperty.getValue());
//        }
    }

    @Test
    @Ignore
    public void getProcessResourceByProcessDefinitionIdTest() throws Exception{

        String processDefinitionId = "process-external-form:1:10";

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", new ArrayList<>(), new ArrayList<>(),"宋体","宋体","宋体",null,1.0);
        File file = new File("d:/image.png");
        FileUtils.copyToFile(imageStream,file);

        System.out.println("image path: " + file.getPath());
    }

    @Test
    @Ignore
    public void getProcessResourceByProcessInstanceIdTest() throws Exception{
        String processInstanceId = "7501";

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", activeActivityIds, new ArrayList<String>(),"宋体","宋体","宋体",null,1.0);

        File file = new File("d:/bpmmImage.png");
        FileUtils.copyToFile(imageStream,file);
        System.out.println("image path: " + file.getPath());
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
