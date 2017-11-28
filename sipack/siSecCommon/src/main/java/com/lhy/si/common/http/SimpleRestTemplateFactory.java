package com.lhy.si.common.http;

import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.lhy.si.common.exception.SiException;
import com.lhy.si.common.util.JacksonUtil;

/**
 * 
* @ClassName: SimpleRestTemplateFactory
* @Description:简单restTemplate工厂
* @author  hyluan
* @date 2017年8月10日 下午1:43:39
* @Copyright: Copyright (c) 2017 wisedu
 */
@Component
@Scope(scopeName="singleton")
public class SimpleRestTemplateFactory {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	private void init(){
		this.restTemplate = new RestTemplate();
	}
	
	public RestTemplate getRestTemplate(){
		return restTemplate;
	}
	
	/*
	 * 获取访问地址链接返回值
	 */
	public JsonNode exchage(String url) {
		return exchange(url,null,null,null);
	}
	
	/*
	 * 获取访问地址链接返回值
	 */
	public JsonNode exchange(String url,HttpMethod method,MultiValueMap<String, Object> params,HttpHeaders header) {
		logger.info("restTemplate开始请求url：{}",url);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(params, header);
		ResponseEntity<byte[]> exchange = restTemplate.exchange(url, method, httpEntity, byte[].class);
		byte[] body = exchange.getBody();
		String resString;
		try {
			resString = new String(body,"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		logger.info("restTemplate返回数据：{}",resString);
		if(StringUtils.isEmpty(resString)){
			throw new SiException("接口调用返回结果为空");
		}
		return JacksonUtil.readValue(resString);
	}
	
	/*
	 * 获取访问地址链接返回值
	 */
	public String exchangeStr(String url,HttpMethod method,MultiValueMap<String, Object> params,HttpHeaders header) {
		logger.info("restTemplate开始请求url：{}",url);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(params, header);
		ResponseEntity<byte[]> exchange = restTemplate.exchange(url, method, httpEntity, byte[].class);
		byte[] body = exchange.getBody();
		String resString;
		try {
			resString = new String(body,"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		logger.info("restTemplate返回数据：{}",resString);
		if(StringUtils.isEmpty(resString)){
			throw new SiException("接口调用返回结果为空");
		}
		return resString;
	}
}
