package com.lhy.si.auth.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhy.si.sec.user.service.SecUserService;

@Component
public class AuthUtil {
	
	@Autowired
	private HttpServletRequest request;
	
	public static final String userId ="userId";
	
	/**
	 * 从当前request中获取用户id 获取请求参数中的userId，如果为空请求头token中的userId
	 * @return
	 */
	public String getUserId(){
		return getUserId(request);
	}
	
	/**
	 * 从指定requet中获取userId 多线程时使用
	 * @param request 需要从主线程中传入request参数到子线程
	 * @return
	 */
	public String getUserId(HttpServletRequest request){
		Object attribute = request.getAttribute(userId);
		if (attribute != null) {
			return attribute.toString();
		}
		return "";
	}
	
 	
}
