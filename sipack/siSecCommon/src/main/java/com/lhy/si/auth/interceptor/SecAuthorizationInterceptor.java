package com.lhy.si.auth.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lhy.si.auth.util.AuthUtil;
import com.lhy.si.common.exception.SiAuthException;
import com.lhy.si.sec.api.model.SecApi;
import com.lhy.si.sec.api.service.ISecApiService;
import com.lhy.si.sec.role.model.SecRole;
import com.lhy.si.sec.role.model.UrlRole;
import com.lhy.si.sec.role.service.ISecRoleService;

/**
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: SecAuthorizationInterceptor.java
 * @Description: 授权拦截器
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
public class SecAuthorizationInterceptor extends SecAbstractInterceptor {

	@Autowired
	private AuthUtil authUtil;
	
	private ISecRoleService secRoleService;
	
	private ISecApiService secApiService;
	
	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行
	 * ，而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的
	 * ，这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = false;
		if (handler instanceof HandlerMethod) {
			boolean preHandle = super.preHandle(request, response, handler);
			if (preHandle) {
				return true;
			}
			String uri = request.getRequestURI();
			// 过滤地址
			String userId = authUtil.getUserId(request);
			//获取用户角色，
			List<SecRole> roleByUserId = secRoleService.getRoleByUserId(Integer.parseInt(userId));
			//根据角色获取api列表
			for (SecRole secRole : roleByUserId) {
				String roleId = String.valueOf(secRole.getRoleId());
				List<UrlRole> apilist = new ArrayList<UrlRole>();
				List<SecApi> apis = secApiService.getApiByRoleId(secRole.getRoleId());
				for (SecApi secApi : apis) {
					 UrlRole urlRole = new UrlRole(secApi.getApiPath(), roleId);
					 apilist.add(urlRole);
				}
				if (apilist.contains(new UrlRole(uri, roleId))) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				if (isAjax(request)) {
					throw new SiAuthException("用户："+userId+"没有访问api："+uri+"的权限");
				}
//				else{
//					RequestDispatcher rd = request.getRequestDispatcher(secServerUrl+"/authFail.html");
//					rd.forward(request, response);
//					return false;
//				}
			}
			return flag;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	public ISecRoleService getSecRoleService() {
		return secRoleService;
	}

	public void setSecRoleService(ISecRoleService secRoleService) {
		this.secRoleService = secRoleService;
	}

	public ISecApiService getSecApiService() {
		return secApiService;
	}

	public void setSecApiService(ISecApiService secApiService) {
		this.secApiService = secApiService;
	}


}
