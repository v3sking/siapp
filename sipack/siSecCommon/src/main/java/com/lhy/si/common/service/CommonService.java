package com.lhy.si.common.service;

import java.util.List;

public interface CommonService<T> {
	
	public T selectOne(T record);
	public List<T> select(T record);
	public List<T> selectAll();
	public int selectCount(T record);
	public T selectByPrimaryKey(Object key);
	public boolean existsWithPrimaryKey(Object key);
	public int insertSelective(T record);
	public int updateByPrimaryKey(T record);
	public int delete(T record) ;
	public int deleteByPrimaryKey(Object key) ;
	public int updateByPrimaryKeySelective(T record);
}
