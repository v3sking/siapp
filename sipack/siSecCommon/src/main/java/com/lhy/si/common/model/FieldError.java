package com.lhy.si.common.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: FieldError
* @Description: 字段错误
* @author  hyluan
* @date 2017年7月20日 下午12:35:35
* @Copyright: Copyright (c) 2017 wisedu
 */
@ApiModel(value="FieldError（字段错误）")
public class FieldError implements Serializable{

	/**
	* @Fields serialVersionUID :
	*/
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "字段名", notes = "字段异常", required = true)
	private String fieldName;
	
	@ApiModelProperty(value = "错误原因", notes = "错误原因", required = true)
	private String errMsg;
	
	@ApiModelProperty(value = "错误值", notes = "错误值", required = true)
	private Object rejectedValue;
	
	public FieldError() {
		super();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public FieldError(String fieldName, String errMsg, Object rejectedValue) {
		super();
		this.fieldName = fieldName;
		this.errMsg = errMsg;
		this.rejectedValue = rejectedValue;
	}

	@Override
	public String toString() {
		return "FieldError [fieldName=" + fieldName + ", errMsg=" + errMsg + ", rejectedValue=" + rejectedValue + "]";
	}


}
