package com.winjean.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Model> queryModel(JSONObject json){

        List<Model> models = repositoryService.createModelQuery().orderByCreateTime().desc().list();

        return models;
    }

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
            JSONArray _fps = task.getJSONArray("formProperties");
            if(_fps != null){
                List<FormProperty> formProperties = establishFormProperty(_fps);
                userTask.setFormProperties(formProperties);
            }

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

    private List<FormProperty> establishFormProperty(JSONArray _array){
        List<FormProperty> list = new ArrayList<>();

        _array.forEach((fp) -> {
            JSONObject json = (JSONObject)fp;
            FormProperty formProperty = new FormProperty();
            String id = json.getString("id");
            if(StringUtils.isNotEmpty(id)) formProperty.setId(id);

            String name = json.getString("name");
            if(StringUtils.isNotEmpty(name)) formProperty.setName(name);

            String type = json.getString("type");
            if(StringUtils.isNotEmpty(type)) formProperty.setType(type);

            String writable = json.getString("writable");
            if(StringUtils.isNotEmpty(writable)) formProperty.setWriteable(Boolean.valueOf(writable));

            String readable = json.getString("readable");
            if(StringUtils.isNotEmpty(readable)) formProperty.setReadable(Boolean.valueOf(readable));

            String required = json.getString("required");
            if(StringUtils.isNotEmpty(required)) formProperty.setRequired(Boolean.valueOf(required));

            String expression = json.getString("expression");
            if(StringUtils.isNotEmpty(expression)) formProperty.setExpression(expression);

            String variable = json.getString("variable");
            if(StringUtils.isNotEmpty(variable)) formProperty.setVariable(variable);

            list.add(formProperty);
        });

        return list;
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

        /*String processName = bpmnModel.getMainProcess().getName();
        if (StringUtils.isEmpty(processName)){
            processName = bpmnModel.getMainProcess().getId();
        }

        Model modelData = repositoryService.newModel();
        modelData.setName(processName);
        modelData.setKey("modelKey");
//        modelData.set
        repositoryService.saveModel(modelData);

        ObjectNode modelObjectNode = new BpmnJsonConverter().convertToJson(bpmnModel);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processName);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, "MODEL_DESCRIPTION");


        String id = modelData.getId();
//        byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel, "utf-8");

//        System.out.println(new String(bytes, "utf-8"));

//        repositoryService.addModelEditorSource(id,bytes);
        repositoryService.addModelEditorSource(id,modelObjectNode.toString().getBytes("utf-8"));
//        repositoryService.addModelEditorSourceExtra("");*/



        ObjectNode editorNode = new BpmnJsonConverter().convertToJson(bpmnModel);// objectMapper.createObjectNode();
        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.set("stencilset", stencilSetNode);

        Model modelData = repositoryService.newModel();
        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "MODEL_NAME");
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        String description = StringUtils.defaultString("Description");
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelData.setMetaInfo(modelObjectNode.toString());
        modelData.setName("MODEL_NAME");
        modelData.setKey(StringUtils.defaultString("model.getKey()"));

        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));



    }

//    public class TaskListenerImpl implements TaskListener {
//
//        @Override
//        public void notify(DelegateTask delegateTask) {
//            String assignee = "winjean";
//            delegateTask.setAssignee(assignee);
//        }
//    }
}
