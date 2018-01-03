package com.wisedu.zzfw.generator.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wisedu.zzfw.generator.JavaGenerator;
import com.wisedu.zzfw.model.BeanModel;

@Service
public class ServiceGenerator extends JavaGenerator{
	
	@Override
	protected void init() {
		super.init();
		this.setPackageName(this.getServicePackageName());
		this.setClassName(this.getModelSimpleName()+"Service");//controller,service,param
		
		this.setTemplateName("TemplateService.java");
		
		this.initFilePath();
	}
	
	@Override
	protected Map<String, Object> initModelParams() {
		Map<String, Object> model = super.initModelParams();
		return model;
	}
}
