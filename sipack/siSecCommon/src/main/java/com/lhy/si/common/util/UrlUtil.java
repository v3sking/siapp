package com.lhy.si.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: UrlUtil.java
 * @Description: 该类的功能描述
 * 
 * @version: v1.0.0
 * @author: hyluan
 * @date: 2017年4月14日 下午5:14:57
 * 
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2017年4月14日
 *        hyluan v1.0.0 修改原因
 */
public class UrlUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(UrlUtil.class);

	public static String urlPage(String strURL) {
		String strPage = null;
		String[] arrSplit = null;

		strURL = strURL.trim().toLowerCase();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 0) {
			if (arrSplit.length > 1) {
				if (arrSplit[0] != null) {
					strPage = arrSplit[0];
				}
			}
		}

		return strPage;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String truncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;

		strURL = strURL.trim();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}

		return strAllParam;
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> urlRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		if(URL == null){
			return mapRequest;
		}
		String[] arrSplit = null;

		String strUrlParam = truncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组 www.2cto.com
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 
	 * @Function: UrlUtil::uri
	 * @Description: 获取uri
	 * @param url
	 * @return
	 * @version: v1.0.0
	 * @author: hyluan
	 * @date: 2017年4月14日 下午5:21:48
	 *
	 *        Modification History: Date Author Version Description
	 *        -------------------------------------------------------------
	 */
	public static String uri(String url) {
		if (url == null || "".equals(url)) {
			return null;
		}
		if (url.indexOf("?") < 0) {
			return url;
		} else {
			return url.split("\\?")[0];
		}
	}
	
	/**
	 * 
	* @Title: getRequestPathWithSortedParams
	* @Description: : 获取uri+排序的请求参数
	* @param url
	* @return
	 */
	public static String getRequestPathWithSortedParams(String url) {
		String uri = uri(url);
		Map<String, String> params = urlRequest(url);
		return uri + sortParams(params);

	}
	
	/**
	 * 
	* @Title: getRequestURIWithSortedParams
	* @Description: 获取uri+排序的请求参数
	* @param request
	* @return
	 */
	public static String getRequestURIWithSortedParams(HttpServletRequest request) {
		Map<String, String> params = getParams(request);
		return request.getRequestURI() + sortParams(params);
	}
	
	/**
	 * 
	* @Title: getRequestURIWithSortedParams
	* @Description: 获取uri+排序的请求参数
	* @param request
	* @return
	 */
	public static String getRequestURIWithParams(HttpServletRequest request) {
		Map<String, String> params = getParams(request);
		return request.getRequestURI() + sortParams(params);
	}
	
	/**
	 * 
	* @Title: sortParams
	* @Description: 排序参数 拼接成字符串
	* @param params
	* @return
	 */
	public static String sortParams(Map<String, String> params) {
		return paramsToString(params, true);
	}
	
	/**
	 * 
	* @Title: noSortParams
	* @Description: 参数 拼接成字符串
	* @param params
	* @return
	 */
	public static String noSortParams(Map<String, String> params) {
		return paramsToString(params, false);
	}
	
	private static String paramsToString(Map<String, String> params,boolean isSort){
		Set<String> set = null;
		if(isSort){
			set = new TreeSet<String>(params.keySet());
		}else{
			set = params.keySet();
		}
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (String key : set) {
			if (index++ == 0) {
				sb.append("?");
			} else {
				sb.append("&");
			}
			sb.append(key).append("=").append(params.get(key));
		}
		try {
			return URLEncoder.encode(sb.toString(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取get和post提交的参数
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParams(HttpServletRequest request) {
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, String> params = new HashMap<String, String>();

		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			params.put(name, request.getParameter(name));
		}
		return params;
	}

}
