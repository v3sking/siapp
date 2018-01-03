package com.wisedu.zzfw.generator.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.wisedu.zzfw.generator.JavaGenerator;

@Component
public class PageModelGenerator extends JavaGenerator{


	@Override
	protected void init() {
		super.init();
		
		this.setPackageName(this.getModelPackageName());
		this.setClassName(this.getModelSimpleName()+"Param");//controller,service,param
		
		this.setTemplateName("TemplatePageModelParam.java");
		
		this.initFilePath();
	}
	
	@Override
	protected Map<String, Object> initModelParams() {
		return super.initModelParams();
	}

}
