package com.wisedu.zzfw.generator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.generator.AbstractGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: SqlGenerator
 * @Description: sql文件创建器
 * @author  wutao
 * @date 2017年12月25日 下午6:13:20
 * @Copyright: Copyright (c) 2017 wisedu
 */
@Service
@Getter
@Setter
public class SqlGenerator extends AbstractGenerator{
	private String viewName;
	
	@Override
	protected void init() {
		super.init();
		this.setTemplateName("template.sql");
		this.viewName = this.getBeanModel().getBeanSimpleName().toLowerCase();
		this.initFilePath();
	}
	
	@Override
	protected Map<String, Object> initModelParams() {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAttributes modelAttributes = this.getBeanModel().getModelAttributes();
		
		model.put("moduleWid", UUID.randomUUID().toString().replaceAll("-", ""));
		model.put("moduleName",  modelAttributes.getMenuAttributes().getModuleName());
		model.put("parentModuleName",  modelAttributes.getMenuAttributes().getParentModuleName());
		model.put("controllerPath",  modelAttributes.getJavaAttributes().getControllerPath());
		model.put("menuitemWid", UUID.randomUUID().toString().replaceAll("-", ""));
		model.put("roleName",  modelAttributes.getMenuAttributes().getRoleName());
		List<Map<String,String>> modulepath = new ArrayList<Map<String,String>>();
		for(MethodNameEnum method:MethodNameEnum.values()){
			Map<String,String> content = new HashMap<String,String>();
			content.put("wid", UUID.randomUUID().toString().replaceAll("-", ""));
			content.put("methodName", method.name());
			modulepath.add(content);
		}
		model.put("modulepath", modulepath);
		return model;
	}
	
	protected void initFilePath() {
		String filePath = this.getProjectPath()+ "/src/main/resources/sql/"+this.getViewName()+".sql";
		this.setFilePath(filePath);
	}
	
	private enum MethodNameEnum {
		view,get,list,add,delete,update,exist;
    }

}
