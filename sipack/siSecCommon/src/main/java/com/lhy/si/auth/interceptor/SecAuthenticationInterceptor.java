package com.lhy.si.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lhy.si.auth.token.StatelessToken;
import com.lhy.si.auth.token.manager.TokenManager;
import com.lhy.si.auth.util.AuthUtil;
import com.lhy.si.common.exception.SiException;
import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.RequestUtil;
import com.lhy.si.common.util.ResponseResultFactory;
import com.lhy.si.sec.user.SecUserEnum;
import com.lhy.si.sec.user.model.SecUser;
import com.lhy.si.sec.user.service.ISecUserService;
import com.wisedu.zzfw.security.exception.IllegalTokenException;

/**
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: SecAuthenticationInterceptor.java
 * @Description: 认证拦截器
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
public class SecAuthenticationInterceptor extends SecAbstractInterceptor {

	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
	private ISecUserService secUserService;
	
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
		if (handler instanceof HandlerMethod) {
			if (super.preHandle(request, response, handler)) {
				return true;
			}
			try{
				checkToken(request, response);
			}catch(Exception e){
				logger.error("认证不通过",e);
				if (isAjax(request)) {
					throw e;
				}
//				else{
//					String refer = request.getRequestURL().toString();
//					RequestDispatcher rd = request.getRequestDispatcher(secServerUrl+"/login.html?refer="+refer);
//					rd.forward(request, response);
//					return false;
//				}
			}
		}
		return true;
	}
	
	private boolean checkToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseResult<Object> responseResult = responseResultFactory.getSuccessObject();
		// 前端token授权信息放在请求头中传入
		String rerfer = request.getRequestURL().toString();
		request.setAttribute("rerfer", rerfer);
		String token = RequestUtil.newInstance().getRequestHeader(request,"token");
		if (StringUtils.isEmpty(token)||"null".equals(token)) {
			token = request.getParameter("token");
		}
		if (StringUtils.isEmpty(token)) {
			responseResult.setMsg("请求头不包含认证信息token");
			throw new IllegalTokenException("请求头不包含认证信息token");
		}
		// 获取无状态Token
		StatelessToken accessToken = tokenManager.getToken(token);
		//如果请求体参数userId为空则从token中获取
		String userId = (String) accessToken.getPrincipal();
		logger.info("从请求头token中获取到userId:{}",userId);
		request.setAttribute(AuthUtil.userId, userId);
		//检查用户是否存在
		checkUserExists(userId);
		//检查token是否合法
		return tokenManager.checkToken(accessToken);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private void checkUserExists(String userId) throws Exception {
		if(StringUtils.isEmpty(userId)){
			throw new SiException("请求头不含有参数：用户id{userId}");
		}
		SecUser user = secUserService.getUser(Integer.valueOf(userId));
		if (user == null) {
			throw new SiException("userId " + userId + "用户不存在");
		}
		if (user.getStatus() == null || SecUserEnum.normal.getStatus() != (user.getStatus())) {
			throw new SiException("userId " + userId + "状态异常："+SecUserEnum.getStatusName(user.getStatus()));
		}
	}

	public ISecUserService getSecUserService() {
		return secUserService;
	}

	public void setSecUserService(ISecUserService secUserService) {
		this.secUserService = secUserService;
	}
	
	

}
