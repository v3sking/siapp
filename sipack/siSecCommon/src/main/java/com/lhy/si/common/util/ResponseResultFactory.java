package com.lhy.si.common.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lhy.si.common.model.FieldError;
import com.lhy.si.common.model.ResponseResult;

/**
 * 
* @ClassName: ResponseResultFactory
* @Description: 返回结果工厂类
* @author  hyluan
* @date 2017年9月29日 上午10:47:54
* @Copyright: Copyright (c) 2017 wisedu
 */
@Component
public class ResponseResultFactory {

	public <OutputParam> ResponseResult<OutputParam> getSuccessObject(){
		return getSuccessObject(null, null);
	}
	
	/**
	 * 
	* @Title: getSuccessObject
	* @Description: 
	* @return ResponseResult<OutputParam>    返回类型
	* @param records 新增 更新 删除 返回记录数
	* @return
	 */
	public <OutputParam> ResponseResult<OutputParam> getSuccessObject(int records){
		if(records> 0){
			return getSuccessObject();
		}else{
			return getFailObject("本次操作修改记录数为0");
		}
	}
	
	public <OutputParam> ResponseResult<OutputParam> getSuccessObject(OutputParam data){
		return getSuccessObject(null, data);
	}
	
	public <OutputParam> ResponseResult<OutputParam> getSuccessObject(String msg, OutputParam data){
		return new ResponseResult<OutputParam>(ResponseResult.SUCCESS, msg, data);
	}
	
	public <OutputParam> ResponseResult<OutputParam> getFailObject(String failMsg){
		return new ResponseResult<OutputParam>(ResponseResult.FAIL, failMsg);
	}
	
	public <OutputParam> ResponseResult<OutputParam> getFailObject(Exception e){
		return getFailObject("api接口调用失败:"+e.getMessage());
	}
	
	public ResponseResult<List<FieldError>> getFailObject(List<FieldError> list){
		ResponseResult<List<FieldError>> failObject = getFailObject("api接口调用失败:参数校验失败");
		failObject.setData(list);
		return failObject;
	}
	
}
