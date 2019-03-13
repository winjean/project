package com.winjean.controller.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.winjean.common.BaseResponse;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：winjean
 * @date ：Created in 2019/2/22 14:52
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("model")
public class ModelController {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("create")
    public void createModel(HttpServletRequest request, HttpServletResponse response){
        try{
            String modelName = "modelName";
            String modelKey = "modelKey";
            String description = "description";

            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

            RepositoryService repositoryService = processEngine.getRepositoryService();

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, modelName);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(modelName);
            modelData.setKey(modelKey);

            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            response.sendRedirect(request.getContextPath() + "/static/modeler.html?modelId=" + modelData.getId());
        }catch (Exception e){
        }
    }

    @GetMapping("/list")
    public Object list() {
        try {
            List list = new ArrayList<>();
            ModelQuery query = repositoryService.createModelQuery();
            List<Model> models = query.orderByCreateTime().desc().list();
            for (Model model : models) {
                Map map = new HashMap<>();
                map.put("id", model.getId());
                map.put("name", model.getName());
                map.put("key", model.getKey());
                map.put("meta", model.getMetaInfo());
                map.put("deploymentId", model.getDeploymentId());
                map.put("createTime", model.getCreateTime());
                map.put("lastUpdateTime", model.getLastUpdateTime());
                list.add(map);
            }
            return BaseResponse.getSuccessResponse(list);
        } catch (Exception e) {
            return BaseResponse.getFailureResponse();
        }
    }


}


