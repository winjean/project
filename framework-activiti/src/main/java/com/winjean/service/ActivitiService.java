package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/20 16:52
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Api("activiti ")
@Slf4j
@Service
public class ActivitiService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FormService formService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    public ProcessInstance startProcessByRuntimeService(JSONObject json) {
        identityService.setAuthenticatedUserId(json.getString("userId"));
        Map<String, Object> vars = json;
        ProcessDefinition pd =repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(json.getString("processDefinitionKey"))
                .latestVersion().singleResult();
        ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(),vars);
        runtimeService.setProcessInstanceName(pi.getProcessInstanceId(),json.getString("processName"));

        log.info("processInstance Id：{}", pi.getId());

        return pi;
    }

    public ProcessInstance startProcessByFormService(JSONObject json) {
        identityService.setAuthenticatedUserId(json.getString("userId"));
        Map vars = json;

        ProcessInstance pi = formService.submitStartFormData(json.getString("processDefinitionId"),vars);
        runtimeService.setProcessInstanceName(pi.getProcessInstanceId(),json.getString("processName"));

        log.info(pi.getId());

        return pi;
    }

    public Object completeTaskByTaskService(JSONObject json) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(json.getString("processInstanceId")).list();
        Assert.isTrue(tasks.size() > 0," task size should greater than 0 ");

        for(Task task : tasks){

            Map<String, Object> vars = new HashMap<>(4);
            vars.put("p_pass", "true");
            taskService.complete(task.getId(), vars);
            log.info( "[" + task.getName() + "]完成!");
        }

        return json;
    }

    public Object completeTaskByFormService(JSONObject json) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(json.getString("processInstanceId")).list();
        Assert.isTrue(tasks.size() > 0," task size should greater than 0. ");

        for(Task task : tasks){
            taskService.unclaim(task.getId());
            taskService.claim(task.getId(),"winjean");
            log.info( "[" + task.getName() + "]签收完成!");

            Map<String, String> vars = new HashMap<>(4);
            vars.put("p_pass", "true");
            formService.submitTaskFormData(task.getId(), vars);
            log.info( "[" + task.getName() + "]完成!");
        }

        return json;
    }

    public Object getFlowElement(JSONObject json){

        //获取BpmnModel对象
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(json.getString("procDefId"));
        //获取Process对象
        Process process = bpmnModel.getProcesses().get(bpmnModel.getProcesses().size()-1);
        //获取所有的FlowElement信息
        Collection<FlowElement> flowElements = process.getFlowElements();

        for(FlowElement flowElement: flowElements){

            if(flowElement instanceof StartEvent){
                StartEvent startEvent = (StartEvent)flowElement;
                log.info("StartEvent: ",  startEvent.getId()+ " " +startEvent.getName());
            } else if (flowElement instanceof org.activiti.bpmn.model.Task){
                org.activiti.bpmn.model.Task task = (org.activiti.bpmn.model.Task)flowElement;
                log.info("Task: ", task.getId()+ " " +task.getName());
            }else if (flowElement instanceof Gateway){
                Gateway gateway = (Gateway)flowElement;
                log.info("Gateway: ", gateway.getId()+ " " +gateway.getName());
            }else if(flowElement instanceof EndEvent){
                EndEvent endEvent = (EndEvent)flowElement;
                log.info("EndEvent: ", endEvent.getId()+ " " +endEvent.getName());
            }else if(flowElement instanceof SequenceFlow){
                SequenceFlow sequenceFlow = (SequenceFlow)flowElement;
                log.info("SequenceFlow: ", sequenceFlow.getId());
            }else if(flowElement instanceof SubProcess){
                SubProcess subProcess = (SubProcess)flowElement;
                log.info("SubProcess: ", subProcess.getId());
            }else{
                log.info("other: ",flowElement.getId()+ " " +flowElement.getName());
            }
        }

        return json;
    }

    public Object getFormInfo(JSONObject json){
        String processDefinitionId = "process-external-form:1:10";

        String startFormKey = formService.getStartFormKey(processDefinitionId);
        Object renderedStartForm = formService.getRenderedStartForm(processDefinitionId);
        log.info("startFormKey:{}, renderedStartForm:{} "+startFormKey,renderedStartForm);


        //正在运行的task的form
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().list();
        String taskId = historicTaskInstances.get(0).getId();

        String taskDefinitionKey = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult().getTaskDefinitionKey();
        String taskFormKey = formService.getTaskFormKey(processDefinitionId,taskDefinitionKey);
        log.info("taskDefinitionKey:{}, taskFormKey:{} ",taskDefinitionKey, taskFormKey);

        Task task = taskService.createTaskQuery().singleResult();
        Object renderedTaskForm = formService.getRenderedTaskForm(task.getId());
        log.info( "renderedTaskForm:{}", renderedTaskForm);

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

        return json;
    }

}
