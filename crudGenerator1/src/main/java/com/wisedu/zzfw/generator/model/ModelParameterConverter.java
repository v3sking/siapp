package com.wisedu.zzfw.generator.model;

import java.util.Map;

import com.wisedu.zzfw.generator.model.entity.ConfigEntity;

/**
 * 
* @ClassName: ModelParameterConverter
* @Description: 将初始化参数转为模板引擎参数
* @author  hyluan
* @date 2018年1月3日 下午4:13:07
* @Copyright: Copyright (c) 2017 wisedu
 */
public interface ModelParameterConverter {
	
	Map<String, Object> converterParameter(Map<String,Object> parametter);

}
