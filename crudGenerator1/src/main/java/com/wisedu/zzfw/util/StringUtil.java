package com.wisedu.zzfw.util;

import org.springframework.util.StringUtils;

public class StringUtil {

	public static String firstToLowerCase(String str){
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		if (str.length() ==1) {
			return str.toLowerCase();
		}else{
			return str.substring(0, 1).toLowerCase()+str.substring(1);
		}
	}
	
	public static String firstToUpperCase(String str){
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		if (str.length() ==1) {
			return str.toUpperCase();
		}else{
			return str.substring(0, 1).toUpperCase()+str.substring(1);
		}
	}	
}
