package com.lhy.si.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lhy.si.auth.interceptor.SecAnonInterceptor;
import com.lhy.si.auth.interceptor.SecAuthenticationInterceptor;
import com.lhy.si.auth.interceptor.SecAuthorizationInterceptor;
import com.lhy.si.common.util.SpringUtil;
import com.lhy.si.sec.api.service.ISecApiService;
import com.lhy.si.sec.api.service.SecApiService;
import com.lhy.si.sec.api.service.SecApiServiceRemote;
import com.lhy.si.sec.role.service.ISecRoleService;
import com.lhy.si.sec.role.service.SecRoleService;
import com.lhy.si.sec.role.service.SecRoleServiceRemote;
import com.lhy.si.sec.user.service.ISecUserService;
import com.lhy.si.sec.user.service.SecUserService;
import com.lhy.si.sec.user.service.SecUserServiceRemote;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private SecAnonInterceptor secAnonInterceptor;
	
	@Autowired
	private SecAuthenticationInterceptor secAuthenticationInterceptor;
	
	@Autowired
	private SecAuthorizationInterceptor secAuthorizationInterceptor;
	
	@Autowired
	private ServerProperties serverProperties;
	
    /**{@inheritDoc}
     * </br>注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	ISecApiService secApiService = null;
    	ISecRoleService secRoleService = null;
    	ISecUserService secUserService = null;

    	if ("/secServer".equals(serverProperties.getContextPath())) {
    		secApiService = SpringUtil.getBean(SecApiService.class);
    		secRoleService = SpringUtil.getBean(SecRoleService.class);
    		secUserService = SpringUtil.getBean(SecUserService.class);
		}else{
			secApiService = SpringUtil.getBean(SecApiServiceRemote.class);
    		secRoleService = SpringUtil.getBean(SecRoleServiceRemote.class);
    		secUserService = SpringUtil.getBean(SecUserServiceRemote.class);
		}
    	secAuthorizationInterceptor.setSecApiService(secApiService);
    	secAuthorizationInterceptor.setSecRoleService(secRoleService);
    	
    	secAuthenticationInterceptor.setSecUserService(secUserService);
    	
        registry.addInterceptor(secAnonInterceptor);
        registry.addInterceptor(secAuthenticationInterceptor);
        registry.addInterceptor(secAuthorizationInterceptor);
    }
    
    /**
     * {@inheritDoc}
     * </br>注册controller映射
     * 如果有相同注解controller 以注解优先
     */
    @Override  
    public void addViewControllers(ViewControllerRegistry registry) { 
        registry.addViewController("/index").setViewName("index"); 
        registry.addViewController("/login").setViewName("login"); 
        registry.addViewController("/authFail").setViewName("authFail"); 
        registry.addViewController("/content").setViewName("content"); 
        registry.setOrder(0);  
    }
    
    /**
     * {@inheritDoc}
     */
    @Override  
    public void configurePathMatch(PathMatchConfigurer configurer) {  
        super.configurePathMatch(configurer);  
        configurer.setUseSuffixPatternMatch(false);  
    }

}
