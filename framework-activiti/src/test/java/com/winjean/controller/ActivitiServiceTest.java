package com.winjean.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winjean.ActivitiApplication;
import com.winjean.command.DeleteRunningTaskCmd;
import com.winjean.command.JumpActivityCmd;
import com.winjean.service.DeployService;
import com.winjean.service.ModelService;
import com.winjean.utils.ModelUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/14 17:24
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@SpringBootTest(classes = ActivitiApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ActivitiServiceTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private DeployService deployService;

    @Test
    public void establishModelTest() throws Exception{

        //创建流程模型
        JSONObject json = new JSONObject();
        json.put("proc-def-key","proc-def-key");
        json.put("proc-def-name","proc-def-key");
        StringBuffer sb = new StringBuffer();
        sb.append("[")
                .append("{\"id\":\"user_task_1\",\"name\":\"用户任务1\",\"assignee\":\"winjean\"")
                    .append(",\"formProperties\" :[{\"id\":\"title\",\"name\":\"标题\",\"type\":\"string\",\"writable\":\"true\"}]")
                .append("},")

                .append("{\"id\":\"user_task_2\",\"name\":\"用户任务2\",\"assignee\":\"winjean\"")
                    .append(",\"formProperties\" :[{\"id\":\"title\",\"name\":\"标题\",\"type\":\"string\",\"writable\":\"true\"}]")
                .append("},")

                .append("{\"id\":\"user_task_3\",\"name\":\"用户任务3\",\"assignee\":\"winjean\"}")
//                .append("")
                .append("]");
        List<JSONObject> tasks = JSONArray.parseArray(sb.toString(), JSONObject.class);
        json.put("userTasks",tasks);

        sb = new StringBuffer();
        sb.append("[")
                .append("{\"id\":\"sequence_flow_1\",\"from\":\"start\",\"to\":\"user_task_1\"},")
                .append("{\"id\":\"sequence_flow_2\",\"from\":\"user_task_1\",\"to\":\"user_task_2\"},")
                .append("{\"id\":\"sequence_flow_3\",\"from\":\"user_task_2\",\"to\":\"user_task_3\"},")
                .append("{\"id\":\"sequence_flow_4\",\"from\":\"user_task_3\",\"to\":\"end\"}")
                .append("]");
        List<JSONObject> sequenceFlows = JSONArray.parseArray(sb.toString(), JSONObject.class);
        json.put("sequenceFlows",sequenceFlows);

        BpmnModel bpmnModel = modelService.getBpmnModel(json);

        //生成bpmn文件
        byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        System.out.println(new String(bytes, "utf-8"));

        //根据流程模型部署流程
        json.put("proc-deployment-name","proc-deployment-name");
        json.put("resourceName","resourceName");
        Deployment deployment = deployService.deployWithBpmnModel(json, bpmnModel);

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        String processDefinitionId =  processDefinition.getId();

        //根据流程定义编号启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
        String processInstanceId = processInstance.getProcessInstanceId();

        //完成任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
        taskService.complete(task.getId());

        //根据流程实例编号生成流程图
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);

        String procDefId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();
        bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", activeActivityIds, new ArrayList<String>(),"宋体","宋体","宋体",null,1.0);

        File file = new File("d:/a.png");
        FileUtils.copyToFile(imageStream,file);
    }


    @Test
    public void activitTest() throws Exception{

        String modelName = "model name";
        String description = " model description";

        Model model = ModelUtils.createNewModel(repositoryService, modelName, description);
        Deployment deployment = ModelUtils.deployModel(repositoryService, model.getId());

        String deploymentId = deployment.getId();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        String processDefinitionId =  processDefinition.getId();

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
        String processInstanceId = processInstance.getProcessInstanceId();
//        String processInstanceId = "25001";

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();

        for (Task task : tasks){
            taskService.complete(task.getId());
        }

//        ProcessInstance processInstance = null;

        TaskEntity taskEntity = (TaskEntity)processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
        String taskName = taskEntity.getName();
        System.out.println(taskName);
        ((RuntimeServiceImpl) processEngine.getRuntimeService()).getCommandExecutor().execute(new DeleteRunningTaskCmd(taskEntity));

//        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();

        for (Task task : tasks){
            taskService.complete(task.getId());
        }

        taskService.deleteTask("taskId");

        Attachment attachment = taskService.createAttachment("png","taskid","pid","attachment name","desc","");
        taskService.getTaskAttachments("taskId");

        taskService.addComment("taskId","pid","type","msg");
        taskService.getTaskComments("taskId","type");

    }

    @Test
    //跳转任意activity
    public void JumpActivity() {
        String processInstanceId = "7507";
        String activityId = "user_task_2";

        managementService.executeCommand(new JumpActivityCmd(processInstanceId, activityId));
    }

    @Test
    public void getActivityImpl() {
        String processDefinitionId = "proc-def-key:6:15006";
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processDefinitionId);
        List<ActivityImpl> list = processDefinition.getActivities();
        for(ActivityImpl activity : list){
            System.out.println(activity.getId());
            System.out.println(activity.getParent().getId());
            activity.getParent().getActivities().forEach((a) -> System.out.println(" --- parent -- " +a.getId()));
        }
    }

    @Test
    //根据流程实例编号生成流程图
    public void diagramGenerator() throws Exception{

        String processInstanceId = "7507";
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);

        String procDefId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", activeActivityIds, new ArrayList<String>(),"宋体","宋体","宋体",null,1.0);

        File file = new File("d:/a.png");
        FileUtils.copyToFile(imageStream,file);

    }




}
