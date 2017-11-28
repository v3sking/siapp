package com.lhy.si.common.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.ResponseResultFactory;
import com.wisedu.zzfw.security.exception.ExpiredTokenException;

import io.jsonwebtoken.JwtException;

/**
 * 
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理类
 * @author hyluan
 * @date 2017年7月20日 上午11:48:45
 * @Copyright: Copyright (c) 2017 wisedu
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private ResponseResultFactory responseResultFactory;

	@Autowired
	private HttpServletRequest httpServletRequest;

	/**
	 * 
	 * @Title: exceptionHandler
	 * @Description: hibernate validator异常处理
	 * @return ResponseResult<List<com.wisedu.zzfw.common.model.FieldError>>
	 *         返回类型
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseResult<List<com.lhy.si.common.model.FieldError>> exceptionHandler(BindException e,
			HttpServletResponse response) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<com.lhy.si.common.model.FieldError> l = new ArrayList<com.lhy.si.common.model.FieldError>();
		for (int i = 0; i < fieldErrors.size(); i++) {
			FieldError fieldError = fieldErrors.get(i);
			com.lhy.si.common.model.FieldError fe = new com.lhy.si.common.model.FieldError(
					fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue());
			l.add(fe);
		}
		ResponseResult<List<com.lhy.si.common.model.FieldError>> failObject = responseResultFactory
				.getFailObject(l);
		logger.warn("参数校验失败:{},{}", httpServletRequest.getRequestURI(), failObject.toString(), e);
		return failObject;
	}
	
	/**
	 * 
	* @Title: exceptionHandler
	* @Description:token异常处理
	* @return ResponseResult<Object>    返回类型
	* @param e
	* @param response
	* @return
	 */
	@ExceptionHandler(JwtException.class)
	@ResponseBody
	public ResponseResult<Object> exceptionHandler(JwtException e,
			HttpServletResponse response) {
		logger.error("",e);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ResponseResult<Object> responseResult = responseResultFactory.getFailObject(e);
		if(e instanceof ExpiredTokenException){
			responseResult.setMsg("token expired");
		}else{
			responseResult.setMsg(e.getMessage());
		}
		responseResult.setData(e.toString());
		return responseResult;
	}
	
	/**
	 * 
	* @Title: exceptionHandler
	* @Description:token异常处理
	* @return ResponseResult<Object>    返回类型
	* @param e
	* @param response
	* @return
	 */
	@ExceptionHandler(SiException.class)
	@ResponseBody
	public ResponseResult<Object> exceptionHandler(SiException e,
			HttpServletResponse response) {
		logger.error("",e);
		ResponseResult<Object> responseResult = responseResultFactory.getFailObject(e);
		responseResult.setData(e.toString());
		return responseResult;
	}
}
