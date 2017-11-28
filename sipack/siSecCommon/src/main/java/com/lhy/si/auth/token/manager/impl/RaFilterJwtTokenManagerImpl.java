package com.lhy.si.auth.token.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.lhy.si.auth.token.StatelessToken;
import com.lhy.si.auth.token.manager.TokenManager;
import com.lhy.si.common.consts.Consts;
import com.lhy.si.common.util.IpAddressUtil;
import com.lhy.si.common.util.JacksonUtil;
import com.lhy.si.common.util.MacUtil;
import com.wisedu.zzfw.security.exception.ExpiredTokenException;
import com.wisedu.zzfw.security.exception.IllegalTokenException;
import com.wisedu.zzfw.security.exception.InvalidIpException;
import com.wisedu.zzfw.security.util.Base64;
import com.wisedu.zzfw.security.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.lang.Strings;

/**
 * 
 * 采用时间戳 +共享秘钥+黑名单 （类似Zendesk的做法） 防范Replay Attacks的tokenManager
 * 
 * @author Administrator
 *
 */
@Component
public class RaFilterJwtTokenManagerImpl implements TokenManager {
	
	private final Logger logger = LoggerFactory.getLogger(RaFilterJwtTokenManagerImpl.class);
	
	@Value("${auth.token.thirdUserId:"+Consts.SRCREGIONCODE.secServer+"}")
	private String thirdUserId;
	
	@Value("${auth.token.expirateTime:0}")
	private long expireTime;
	
	@Value("${auth.token.profiles:secDefaultPwd}")
	private String profiles;
	
	@Value("${auth.token.validMac:false}")
	private String validMac;
	
	@Autowired(required=false)
	private HttpServletRequest request;
	
	protected JwtUtil jwtUtil = new JwtUtil();
	
	@PostConstruct
	private void init(){
		jwtUtil.setProfiles(profiles);
	}


	@Override
	public StatelessToken getToken(String authentication) {
		if (StringUtils.isEmpty(authentication)) {
			throw new NullPointerException("请求头token为空");
		}
		JsonNode readValue = null;
		try {
			byte[] decode = Base64.decode(authentication.split("\\.")[1]);
			String jsonStr = new String(decode, Strings.UTF_8);
			if(!jsonStr.endsWith("\"}")){
				jsonStr+="\"}";
			}
			readValue = JacksonUtil.readValue(jsonStr);
		} catch (Exception e) {
			throw new IllegalTokenException("获取token异常", e);
		}
		String userCode = readValue.get("aud").textValue();
		return new StatelessToken(userCode, authentication);
	}

	@Override
	public boolean check(String authentication) {
		StatelessToken accessToken = getToken(authentication);
		if (accessToken == null) {
			return false;
		}
		return this.checkToken(accessToken);
	}

	@Override
	public boolean checkToken(StatelessToken accessToken) {
		Claims parseJWT = parseClaims(accessToken);
		return true;
	}

	private Claims parseClaims(StatelessToken accessToken) {
		String token = accessToken.getToken();
		if(StringUtils.isEmpty(token)){
			throw new IllegalTokenException("空的token串：");
		}
		//检查证书
		Claims claims = jwtUtil.parseJWT(token);
		//token失效时间校验
		if(expireTime > 0l){
			long createTime = claims.getIssuedAt().getTime();
			long expireTime = createTime + getExpireTime();
			long now = System.currentTimeMillis();
			logger.info("过期时间："+expireTime);
			logger.info("当前时间："+now);
			//当前时间大于失效时间
			if(expireTime < now){
				throw new ExpiredTokenException("token已经过期");
			}
		}
		if ("true".equals(validMac)) {
			String ipAddr = IpAddressUtil.getIpAddr(request);
			String reqMac = MacUtil.getMacAddress(ipAddr);
			if (!reqMac.equals(claims.get("mac"))) {
				throw new InvalidIpException("mac地址与登陆mac地址不符合");
			}
		}
		return claims;
	}

	@Override
	public StatelessToken createToken(String userId) {
		Map<String,Object> header = new HashMap<String,Object>();
		header.put("typ","JWT");
		header.put("alg", "HS256");
		Claims claims = new DefaultClaims();
		claims.setSubject(Consts.SRCREGIONCODE.secServer)
		.setAudience(userId)
		.setId(UUID.randomUUID().toString().replace("-", ""))
		.setIssuer(getThirdUserId())
		.setIssuedAt(new Date());
		
		if ("true".equals(validMac)) {
			String ipAddr = IpAddressUtil.getIpAddr(request);
			String macAddress = MacUtil.getMacAddress(ipAddr);
			claims.put("mac", macAddress);
		}
		String token = jwtUtil.genJWT(header, claims);
		return new StatelessToken(userId, token);
	}
	
	@Override
	public void deleteToken(String userCode) {
	}

	public String getThirdUserId() {
		return thirdUserId;
	}

	public void setThirdUserId(String thirdUserId) {
		this.thirdUserId = thirdUserId;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	

}
