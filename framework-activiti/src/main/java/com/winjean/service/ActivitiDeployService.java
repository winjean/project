package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
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
public class ActivitiDeployService {

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

    public Deployment deploy(JSONObject json) throws Exception{
        Model modelData = repositoryService.getModel(json.getString("modelId"));
        byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());

        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(modelEditorSource);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();

        log.info("deployment id：{}, deployment name：{}, deployment time：{}", deployment.getId(),deployment.getName(), deployment.getDeploymentTime());

        return deployment;
    }

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
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions>...</definitions>";

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addString("process-external-form.bpmn",text)
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
