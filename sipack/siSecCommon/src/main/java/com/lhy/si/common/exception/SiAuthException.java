package com.lhy.si.common.exception;

/**
* @Title: SiAuthException.java
* @Description: spring 默认继承RuntimeException事务才能回滚
* @author mt
* @date 2017年7月3日 上午8:56:51
* @version V1.0
* @tags 
*/

public class SiAuthException extends RuntimeException {
	/**
	* @Fields serialVersionUID 
	*/
	private static final long serialVersionUID = 1L;

	public SiAuthException(String message) {
		super(message);
	}

	public SiAuthException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SiAuthException(Throwable cause) {
		super(cause);
	}
}
