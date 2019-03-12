package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/12 11:39
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Service
@Slf4j
public class ActivitiModelService {

    @Autowired
    private  RepositoryService repositoryService;

    public Deployment getBpmnModel(JSONObject json) throws Exception{
        //创建bpmn模型
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId(json.getString("proc-def-key"));
        process.setName(json.getString("proc-def-name"));

        //创建bpmn元素
        process.addFlowElement(createStartEvent());
        process.addFlowElement(createUserTask("task1", "First task", "fred"));
        process.addFlowElement(createUserTask("task2", "Second task", "john"));
        process.addFlowElement(createEndEvent());


        process.addFlowElement(createSequenceFlow("start", "task1"));
        process.addFlowElement(createSequenceFlow("task1", "task2"));
        process.addFlowElement(createSequenceFlow("task2", "end"));


        // 2.生成BPMN自动布局
        new BpmnAutoLayout(model).execute();

        if(validateBpmnModel(model)){
            log.info("success!");
        }else{
            log.error("validateBpmnModel error!");
            return null;
        }

//        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model,"utf-8");

        Deployment deployment = repositoryService.createDeployment()
                .name(json.getString("proc-deployment-name"))
//                .addString(json.getString("resourceName")+".bpmn20.xml", new String(bpmnBytes, "UTF-8"))
                .addBpmnModel(json.getString("resourceName")+".bpmn20.xml",model)
                .deploy();

        return deployment;
    }

    public boolean validateBpmnModel(BpmnModel bpmnModel){
        ProcessValidatorFactory processValidatorFactory = new ProcessValidatorFactory();

        ProcessValidator processValidator = processValidatorFactory.createDefaultProcessValidator();

        //验证失败信息的封装ValidationError
        List<ValidationError> errors = processValidator.validate(bpmnModel);
        for(ValidationError error : errors){
            log.info("ActivityId: {}, ActivityName: {},Problem: {}. ",error.getActivityId(),error.getActivityName() ,error.getProblem());
        }

        return errors.size() == 0;
    }

    //  创建task
    private UserTask createUserTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }


    //创建箭头
    private SequenceFlow createSequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }


    private StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        return startEvent;
    }


    private EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        return endEvent;
    }
}
