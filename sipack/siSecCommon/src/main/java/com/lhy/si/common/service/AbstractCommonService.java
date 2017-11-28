package com.lhy.si.common.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tk.mybatis.mapper.common.Mapper;

/**
 * 单表操作
 * Copyright: Copyright (c) 2017 wisedu
 * 
 * @ClassName: AbstractCommonService.java
 * @Description: 通用service类
 * 子类覆盖构造器注入mapper即可使用 例如：
 * @Autowired
	public XszcZpService(@Qualifier("xszcZpMapper") Mapper<XszcZp> mapper) {
		super(mapper);
	}
 *
 * @version: v1.0.0
 * @author: hyluan
 * @date: 2017年4月24日 下午4:03:21 
 *
 * Modification History:
 * Date         Author          Version            Description
 *------------------------------------------------------------
 * 2017年4月24日     hyluan           v1.0.0               修改原因
 */
public abstract class AbstractCommonService<T>{
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private Mapper<T> mapper;
	
	public AbstractCommonService(Mapper<T> mapper) {
        this.mapper = mapper;
    }
	
	public Mapper<T> getMapper() {
		return mapper;
	}

	protected void setMapper(Mapper<T> mapper) {
		this.mapper = mapper;
	}
    
	
	public T selectOne(T record) {
		return mapper.selectOne(record);
	}

	
	public List<T> select(T record) {
		return mapper.select(record);
	}

	
	public List<T> selectAll() {
		return mapper.selectAll();
	}

	
	public int selectCount(T record) {
		return mapper.selectCount(record);
	}
	
	
	public T selectByPrimaryKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	
	public boolean existsWithPrimaryKey(Object key) {
		return mapper.existsWithPrimaryKey(key);
	}

	
	public int insertSelective(T record) {
		return mapper.insertSelective(record);
	}

	
	public int updateByPrimaryKey(T record) {
		return mapper.updateByPrimaryKey(record);
	}

	
	public int delete(T record) {
		return mapper.delete(record);
	}

	
	public int deleteByPrimaryKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	
	public int updateByPrimaryKeySelective(T record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

}
