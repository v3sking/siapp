package com.wisedu.zzfw.generator.controller;

import com.wisedu.zzfw.generator.JavaGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractControllerGenerator extends JavaGenerator {
	
	private String controllerRequestMapping;
	private String viewPath;
	private String viewFilePrefix;
	
	@Override
	protected void init() {
		super.init();
		this.setClassName(this.className());
		this.setPackageName(this.packageName());
		this.controllerRequestMapping = controllerRequestMapping();
		this.viewPath = viewPath();
		this.viewFilePrefix = viewFilePrefix();
		this.setTemplateName(this.templateName());
		this.setFilePath(this.filePath());
		
	}
	
	protected abstract String controllerRequestMapping();
	
	protected abstract String viewPath();
	
	protected abstract String viewFilePrefix();

	@Override
	protected String className() {
		return this.getModelSimpleName()+"Controller";
	}
	
	@Override
	protected String templateName() {
		return "TemplateController.java";
	} 
	
	@Override
	protected String packageName() {
		return this.getJavaAttributes().getControllerPackage();
	}
	
	protected String filePath() {
		String packageName = this.getPackageName();
		String filePath = this.getViewProjectName()+ "/src/main/java/"+ packageName.replaceAll("\\.", "/") +"/"+this.getClassName()+".java";
		return filePath;
	}
	
	

}
