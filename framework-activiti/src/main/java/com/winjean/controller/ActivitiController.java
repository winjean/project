package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.service.ActivitiModelService;
import com.winjean.service.ActivitiService;
import com.winjean.utils.DateUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/20 16:52
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@Api("activiti ")
@RequestMapping("/activiti")
@Slf4j
public class ActivitiController {

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

    @Autowired
    private ActivitiModelService modelService;

    @Autowired
    private ActivitiService activitiService;

    @PostMapping("createModel")
    public Object createModel1(@RequestBody JSONObject json) throws Exception{
        Deployment deployment = modelService.getBpmnModel(json);

        json.put("deploymentId",deployment.getId());
        json.put("deploymentName",deployment.getName());
        json.put("deploymentTime", DateUtils.formatDate(deployment.getDeploymentTime()));

        return json;
    }

    @PostMapping("deployWithModelId")
    public Object deploy(@RequestBody JSONObject json) throws Exception{

        Deployment deployment = activitiService.deploy(json);

        json.put("deploymentId",deployment.getId());
        json.put("deploymentName",deployment.getName());
        json.put("deploymentTime", DateUtils.formatDate(deployment.getDeploymentTime()));


        return json;
    }

    @PostMapping("deploymentWithClasspathResource")
    public Object deploymentWithClasspathResource(@RequestBody JSONObject json){

        Deployment deployment = activitiService.deploymentWithClasspathResource(json);

        json.put("deploymentId",deployment.getId());
        json.put("deploymentName",deployment.getName());
        json.put("deploymentTime", DateUtils.formatDate(deployment.getDeploymentTime()));

        return json;
    }

    @PostMapping("deploymentWithInputStream")
    public Object deploymentWithInputStream(@RequestBody JSONObject json) throws Exception{

        Deployment deployment = activitiService.deploymentWithInputStream(json);

        json.put("deploymentId",deployment.getId());
        json.put("deploymentName",deployment.getName());
        json.put("deploymentTime", DateUtils.formatDate(deployment.getDeploymentTime()));

        return json;
    }

    @PostMapping("deploymentWithString")
    public Object deploymentWithString(@RequestBody JSONObject json){
        Deployment deployment = activitiService.deploymentWithString(json);

        json.put("deploymentId",deployment.getId());
        json.put("deploymentName",deployment.getName());
        json.put("deploymentTime", DateUtils.formatDate(deployment.getDeploymentTime()));

        return json;
    }

    @PostMapping("deploymentWithZipInputStream")
    public Object deploymentWithZipInputStream(@RequestBody JSONObject json){

        Deployment deployment = activitiService.deploymentWithZipInputStream(json);

        json.put("deploymentId",deployment.getId());
        json.put("deploymentName",deployment.getName());
        json.put("deploymentTime", DateUtils.formatDate(deployment.getDeploymentTime()));

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());

        return json;
    }

    @PostMapping("startProcessByRuntimeService")
    public Object startProcessByRuntimeService(@RequestBody JSONObject json) {

        ProcessInstance processInstance = activitiService.startProcessByRuntimeService(json);
        json.put("processInstanceId",processInstance.getId());
        json.put("processInstanceName",processInstance.getName());

        return json;
    }

    @PostMapping("startProcessByFormService")
    public Object startProcessByFormService(@RequestBody JSONObject json) {

        ProcessInstance processInstance = activitiService.startProcessByFormService(json);
        json.put("processInstanceId",processInstance.getId());
        json.put("processInstanceName",processInstance.getName());
        return json;
    }

    @PostMapping("completeTaskByTaskService")
    public Object completeTaskByTaskService(@RequestBody JSONObject json) {

        activitiService.completeTaskByTaskService(json);

        return json;
    }

    @PostMapping("completeTaskByFormService")
    public Object completeTaskByFormService(@RequestBody JSONObject json) {
        activitiService.completeTaskByFormService(json);

        return json;
    }

    @PostMapping("getFlowElement")
    public Object getFlowElement(@RequestBody JSONObject json){

        activitiService.getFlowElement(json);

        return json;
    }

    @PostMapping("getFormInfo")
    public Object getFormInfo(@RequestBody JSONObject json){
        activitiService.getFormInfo(json);
        return json;
    }

    @PostMapping("getProcessResourceByProcessDefinitionId")
    public Object getProcessResourceByProcessDefinitionId(@RequestBody JSONObject json) throws Exception{

        activitiService.getProcessResourceByProcessDefinitionId(json);

        return json;
    }

    @PostMapping("getProcessResourceByProcessInstanceId")
    public Object getProcessResourceByProcessInstanceId(@RequestBody JSONObject json) throws Exception{
        activitiService.getProcessResourceByProcessInstanceId(json);

        return json;
    }

}
