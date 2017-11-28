package com.lhy.boot.my_thymeleaf;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.lhy.si.Application;
import com.lhy.si.common.util.JacksonUtil;
import com.lhy.si.sec.user.model.SecUser;
import com.lhy.si.sec.user.service.SecUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class AppTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void test() {
		int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
		System.out.println(beanDefinitionCount);
	}
	
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void test1() throws Exception {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
	
	@Autowired
	private SecUserService secUserService;
	
	@Test
	public void test2() throws Exception {
		secUserService.existsWithPrimaryKey(1);
		SecUser selectByPrimaryKey = secUserService.selectByPrimaryKey(1);
		System.out.println(JacksonUtil.toJSon(selectByPrimaryKey));
		SecUser secUser = new SecUser();
		secUser.setId(1);
		secUser.setEmail("1234555555");
		secUserService.updateByPrimaryKeySelective(secUser);
		SecUser selectByPrimaryKey1 = secUserService.selectByPrimaryKey(1);
		System.out.println(JacksonUtil.toJSon(selectByPrimaryKey1));
    }
	
	@Test
	public void test3() throws Exception {}
		
	
	public static void main(String[] args) {
		RestTemplate r = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzZWNTZXJ2ZXIiLCJhdWQiOiIxIiwianRpIjoiZDdkNDI3M2E4NWI5NGEyZGE4ZjNiMWJkMDI3M2NjODgiLCJpc3MiOiJzZWNTZXJ2ZXIiLCJpYXQiOjE1MTE2NzE2ODl9.oJnkdH1keD8HliK8D0vlXPLGNVcNx-KB3Ijfn5Fibe4");
		HttpEntity<String> httpEntity = new HttpEntity<String>("",header);
		ResponseEntity<byte[]> exchange = r.exchange("http://192.168.0.102:8080/secServer/user/get?id=1", HttpMethod.GET, httpEntity, byte[].class);
		byte[] body = exchange.getBody();
		String resString = null;
		try {
			resString = new String(body,"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		System.out.println(resString);
	}
	
	
}
