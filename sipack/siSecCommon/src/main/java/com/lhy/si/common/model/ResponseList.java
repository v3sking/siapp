package com.lhy.si.common.model;

import java.io.Serializable;
import java.util.List;

/**  
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: ResponseList.java
 * @Description: 分页查询返回类
 *
 * @version: v1.0.0
 * @author: hyluan
 * @date: 2017年5月10日 下午4:38:05 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2017年5月10日     hyluan           v1.0.0               修改原因
 */
public class ResponseList<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long total;
	private List<T> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
