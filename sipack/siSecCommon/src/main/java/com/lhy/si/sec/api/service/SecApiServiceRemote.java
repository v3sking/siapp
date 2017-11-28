package com.lhy.si.sec.api.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.lhy.si.auth.token.StatelessToken;
import com.lhy.si.auth.token.manager.TokenManager;
import com.lhy.si.auth.util.AuthUtil;
import com.lhy.si.common.http.SimpleRestTemplateFactory;
import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.JacksonUtil;
import com.lhy.si.sec.api.model.SecApi;
import com.lhy.si.sec.role.model.SecRole;

/**
* @ClassName: SecRoleService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 上午11:16:31
* @Copyright: Copyright (c) 2017 wisedu
*/
@Service
public class SecApiServiceRemote implements ISecApiService{
	
	@Autowired
	private SimpleRestTemplateFactory simpleRestTemplateFactory;
	
	@Value("${si.serServer.url:http://127.0.0.1:8080/secServer}")
	private String secServerUrl;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private AuthUtil authUtil;
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	@Cacheable(cacheNames="api",key="#roleId")
	public List<SecApi> getApiByRoleId(int roleId) {
		String url = secServerUrl+"/api/getByRoleId?roleId="+roleId;
		StatelessToken createToken = tokenManager.createToken(authUtil.getUserId());
		HttpHeaders header = new HttpHeaders();
		header.add("token", createToken.getToken());
		JsonNode exchange = simpleRestTemplateFactory.exchange(url, HttpMethod.GET, null, header);
		JsonNode jsonNode = exchange.get("data");
		Iterator<JsonNode> iterator = jsonNode.iterator();
		List<SecApi> l = new ArrayList<SecApi>();
		while (iterator.hasNext()) {
			JsonNode next = iterator.next();
			SecApi readValue = JacksonUtil.readValue(next.toString(), SecApi.class);
			l.add(readValue);
		}
		return l;
	}
}
