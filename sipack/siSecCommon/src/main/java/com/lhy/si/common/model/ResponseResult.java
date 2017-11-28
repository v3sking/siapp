package com.lhy.si.common.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
**
* 
* @ClassName: ResponseResult
* @Description: 响应结果
* @author luanhy
* @param <O> 返回的实体对象
* @date 2017年7月7日 下午5:00:16
* @Copyright: Copyright (c) 2017 wisedu
*/
@ApiModel(value="ResponseResult（响应结果）",description="数据")
public class ResponseResult<OutputParam> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String SUCCESS = "0";
	public static final String FAIL = "1";

	/**
	 * 0 成功 1失败
	 */
	@ApiModelProperty(value = "返回结果:0 成功 1失败", notes = " 0 成功 1失败", required = true)
	private String ret;

	@ApiModelProperty(value = "返回消息")
	private String msg;
	
	@ApiModelProperty(value = "返回的业务数据")
	private OutputParam data;

	public ResponseResult() {
		super();
	}
	
	public ResponseResult(String ret, String msg) {
		super();
		this.ret = ret;
		this.msg = msg;
	}
	
	public ResponseResult(String ret, String msg,OutputParam data) {
		super();
		this.ret = ret;
		this.msg = msg;
		this.data = data;
	}

	public String getRet() {
		return ret;
	}

	public ResponseResult<OutputParam> setRet(String ret) {
		this.ret = ret;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseResult<OutputParam> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public OutputParam getData() {
		return data;
	}

	public ResponseResult<OutputParam> setData(OutputParam data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return "ResponseResult [ret=" + ret + ", msg=" + msg + ", data=" + data + "]";
	}

	
}
