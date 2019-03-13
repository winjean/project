package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
public class ProcessResourceService {

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

    public Object getProcessResourceByProcessDefinitionId(JSONObject json) throws Exception{

        BpmnModel bpmnModel = repositoryService.getBpmnModel(json.getString("processDefinitionId"));

//        ProcessDefinitionEntity processDefinitionEntity=(ProcessDefinitionEntity) repositoryService.getProcessDefinition("");
//        List<ActivityImpl> list = processDefinitionEntity.getActivities();
//        for(ActivityImpl activity : list){
////            activity.get
//        }

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", new ArrayList<>(), new ArrayList<>(),"宋体","宋体","宋体",null,1.0);
        File file = new File(json.getString("fileName"));
        FileUtils.copyToFile(imageStream,file);

        log.info("image path: {}", file.getPath());

        return json;
    }

    public Object getProcessResourceByProcessInstanceId(JSONObject json) throws Exception{
        // TODO 已结束流程

        String processInstanceId = json.getString("processInstanceId");

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

//        repositoryService.get
//        processEngine.
//        processDefinitionEntity.findActivity

        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", activeActivityIds, new ArrayList<String>(),"宋体","宋体","宋体",null,1.0);

        File file = new File(json.getString("fileName"));
        FileUtils.copyToFile(imageStream,file);

        log.info("image path: {}", file.getPath());

        return json;
    }

}
