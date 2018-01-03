package com.wisedu.zzfw.generator.decorator;

import org.springframework.beans.factory.annotation.Autowired;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.model.BeanModel;

import freemarker.template.Configuration;

public class FileDecorator {
	
	private boolean enabled;
	
	@Autowired  
	private Configuration configuration;
	
	/**
	 * mbg生成的java模型类
	 */
	private BeanModel beanModel; 
	
	/**
	 * 模型额外属性
	 */
	private ModelAttributes modelAttributes;

	/**
	 * E:/workspace/crudGenerator例如
	 */
	private String projectPath;//TODO custom
	
	/**
	 * "E:/workspace/crudGenerator/src/main/java/com/wisedu/zzfw/bulletion/web/bulletion.java
	 */
	private String filePath;
	
	private String fileName;
	
	/**
	 * 例如 TemplateController.java
	 */
	private String templateName;
}
