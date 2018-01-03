package com.wisedu.zzfw.generator.impl;

import org.springframework.stereotype.Component;

import com.wisedu.zzfw.generator.ViewGenerator;

@Component
public class JsGenerator extends ViewGenerator {

	@Override
	protected void init() {
		super.init();
		this.setTemplateName("template.js");
		super.initModelParams();
		this.initFilePath();
	}

	@Override
	protected void initFilePath() {
		String jspPath = this.getBeanModel().getModelAttributes().getPageAttributes().getJspPath().replaceAll("\\.", "/");
		String filePath = this.getProjectPath()+ "/src/main/webapp/resources/js/"+ jspPath +"/"+this.getViewName()+".js";
		this.setFilePath(filePath);
	}
	
}
