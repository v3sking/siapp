package com.wisedu.zzfw.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.SneakyThrows;

public class ReflectionUtil {
	
	public static String getClassApiModelValue(Class<?> modelClass){
		ApiModel annotation = modelClass.getAnnotation(ApiModel.class);
		String value = annotation.value();
		return value;
	}
	
	@SneakyThrows
	public static String getFieldApiModelPropertyValue(Class<?> modelClass,String fieldName){
		Field declaredField = modelClass.getDeclaredField(fieldName);
		ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
		return annotation.value();
	}
	
}
