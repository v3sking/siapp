//package com.lhy.si.auth.token.helper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;
//
//@Component
//public class EhCacheLoginFlagHelper implements LoginFlagOperHelper {
//	
//	@Autowired
//	private CacheManager cacheManager;
//	
//	private String userLoginCacheName ="userLoginCache";
//	
//	@Override
//	public String getAndSetLoginFlag(String tokenKey) {
//		Cache cache = cacheManager.getCache(userLoginCacheName);
//		Element element = cache.get(tokenKey);
//		if(element == null){
//			Element e = new Element(tokenKey, "im alive");
//			cache.put(e);
//			return null;
//		}else{
//			Object objectValue = element.getObjectValue();
//			if(objectValue == null){
//				return null;
//			}else{
//				return (String)objectValue;
//			}
//		}
//	}
//
//	@Override
//	public void setLoginFlagExpiration(String tokenKey, long minutes) {
//		Cache cache = cacheManager.getCache(userLoginCacheName);
//		Element element = cache.get(tokenKey);
//		if(element != null){
//			element.setTimeToLive(new Long(minutes).intValue());
//		}
//
//	}
//	
//	public CacheManager getCacheManager() {
//		return cacheManager;
//	}
//
//	public void setCacheManager(CacheManager cacheManager) {
//		this.cacheManager = cacheManager;
//	}
//	
//	public String getUserLoginCacheName() {
//		return userLoginCacheName;
//	}
//
//	public void setUserLoginCacheName(String userLoginCacheName) {
//		this.userLoginCacheName = userLoginCacheName;
//	}
//
//}
