package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.ActivitiApplication;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@Slf4j
public class ActivitiControllerTest implements JavaDelegate {

    private MockMvc mockMvc;

    private String url = "http://localhost:8080/activiti/";

    @Autowired
    private WebApplicationContext wac;

    @Before
    // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCreateModel()throws Exception{

        JSONObject json = new JSONObject();
        json.put("proc-def-key","proc-def-key");
        json.put("proc-def-name","proc-def-name");
        json.put("proc-deployment-name","proc-deployment-name");
        json.put("resourceName","resourceName");

        MvcResult result = mockMvc.perform(post(url+"createModelAndDeploy")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void testDeployWithModelId()throws Exception{

        JSONObject json = new JSONObject();
        json.put("modelId","15001");

        MvcResult result = mockMvc.perform(post(url+"deployWithModelId")
                    .content(json.toJSONString())
                    .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    @Ignore
    public void deploymentWithInputStreamTest() throws Exception{
        //获取资源相对路径
//        String bpmnPath = "activiti/process-external-form.bpmn";
//        String pngPath = "activiti/process-external-form.png";
//
//        //读取资源作为一个输入流
//        FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
//        FileInputStream pngfileInputStream = new FileInputStream(pngPath);
//
//        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
//                .createDeployment()//创建部署对象
//                .addInputStream("helloworld.bpmn",bpmnfileInputStream)
//                .addInputStream("helloworld.png", pngfileInputStream)
//                .deploy();//完成部署
//        System.out.println("部署ID："+deployment.getId());//1
//        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    @Test
    @Ignore
    public void deploymentWithStringTest() throws Exception{
        //字符串
//        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions>...</definitions>";
//
//        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
//                .createDeployment()//创建部署对象
//                .addString("process-external-form.bpmn",text)
//                .deploy();//完成部署
//        System.out.println("部署ID："+deployment.getId());//1
//        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    @Test
    public void deploymentWithZipInputStreamTest() throws Exception{
        //从classpath路径下读取资源文件
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream("activiti/activiti.zip");
//        ZipInputStream zipInputStream = new ZipInputStream(in);
//        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
//                .createDeployment()//创建部署对象
//                .addZipInputStream(zipInputStream)//使用zip方式部署，将*.bpmn和*.png压缩成zip格式的文件
//                .deploy();//完成部署
//        System.out.println("部署ID："+deployment.getId());//1
//        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    @Test
    public void startProcessByRuntimeServiceTest() {
//        String processDefinitionKey = "process";
//        identityService.setAuthenticatedUserId("winjean");
//        Map<String, Object> vars = new HashMap<>(4);
//        vars.put("name", "win");
//        vars.put("age", "11");
//        vars.put("applyUser", "winjean");
//        ProcessDefinition pd =repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult();
//        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(),vars);
//        System.out.println(pi.getId() +" "+ pi.getName());
    }

    @Test
    public void startProcessByFormServiceTest() throws Exception{
        JSONObject json = new JSONObject();
        json.put("authenticatedUserId","winjean");
        json.put("processDefinitionId","proc-def-key:1:30006");

        MvcResult result = mockMvc.perform(post(url+"startProcessByFormService")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void completeTaskByTaskServiceTest() throws Exception{
        JSONObject json = new JSONObject();
        json.put("processInstanceId","32501");

        MvcResult result = mockMvc.perform(post(url+"completeTaskByTaskService")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void completeTaskByFormServiceTest() throws Exception{
        JSONObject json = new JSONObject();
        json.put("processInstanceId","32501");

        MvcResult result = mockMvc.perform(post(url+"completeTaskByFormService")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    @Ignore
    public void getFlowElementTest(){

//        String procDefId = "dispatch:1:4";
//        //获取BpmnModel对象
//        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);
//        //获取Process对象
//        Process process = bpmnModel.getProcesses().get(bpmnModel.getProcesses().size()-1);
//        //获取所有的FlowElement信息
//        Collection<FlowElement> flowElements = process.getFlowElements();
//
//        for(FlowElement flowElement: flowElements){
//
//            if(flowElement instanceof StartEvent){
//                StartEvent startEvent = (StartEvent)flowElement;
//                System.out.println("StartEvent: " + startEvent.getId()+ " " +startEvent.getName());
//            } else if (flowElement instanceof org.activiti.bpmn.model.Task){
//                org.activiti.bpmn.model.Task task = (org.activiti.bpmn.model.Task)flowElement;
//                System.out.println("Task: " + task.getId()+ " " +task.getName());
//            }else if (flowElement instanceof Gateway){
//                Gateway gateway = (Gateway)flowElement;
//                System.out.println("Gateway: " + gateway.getId()+ " " +gateway.getName());
//            }else if(flowElement instanceof EndEvent){
//                EndEvent endEvent = (EndEvent)flowElement;
//                System.out.println("EndEvent: " + endEvent.getId()+ " " +endEvent.getName());
//            }else if(flowElement instanceof SequenceFlow){
//                SequenceFlow sequenceFlow = (SequenceFlow)flowElement;
//                System.out.println("SequenceFlow: " + sequenceFlow.getId());
//            }else if(flowElement instanceof SubProcess){
//                SubProcess subProcess = (SubProcess)flowElement;
//                System.out.println("SubProcess: " + subProcess.getId());
//            }else{
//                System.out.println(flowElement.getId()+ " " +flowElement.getName());
//            }
//        }
    }

    @Test
    public void getFormInfoTest(){
//        String processDefinitionId = "process-external-form:1:10";
//
//        String startFormKey = formService.getStartFormKey(processDefinitionId);
//        Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
//        System.out.println("startFormKey:" + startFormKey +",\n renderedStartForm: "+renderedStartForm);
//
//
//        //正在运行的task的form
//        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().list();
//        String taskId = historicTaskInstances.get(0).getId();
//
//        String taskDefinitionKey = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult().getTaskDefinitionKey();
//        String taskFormKey = formService.getTaskFormKey(processDefinitionId,taskDefinitionKey);
//        System.out.println("taskDefinitionKey:" + taskDefinitionKey+", taskFormKey: "+taskFormKey);
//
//        Task task = taskService.createTaskQuery().singleResult();
//        Object renderedTaskForm = formService.getRenderedTaskForm(task.getId());
//        System.out.println( "renderedTaskForm:" + renderedTaskForm);
//
////        StartFormData startFormData = formService.getStartFormData(processDefinitionId);
////        List<FormProperty> formProperties = startFormData.getFormProperties();
////        for(FormProperty formProperty : formProperties){
////            System.out.println(formProperty.getId() +" "+ formProperty.getName() + " " + formProperty.getValue());
////        }
//
////        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
////        String formKey = taskFormData.getFormKey();
////        List<FormProperty> formProperties = taskFormData.getFormProperties();
////        for(FormProperty formProperty : formProperties){
////            System.out.println(formProperty.getId() +" "+ formProperty.getName() + " " + formProperty.getValue());
////        }
    }

    @Test
    public void getProcessResourceByModelIdTest() throws Exception{

        JSONObject json = new JSONObject();
        json.put("modelId","7503");
        json.put("fileName","d:/a.png");

        MvcResult result = mockMvc.perform(post(url+"getProcessResourceByModelId")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void getProcessResourceByProcessDefinitionIdTest() throws Exception{

        JSONObject json = new JSONObject();
        json.put("processDefinitionId","proc-def-key:1:30006");
        json.put("fileName","d:/a.png");

        MvcResult result = mockMvc.perform(post(url+"getProcessResourceByProcessDefinitionId")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void getProcessResourceByProcessInstanceIdTest() throws Exception{

        JSONObject json = new JSONObject();
        json.put("processInstanceId","32501");
        json.put("fileName","d:/b.png");

        MvcResult result = mockMvc.perform(post(url+"getProcessResourceByProcessInstanceId")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }

    @Test
    public void getBpmnByModelIdTest() throws Exception{

        JSONObject json = new JSONObject();
        json.put("modelId","5095");

        MvcResult result = mockMvc.perform(post(url+"getBpmnByModelId")
                .content(json.toJSONString())
                .header("content-type","application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        log.info("response info : {} ",result.getResponse().getContentAsString());
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
