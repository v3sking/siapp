package com.lhy.si.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lhy.si.common.util.NatureObjectMapper;

/**
 * 
* @ClassName: JsonMapper
* @Description: null替换为空字符串
* @author  hyluan
* @date 2017年7月12日 下午2:39:13
* @Copyright: Copyright (c) 2017 wisedu
 */
public class NullToEmptyMapper extends NatureObjectMapper {

	private static final long serialVersionUID = 1L;

	public NullToEmptyMapper() {
		// this(Include.NON_EMPTY);
		// 空值处理为空串
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
					throws IOException, JsonProcessingException {
				jgen.writeString("");
			}
		});
	}

}