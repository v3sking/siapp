package com.lhy.si.common.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpAddressUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(IpAddressUtil.class);
	
	public static String localhost = "0:0:0:0:0:0:0:1";
	public static String reservedAddress = "127.0.0.1";

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null) {
			if (ip.indexOf(',') == -1) {
				return ip;
			}
			return ip.split(",")[0];
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();

		}
		return ip;
	}
	
	/**
	 * 从url中获取ip地址 没有ip地址获取域名
	 * @param url
	 * @return
	 */
	public static String getIpDomainByUrl(String url){
		String ipOrDomain = "";
		try {
			if (url == null || "".equals(url)) {
				return "";
			}
			String string = url.split("//")[1];
			String split = string.split("/")[0];
			if (split.indexOf(":") > 0) {
				ipOrDomain = split.split(":")[0];
			}else{
				ipOrDomain = split;
			}
		} catch (Exception e) {
			logger.error("url地址{}不合法",url,e);
		}
		return ipOrDomain;
	}
	
}
