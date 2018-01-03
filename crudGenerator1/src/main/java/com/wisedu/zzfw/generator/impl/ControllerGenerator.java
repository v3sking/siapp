package com.wisedu.zzfw.generator.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.generator.JavaGenerator;
import com.wisedu.zzfw.model.BeanModel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
@Setter
public class ControllerGenerator extends JavaGenerator {
	
	private String controllerPath;
	private String jspPath;
	private String viewName;
	
	@Override
	protected void init() {
		super.init();
		this.setClassName(this.getModelSimpleName()+"Controller");//controller,service,param
		this.setTemplateName("TemplateController.java");
		
		BeanModel beanModel = super.getBeanModel();
		ModelAttributes modelAttributes = beanModel.getModelAttributes();
		this.setPackageName(modelAttributes.getJavaAttributes().getControllerPackage());
		
		initFilePath();
		
		this.controllerPath = modelAttributes.getJavaAttributes().getControllerPath();
		this.jspPath = modelAttributes.getPageAttributes().getJspPath();
		this.viewName = beanModel.getBeanSimpleName().toLowerCase();
	}
	
	@Override
	protected Map<String, Object> initModelParams() {
		Map<String, Object> model = super.initModelParams();
		model.put("controllerPath",this.getControllerPath());
		model.put("jspPath",this.getJspPath());
		model.put("viewName",viewName);
		return model;
	}
	
	protected void initFilePath(){
		String packageName = this.getPackageName();
		String filePath = this.getViewProjectName()+ "/src/main/java/"+ packageName.replaceAll("\\.", "/") +"/"+this.getClassName()+".java";
		this.setFilePath(filePath);
	} 
}
