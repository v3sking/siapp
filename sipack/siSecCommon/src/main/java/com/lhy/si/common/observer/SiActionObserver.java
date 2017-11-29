package com.lhy.si.common.observer;

import java.util.Observable;
import java.util.Observer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.lhy.si.auth.token.manager.TokenManager;
import com.lhy.si.auth.token.manager.impl.RaFilterJwtTokenManagerImpl;
import com.lhy.si.common.http.SimpleRestTemplateFactory;
import com.lhy.si.common.util.SpringUtil;
import com.lhy.si.sec.user.model.SecUser;

public class SiActionObserver implements Observer {
	
	private String notifyUrl;
	
	private String sysCode;
	
	private String reason;
	
	public SiActionObserver() {
		super();
	}

	public SiActionObserver(String notifyUrl, String sysCode, String reason) {
		super();
		this.notifyUrl = notifyUrl;
		this.sysCode = sysCode;
		this.reason = reason;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO:调用通知接口
		SimpleRestTemplateFactory bean = SpringUtil.getBean(SimpleRestTemplateFactory.class);
		TokenManager tokenManager = SpringUtil.getBean(RaFilterJwtTokenManagerImpl.class);
		MultiValueMap<String, Object>  params = new LinkedMultiValueMap<String, Object>();
		params.add("obj", arg);
		HttpHeaders header = new HttpHeaders();
		header.add("token", tokenManager.createToken("sysAdmin").getToken());
		bean.exchange(this.notifyUrl, HttpMethod.POST, params, header);
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	

}
