package com.lhy.si.sec.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.lhy.si.auth.token.manager.TokenManager;
import com.lhy.si.common.http.SimpleRestTemplateFactory;
import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.JacksonUtil;
import com.lhy.si.sec.user.model.SecUser;

/**
* @ClassName: SecRoleService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 上午11:16:31
* @Copyright: Copyright (c) 2017 wisedu
*/
@Service
public class SecUserServiceRemote implements ISecUserService{
	
	@Autowired
	private SimpleRestTemplateFactory simpleRestTemplateFactory;
	
	@Value("${si.serServer.url:http://127.0.0.1:8080/secServer}")
	private String secServerUrl;
	
	@Autowired
	private TokenManager tokenManager;
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	@Cacheable(cacheNames="user",key="#id")
	public SecUser getUser(int id) {
		String url = secServerUrl+"/user/get?id="+id;
		HttpHeaders header = new HttpHeaders();
		header.add("token", tokenManager.createToken(id+"").getToken());
		JsonNode exchange = simpleRestTemplateFactory.exchange(url, HttpMethod.GET, null, header);
		String asText = exchange.get("data").toString();
		return JacksonUtil.readValue(asText, SecUser.class);
	}

	
}
