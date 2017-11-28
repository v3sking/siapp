package com.lhy.si.auth.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lhy.si.common.model.ResponseResult;

/**
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: SecAbstractInterceptor.java
 * @Description: 抽象拦截器
 * 
 * @version: v1.0.0
 * @author: hyluan
 * @date: 2017年3月31日 下午4:35:42
 * 
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2017年3月31日
 *        hyluan v1.0.0 修改原因
 */
@Component
public abstract class SecAbstractInterceptor implements HandlerInterceptor {

	protected final Logger logger = LoggerFactory.getLogger(SecAbstractInterceptor.class);

	@Value("${si.serServer.url:http://127.0.0.1:8080/secServer}")
	protected String secServerUrl;
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("secServerUrl", secServerUrl);
		Object attribute = request.getAttribute("anonPathFlag");
		if (attribute!= null && (boolean)attribute) {
			return true;
		}else{
			return false;
		}
	}
	
	protected boolean isAjax(HttpServletRequest request){
		return !StringUtils.isEmpty(request.getHeader("X-Requested-With"));
		
	}
	
	/**
	 * 输出json响应
	 * 
	 * @param response
	 * @param resObj
	 * @throws IOException
	 */
	protected void response(HttpServletResponse response, ResponseResult<Object> failObject)
			throws IOException {
//		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().print(resObj.toString());
	}

}
