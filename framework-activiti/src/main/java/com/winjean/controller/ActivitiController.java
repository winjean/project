package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.service.ActivitiDeployService;
import com.winjean.service.ActivitiModelService;
import com.winjean.service.ActivitiService;
import com.winjean.utils.DateUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
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
    private ActivitiModelService modelService;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private ActivitiDeployService activitiDeployService;

    @PostMapping("createModel")
    public Object createModel1(@RequestBody JSONObject json) throws Exception{

        BpmnModel bpmnModel = modelService.getBpmnModel(json);

//        Deployment deployment = activitiDeployService.deployWithBpmnModel(json, bpmnModel);
//
//        appendDeploymentInfo(json, deployment);

        return json;
    }

    @PostMapping("deployWithModelId")
    public Object deploy(@RequestBody JSONObject json) throws Exception{

        Deployment deployment = activitiDeployService.deploy(json);

        appendDeploymentInfo(json, deployment);

        return json;
    }

    @PostMapping("deploymentWithClasspathResource")
    public Object deploymentWithClasspathResource(@RequestBody JSONObject json){

        Deployment deployment = activitiDeployService.deploymentWithClasspathResource(json);

        appendDeploymentInfo(json, deployment);

        return json;
    }

    @PostMapping("deploymentWithInputStream")
    public Object deploymentWithInputStream(@RequestBody JSONObject json) throws Exception{

        Deployment deployment = activitiDeployService.deploymentWithInputStream(json);

        appendDeploymentInfo(json, deployment);

        return json;
    }

    @PostMapping("deploymentWithString")
    public Object deploymentWithString(@RequestBody JSONObject json){
        Deployment deployment = activitiDeployService.deploymentWithString(json);

        appendDeploymentInfo(json, deployment);

        return json;
    }

    @PostMapping("deploymentWithZipInputStream")
    public Object deploymentWithZipInputStream(@RequestBody JSONObject json){

        Deployment deployment = activitiDeployService.deploymentWithZipInputStream(json);

        appendDeploymentInfo(json, deployment);

        return json;
    }

    @PostMapping("startProcessByRuntimeService")
    public Object startProcessByRuntimeService(@RequestBody JSONObject json) {

        ProcessInstance processInstance = activitiService.startProcessByRuntimeService(json);
        appendProcessInstanceInfo(json, processInstance);
        return json;
    }

    @PostMapping("startProcessByFormService")
    public Object startProcessByFormService(@RequestBody JSONObject json) {

        ProcessInstance processInstance = activitiService.startProcessByFormService(json);
        appendProcessInstanceInfo(json, processInstance);
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

    private void appendDeploymentInfo(JSONObject json, Deployment deployment){
        json.put("deploymentId",deployment.getId());
        json.put("deploymentName",deployment.getName());
        json.put("deploymentTime", DateUtils.formatDate(deployment.getDeploymentTime()));
    }

    private void appendProcessInstanceInfo(JSONObject json, ProcessInstance processInstance){
        json.put("processInstanceId",processInstance.getId());
        json.put("processInstanceName",processInstance.getName());
    }
}
