package com.wisedu.zzfw.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.wisedu.zzfw.GeneratorProperties.ModelAttributes;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
public class BeanModel {
	
	private Class<?> beanClass;
	
	private String beanDescription;
	
	private String beanSimpleName;
	
	private String beanFullName;
	
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	
	private ModelAttributes modelAttributes = new ModelAttributes();
	
	public BeanModel(Class<?> beanClass, ModelAttributes modelAttributes) {
		this.beanClass = beanClass;
		this.beanSimpleName = beanClass.getSimpleName();
		this.beanFullName = beanClass.getName();
		this.modelAttributes = modelAttributes;
		ApiModel annotation = beanClass.getAnnotation(ApiModel.class);
		if (annotation != null) {
			String value = annotation.value();
			this.beanDescription = value;
		}
		
		Field[] declaredFields = beanClass.getDeclaredFields();
		for (Field field : declaredFields) {
			if(!Modifier.isStatic(field.getModifiers())){	//判断是否静态属性
				columns.add(new ColumnModel(field));
			}
		}
	}

}
