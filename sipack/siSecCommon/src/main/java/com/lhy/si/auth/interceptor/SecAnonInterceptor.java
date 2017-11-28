package com.lhy.si.auth.interceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lhy.si.common.util.JacksonUtil;

/**
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: SecAnonInterceptor.java
 * @Description: anonymous拦截器
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
public class SecAnonInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(SecAnonInterceptor.class);

	@Autowired
	private ServerProperties serverProperties;

	/**
	 * 不拦截的资源路径
	 */
	protected Set<String> anonPaths = new HashSet<String>();
	
	@Value("${si.sec.annoPaths:/user/register,/user/login}")
	protected String annoPaths;
	
	/**
	 * 初始化无需拦截路径
	 */
	@PostConstruct
	public void init(){
		String[] split = annoPaths.split(",");
		anonPaths.addAll(Arrays.asList(split));
		anonPaths.add("/");
		anonPaths.add("/resource/**");
		anonPaths.add("/favicon.ico");
		anonPaths.add("/error/**");
		anonPaths.add("/error");
		
		
		anonPaths.add("/role/getByUserId");
		anonPaths.add("/api/getByRoleId");
		anonPaths.add("/user/get");

		anonPaths.add("/login/**");
		anonPaths.add("/login");
		anonPaths.add("/login.html");
		anonPaths.add("/index/**");
		anonPaths.add("/index");
		// --------------------swagger调试接口用----------------begin
		anonPaths.add("/swagger-ui.html");
		anonPaths.add("/webjars/**");
		anonPaths.add("/swagger-resources/**");
		anonPaths.add("/v2/**");
		logger.debug("不拦截的资源路径：{}",JacksonUtil.toJSon(anonPaths));
	}
	
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
			//过滤地址
			PathMatcher matcher = new AntPathMatcher();
			String url = request.getRequestURL().toString();
			logger.info("拦截到的url:" + url);
			String uri = request.getRequestURI();
			String contextPath = serverProperties.getContextPath();
			if(!StringUtils.isEmpty(serverProperties.getContextPath()) && uri.startsWith(contextPath)){
				uri =uri.replace(contextPath, "");
			}
			for (String anonPath : this.anonPaths) {
				boolean result = matcher.match(anonPath, uri);
				if (result) {
					request.setAttribute("anonPathFlag", true);
					return true;	
				}
			}
			request.setAttribute("anonPathFlag", false);
			return true;
		}
		request.setAttribute("anonPathFlag", true);
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

	protected boolean filterAnonPaths(HttpServletRequest request){
		//过滤地址
		PathMatcher matcher = new AntPathMatcher();
		String url = request.getRequestURL().toString();
		logger.info("拦截到的url:" + url);
		String uri = request.getRequestURI();
		String contextPath = serverProperties.getContextPath();
		if(!StringUtils.isEmpty(serverProperties.getContextPath()) && uri.startsWith(contextPath)){
			uri = uri.replace(contextPath, "");
		}
		for (String anonPath : this.anonPaths) {
			boolean result = matcher.match(anonPath, uri);
			if (result) {
				return true;	
			}
		}
		return false;
	}
	

}
