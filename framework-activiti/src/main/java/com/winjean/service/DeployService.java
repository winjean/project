package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

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
public class DeployService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    public Deployment deployWithBpmnModel(JSONObject json,BpmnModel bpmnModel){
        Deployment deployment = repositoryService.createDeployment()
                .name(json.getString("proc-deployment-name"))
                .addBpmnModel(json.getString("resourceName")+".bpmn20.xml",bpmnModel)
                .deploy();

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());

        return deployment;
    }

    public Deployment deployWithModelId(JSONObject json) throws Exception{
        Model modelData = repositoryService.getModel(json.getString("modelId"));
        byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());

        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(modelEditorSource);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
//                .addBpmnModel(modelData.getName(),model)
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());
        return deployment;
    }

//    public Deployment deployWithModelId(JSONObject json) throws Exception{
//        Model modelData = repositoryService.getModel(json.getString("modelId"));
//        byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
//
//        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(modelEditorSource);
//
//        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
//        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
//
//        String processName = modelData.getName() + ".bpmn20.xml";
//        Deployment deployment = repositoryService.createDeployment()
//                .name(modelData.getName())
//                .addString(processName, new String(bpmnBytes, "UTF-8"))
//                .deploy();
//
//        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());
//        return deployment;
//    }

    public Deployment deploymentWithClasspathResource(JSONObject json){
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .name(json.getString("processName"))//声明流程的名称
                .addClasspathResource(json.getString("processFile"))//加载资源文件，一次只能加载一个文件 "activiti/external-form.bpmn"
                .deploy();//完成部署

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());

        return deployment;
    }

    public Deployment deploymentWithInputStream(JSONObject json) throws Exception{

        //读取资源作为一个输入流
        FileInputStream bpmnfileInputStream = new FileInputStream(json.getString("bpmnfile"));
        FileInputStream pngfileInputStream = new FileInputStream(json.getString("pngfile"));

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addInputStream("helloworld.bpmn",bpmnfileInputStream)
                .addInputStream("helloworld.png", pngfileInputStream)
                .deploy();//完成部署

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());

        return deployment;
    }

    public Deployment deploymentWithString(JSONObject json){
        //字符串
        String xml = json.getString("xml");
//        <?xml version="1.0" encoding="utf-8"?>
//<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:activiti="http://activiti.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.activiti.org/test">
//  <process id="proc-def-key" name="proc-def-name" isExecutable="true">
//    <startEvent id="start" name="开始"></startEvent>
//    <userTask id="task1" name="First task" activiti:assignee="fred"></userTask>
//    <userTask id="task2" name="Second task" activiti:assignee="john"></userTask>
//    <endEvent id="end" name="结束"></endEvent>
//    <sequenceFlow id="sequenceFlow-6f2c02de-5596-4f82-af21-5b7c697fd2c4" name="1" sourceRef="start" targetRef="task1"></sequenceFlow>
//    <sequenceFlow id="sequenceFlow-85b484c3-7454-4336-a0ea-86f7a8a4af55" name="5" sourceRef="task1" targetRef="task2"></sequenceFlow>
//    <sequenceFlow id="sequenceFlow-3032aaec-d6da-4639-8a76-6ae2bbb61c25" name="6" sourceRef="task2" targetRef="end"></sequenceFlow>
//  </process>
//  <bpmndi:BPMNDiagram id="BPMNDiagram_proc-def-key">
//    <bpmndi:BPMNPlane bpmnElement="proc-def-key" id="BPMNPlane_proc-def-key">
//      <bpmndi:BPMNShape bpmnElement="start" id="BPMNShape_start">
//        <omgdc:Bounds height="30.0" width="30.0" x="0.0" y="15.0"></omgdc:Bounds>
//      </bpmndi:BPMNShape>
//      <bpmndi:BPMNShape bpmnElement="end" id="BPMNShape_end">
//        <omgdc:Bounds height="30.0" width="30.0" x="380.0" y="15.0"></omgdc:Bounds>
//      </bpmndi:BPMNShape>
//      <bpmndi:BPMNShape bpmnElement="task1" id="BPMNShape_task1">
//        <omgdc:Bounds height="60.0" width="100.0" x="80.0" y="0.0"></omgdc:Bounds>
//      </bpmndi:BPMNShape>
//      <bpmndi:BPMNShape bpmnElement="task2" id="BPMNShape_task2">
//        <omgdc:Bounds height="60.0" width="100.0" x="230.0" y="0.0"></omgdc:Bounds>
//      </bpmndi:BPMNShape>
//      <bpmndi:BPMNEdge bpmnElement="sequenceFlow-3032aaec-d6da-4639-8a76-6ae2bbb61c25" id="BPMNEdge_sequenceFlow-3032aaec-d6da-4639-8a76-6ae2bbb61c25">
//        <omgdi:waypoint x="330.0" y="30.0"></omgdi:waypoint>
//        <omgdi:waypoint x="342.0" y="30.0"></omgdi:waypoint>
//        <omgdi:waypoint x="342.0" y="30.000000000000004"></omgdi:waypoint>
//        <omgdi:waypoint x="380.0" y="30.000000000000004"></omgdi:waypoint>
//      </bpmndi:BPMNEdge>
//      <bpmndi:BPMNEdge bpmnElement="sequenceFlow-85b484c3-7454-4336-a0ea-86f7a8a4af55" id="BPMNEdge_sequenceFlow-85b484c3-7454-4336-a0ea-86f7a8a4af55">
//        <omgdi:waypoint x="180.0" y="30.0"></omgdi:waypoint>
//        <omgdi:waypoint x="192.0" y="30.0"></omgdi:waypoint>
//        <omgdi:waypoint x="192.0" y="30.000000000000007"></omgdi:waypoint>
//        <omgdi:waypoint x="230.0" y="30.000000000000007"></omgdi:waypoint>
//      </bpmndi:BPMNEdge>
//      <bpmndi:BPMNEdge bpmnElement="sequenceFlow-6f2c02de-5596-4f82-af21-5b7c697fd2c4" id="BPMNEdge_sequenceFlow-6f2c02de-5596-4f82-af21-5b7c697fd2c4">
//        <omgdi:waypoint x="30.0" y="30.0"></omgdi:waypoint>
//        <omgdi:waypoint x="42.0" y="30.0"></omgdi:waypoint>
//        <omgdi:waypoint x="42.0" y="30.000000000000007"></omgdi:waypoint>
//        <omgdi:waypoint x="80.0" y="30.000000000000007"></omgdi:waypoint>
//      </bpmndi:BPMNEdge>
//    </bpmndi:BPMNPlane>
//  </bpmndi:BPMNDiagram>
//</definitions>;

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addString("process-external-form.bpmn",xml)
                .deploy();//完成部署

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());

        return deployment;
    }

    public Deployment deploymentWithZipInputStream(JSONObject json){
        //从classpath路径下读取资源文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("activiti/activiti.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addZipInputStream(zipInputStream)//使用zip方式部署，将*.bpmn和*.png压缩成zip格式的文件
                .deploy();//完成部署

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());

        return deployment;
    }
}
