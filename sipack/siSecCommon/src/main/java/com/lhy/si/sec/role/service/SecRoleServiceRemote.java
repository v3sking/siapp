package com.lhy.si.sec.role.service;

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
import com.lhy.si.common.http.SimpleRestTemplateFactory;
import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.JacksonUtil;
import com.lhy.si.sec.role.model.SecRole;
import com.lhy.si.sec.user.model.SecUser;

/**
* @ClassName: SecRoleService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 上午11:16:31
* @Copyright: Copyright (c) 2017 wisedu
*/
@Service
public class SecRoleServiceRemote implements ISecRoleService{
	
	@Autowired
	private SimpleRestTemplateFactory simpleRestTemplateFactory;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Value("${si.serServer.url:http://127.0.0.1:8080/secServer}")
	private String secServerUrl;
	
	@Cacheable(cacheNames="role",key="#userId")
	public List<SecRole> getRoleByUserId(int userId){
		String url = secServerUrl+"/role/getByUserId?userId="+userId;
		StatelessToken createToken = tokenManager.createToken(userId+"");
		HttpHeaders header = new HttpHeaders();
		header.add("token", createToken.getToken());
		JsonNode exchange = simpleRestTemplateFactory.exchange(url, HttpMethod.GET, null, header);
		JsonNode jsonNode = exchange.get("data");
		Iterator<JsonNode> iterator = jsonNode.iterator();
		List<SecRole> l = new ArrayList<SecRole>();
		while (iterator.hasNext()) {
			JsonNode next = iterator.next();
			SecRole readValue = JacksonUtil.readValue(next.toString(), SecRole.class);
			l.add(readValue);
		}
		return l;
	}
}
