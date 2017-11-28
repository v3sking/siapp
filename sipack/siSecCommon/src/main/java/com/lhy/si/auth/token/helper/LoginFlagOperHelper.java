package com.lhy.si.auth.token.helper;


/**
 * 用户登陆标志操作帮助类
 * @author Administrator
 *
 */
public interface LoginFlagOperHelper {
	
	public String getAndSetLoginFlag(String tokenKey);
	
	
	public void setLoginFlagExpiration(String tokenKey,long minutes);
	
}
