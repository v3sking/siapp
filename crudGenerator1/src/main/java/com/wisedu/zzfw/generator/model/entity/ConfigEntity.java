package com.wisedu.zzfw.generator.model.entity;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;
import com.wisedu.zzfw.model.BeanModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: InitializeParametter
* @Description: 获取的初始化配置参数 
* @author  hyluan
* @date 2018年1月3日 下午4:12:33
* @Copyright: Copyright (c) 2017 wisedu
 */
@Getter
@Setter
public class ConfigEntity {
	
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
	 * 例如 TemplateController.java
	 */
	private String templateName;
	
}
