package com.wisedu.zzfw.generator.controller;

import java.util.Map;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.model.BeanModel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class DefaultControllerGenerator extends AbstractControllerGenerator {
	
	
	@Override
	protected String controllerRequestMapping() {
		return this.getJavaAttributes().getControllerPath();
	}
	
	@Override
	protected String viewPath() {
		return this.getModelAttributes().getPageAttributes().getJspPath();
	}

	@Override
	protected String viewFilePrefix() {
		return this.getBeanModel().getBeanSimpleName().toLowerCase();
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

