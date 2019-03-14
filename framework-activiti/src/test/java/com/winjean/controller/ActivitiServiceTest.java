package com.winjean.controller;

import com.winjean.ActivitiApplication;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/14 17:24
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@SpringBootTest(classes = ActivitiApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ActivitiServiceTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void activitTest() throws Exception{
//        ActivityImpl activity =

//        String modelName = "model name";
//        String description = " model description";
//
//        Model model = ModelUtils.createNewModel(repositoryService, modelName, description);
//        Deployment deployment = ModelUtils.deployModel(repositoryService, model.getId());

//        String deploymentId = deployment.getId();

//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
//        String processDefinitionId =  processDefinition.getId();

//        proc-def-key:5:22506


    }
}
