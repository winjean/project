package com.winjean.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.h2.util.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 包装了对BPMN模型的部署、注册等功能
 * 
 * @author bluejoe2008@gmail.com
 *
 */
public class ModelUtils{
	private static String EMPTY_MODEL_XML = "";

	private static String EMPTY_MODEL_XML_PATH = "processes/empty-model.bpmn";

	static{
		Resource defaultModelXmlResource = new ClassPathResource(EMPTY_MODEL_XML_PATH);
		try {
			EMPTY_MODEL_XML = IOUtils.readStringAndClose(new InputStreamReader(defaultModelXmlResource.getInputStream()),(int) defaultModelXmlResource.contentLength());
		} catch (IOException e)	{
			throw new RuntimeException(e);
		}
	}

	public static Model createNewModel(RepositoryService repositoryService, String name, String description)
			throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Model modelData = repositoryService.newModel();

		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);

		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
		modelData.setMetaInfo(modelObjectNode.toString());
		modelData.setName(name);

		repositoryService.saveModel(modelData);
		repositoryService.addModelEditorSource(modelData.getId(), EMPTY_MODEL_XML.getBytes("utf-8"));
		return modelData;
	}

	public static Deployment deployModel(RepositoryService repositoryService, String modelId) throws IOException{
		Model modelData = repositoryService.getModel(modelId);
		//EditorSource就是XML格式的
		byte[] bpmnBytes = repositoryService.getModelEditorSource(modelId);

		String processName = modelData.getName() + ".bpmn20.xml";
		Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
				.addString(processName, new String(bpmnBytes, "utf-8")).deploy();

		//设置部署ID
		modelData.setDeploymentId(deployment.getId());
		repositoryService.saveModel(modelData);

		return deployment;
	}

	public static void importModel(RepositoryService repositoryService, File modelFile) throws IOException,
			XMLStreamException	{

		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(new FileInputStream(modelFile));
		XMLStreamReader xtr = xif.createXMLStreamReader(in);

		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

		String processName = bpmnModel.getMainProcess().getName();
		if (processName == null || processName.isEmpty())
		{
			processName = bpmnModel.getMainProcess().getId();
		}

		Model modelData = repositoryService.newModel();
		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processName);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelData.setMetaInfo(modelObjectNode.toString());
		modelData.setName(processName);
		modelData.setKey(modelFile.getName());

		repositoryService.saveModel(modelData);

		InputStreamReader reader = new InputStreamReader(new FileInputStream(modelFile), "utf-8");
		String fileContent = IOUtils.readStringAndClose(reader, (int) modelFile.length());

		repositoryService.addModelEditorSource(modelData.getId(), fileContent.getBytes("utf-8"));
	}
}
