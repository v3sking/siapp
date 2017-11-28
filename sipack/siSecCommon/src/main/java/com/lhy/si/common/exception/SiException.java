package com.lhy.si.common.exception;

/**
* @Title: SiException.java
* @Description: spring 默认继承RuntimeException事务才能回滚
* @author mt
* @date 2017年7月3日 上午8:56:51
* @version V1.0
* @tags 
*/

public class SiException extends RuntimeException {
	/**
	* @Fields serialVersionUID 
	*/
	private static final long serialVersionUID = 1L;

	public SiException(String message) {
		super(message);
	}

	public SiException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SiException(Throwable cause) {
		super(cause);
	}
}
