package com.wisedu.zzfw.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import com.wisedu.zzfw.util.StringUtil;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ColumnModel {
	
	private Field field;
	
	private boolean primary;
	
	private String columnName;
	
	private String columnDesc;
	
	private String columnNameDx;
	
	private String dbColumnName;
	
	private boolean canNull;
	
	private Class<?> columnType;
	
	public ColumnModel(Field field){
		this.field = field;
		
		Id annotation = field.getAnnotation(Id.class);
		if (annotation != null) {
			primary = true;
		}
		
		columnName = field.getName();
		
		ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
		Column column = field.getAnnotation(Column.class);
		dbColumnName = column.name();
		if (apiModelProperty != null) {
			if(!StringUtils.isEmpty(apiModelProperty.value())){
				columnDesc = apiModelProperty.value();
			}else{
				columnDesc = column.name();
			}
			canNull = !apiModelProperty.required();
		}
		columnNameDx = StringUtil.firstToUpperCase(columnName);
		columnType = field.getType();
	}
	
	
	
}
