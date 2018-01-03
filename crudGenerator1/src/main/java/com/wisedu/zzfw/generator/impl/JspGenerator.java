package com.wisedu.zzfw.generator.impl;

import org.springframework.stereotype.Component;

import com.wisedu.zzfw.generator.ViewGenerator;

@Component
public class JspGenerator extends ViewGenerator{

	@Override
	protected void init() {
		super.init();
		this.setTemplateName("template.jsp");
		super.initModelParams();
		this.initFilePath();
	}
	
	@Override
	protected void initFilePath() {
		String jspPath = this.getBeanModel().getModelAttributes().getPageAttributes().getJspPath().replaceAll("\\.", "/");
		String filePath = this.getProjectPath()+ "/src/main/webapp/WEB-INF/views/"+ jspPath +"/"+this.getViewName()+".jsp";
		this.setFilePath(filePath);
	}

}
