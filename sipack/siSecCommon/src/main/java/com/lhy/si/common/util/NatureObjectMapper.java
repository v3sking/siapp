package com.lhy.si.common.util;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 日期格式化 属性自然名格式
 * @author Administrator
 *
 */
public class NatureObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(NatureObjectMapper.class);
	
	public NatureObjectMapper() {
		super();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		this.setDateFormat(fmt); 
	}
	
	@Override
	public String writeValueAsString(Object value)
			throws JsonProcessingException {
		String writeValueAsString = super.writeValueAsString(value);
		logger.info("jsonStr:" + value + "--->" + writeValueAsString);
		return writeValueAsString;
	}
}
