package com.lhy.si.auth.token;

import java.io.Serializable;

public class StatelessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userCode;
	
	private String token;
	
	public StatelessToken(String userCode, String token){
		this.userCode = userCode;
		this.token = token;
	}
	

	public Object getPrincipal() {
		return userCode;
	}

	public Object getCredentials() {
		return token;
	}
	
	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}


}
