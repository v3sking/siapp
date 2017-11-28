package com.lhy.si.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhy.si.common.model.ResponseList;

/**  
 * Copyright: Copyright (c) 2016 zznode
 * 
 * @ClassName: JacksonUtil.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: luanhy
 * @date: 2016年2月2日 下午1:39:04 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2016年2月2日     luanhy           v1.0.0               修改原因
 */
/**
 * The class JacksonUtil
 *
 * json字符与对像转换 
 * 
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public final class JacksonUtil {
	
	private static Logger logger = Logger.getLogger(JacksonUtil.class);

	private static ObjectMapper objectMapper = new NatureObjectMapper();
	
	private static ObjectMapper nullToEmptyMapper = new NullToEmptyMapper();

	
	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,如List<Student>,将第二个参数传递为Student
	 * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static JsonNode readValue(String jsonStr) {
		try {
			return objectMapper.readTree(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json数组转List
	 * @param jsonStr
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
		try {
			return (T)objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 把JavaBean转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSon(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 把JavaBean转换为json字符串 null替换为空字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSonNullToEmptyStr(Object object) {
		try {
			return nullToEmptyMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> ResponseList<T> getTableJSON(long total, List<T> list){
		ResponseList<T> l = new ResponseList<T>();
    	try {
			if (list == null || list.size() == 0) {
				l.setTotal(0);
				l.setRows(new ArrayList<T>());
				logger.info("getTableJSON:List对象为空或长度为0");
			}
			l.setTotal(total);
			l.setRows(list);
		} catch (Exception e) {
			logger.error(e,e);
		}
		return l;
	}

}
