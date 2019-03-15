package com.winjean.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.repository.Model;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
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
public class ModelService {

    @Autowired
    private  RepositoryService repositoryService;

    @Autowired
    private DeployService deployService;

    public BpmnModel getBpmnModel(JSONObject json) throws Exception{
        //创建bpmn模型
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();
        bpmnModel.addProcess(process);

        process.setId(json.getString("proc-def-key"));
        process.setName(json.getString("proc-def-name"));

        //创建bpmn元素
        process.addFlowElement(createStartEvent());
        JSONArray tasks = json.getJSONArray("userTasks");

        Iterator<Object> it = tasks.iterator();

        while (it.hasNext()){
            JSONObject task = (JSONObject)it.next();
            UserTask userTask = createUserTask(task.getString("id"),task.getString("name"), task.getString("assignee"));
            process.addFlowElement(userTask);
        }

        process.addFlowElement(createEndEvent());

        JSONArray flowElements = json.getJSONArray("sequenceFlows");

        Iterator<Object> feit = flowElements.iterator();

        while (feit.hasNext()){
            JSONObject sequence = (JSONObject)feit.next();
            SequenceFlow sequenceFlow = createSequenceFlow(sequence.getString("id"),sequence.getString("from"), sequence.getString("to"));
            process.addFlowElement(sequenceFlow);
        }

//        process.addFlowElement(createEventGateway());

//        process.addFlowElement(createSequenceFlow("1","start", "task1"));
////        process.addFlowElement(createSequenceFlow("2","task1", "gateway"));
////        process.addFlowElement(createSequenceFlow("3","gateway", "task2"));
////        process.addFlowElement(createSequenceFlow("4","gateway", "end"));
//        process.addFlowElement(createSequenceFlow("5","task1", "task2"));
//        process.addFlowElement(createSequenceFlow("6","task2", "end"));

        // 2.生成BPMN自动布局
        new BpmnAutoLayout(bpmnModel).execute();

//        byte[] bpmnBytes  = new BpmnXMLConverter().convertToXML(bpmnModel,"utf-8");
//        System.out.println(new String(bpmnBytes, "utf-8"));
//
//        ObjectNode objectNode = new BpmnJsonConverter().convertToJson(bpmnModel);
//        System.out.println("------------------------------------------------------");
//        System.out.println(objectNode.toString());

        if(validateBpmnModel(bpmnModel)){
            log.info("success!");
        }else{
            log.error("validateBpmnModel error!");
            return null;
        }

        saveModel(bpmnModel);

        return bpmnModel;
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

//        FormProperty formProperty = new FormProperty();
//        formProperty.setType("enmu");
//        FormValue formValue = new FormValue();
////        formValue.setValue;
//        formProperty.setFormValues(null);

//        userTask.setFormProperties(null);
//        MultiInstanceLoopCharacteristics m = new MultiInstanceLoopCharacteristics();
//        m.setSequential(true);
//        m.setElementVariable("");
//        m.setExtensionElements(null);
//        m.setLoopCardinality("2");
//        userTask.setLoopCharacteristics(m);

//        userTask.setCandidateUsers(null);
//        userTask.setCandidateGroups(null);
//        userTask.setCategory(null);

//        List<ActivitiListener> list = new ArrayList<>();
//
//        ActivitiListener listener = new ActivitiListener();
//
//        listener.setImplementation("TaskListenerImpl");
//        listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
//        listener.setEvent(ActivitiEventType.TASK_COMPLETED.name());
//
//        list.add(listener);
//        userTask.setTaskListeners(list);

        return userTask;
    }

    //创建箭头
    private SequenceFlow createSequenceFlow(String name,String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setName(name);
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }

    private StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        startEvent.setName("开始");
        return startEvent;
    }

    private EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        endEvent.setName("结束");
        return endEvent;
    }

    private ExclusiveGateway createEventGateway() {
        ExclusiveGateway gateway = new ExclusiveGateway();
        gateway.setId("gateway");
        gateway.setName("网关");
//        gateway.setDefaultFlow("end");

//        gateway.setOutgoingFlows();
//        gateway.setIncomingFlows(null);

        return gateway;
    }

    private SubProcess createSubProcess() {
        SubProcess subProcess = new SubProcess();
//        subProcess.clone();


        return subProcess;
    }

    public void saveModel(BpmnModel bpmnModel) throws Exception{

        String processName = bpmnModel.getMainProcess().getName();
        if (StringUtils.isEmpty(processName)){
            processName = bpmnModel.getMainProcess().getId();
        }

        Model modelData = repositoryService.newModel();
        modelData.setName(processName);
        modelData.setKey("modelKey");
        repositoryService.saveModel(modelData);

        ObjectNode modelObjectNode = new BpmnJsonConverter().convertToJson(bpmnModel);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processName);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, "MODEL_DESCRIPTION");


        String id = modelData.getId();
        byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel, "utf-8");

//        System.out.println(new String(bytes, "utf-8"));

        repositoryService.addModelEditorSource(id,bytes);
//        repositoryService.addModelEditorSource(id,modelObjectNode.toString().getBytes("utf-8"));
//        repositoryService.addModelEditorSourceExtra("");
    }

    public class TaskListenerImpl implements TaskListener {

        @Override
        public void notify(DelegateTask delegateTask) {
            String assignee = "winjean";
            delegateTask.setAssignee(assignee);
        }
    }
}
