package com.lhy.si.common.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: StringUtils.java
 * @Description:String工具类
 * 
 * @version: v1.0.0
 * @author: hyluan
 * @date: 2017年4月25日 下午4:31:11
 * 
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2017年4月25日
 *        hyluan v1.0.0 修改原因
 */
public class StringUtils {

	public static String trimCNLength(String orignal, int totalLenth, String charset) {
		if (orignal == null) {
			return "";
		}
		try {
			if (orignal.getBytes(charset).length <= totalLenth) {
				return orignal;
			} else {
				StringBuilder sb = new StringBuilder();
				int length = 0;
				for (int i = 0; i < orignal.length(); i++) {
					String ss = String.valueOf(orignal.charAt(i));
					length = Pattern.matches("[^\\x00-\\xff]", ss) ? length + 3 : length + 1;
					if (length <= totalLenth) {
						sb.append(ss);
					} else {
						break;
					}
				}
				return sb.toString();
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
