package com.lhy.si.sec.user.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.lhy.si.common.service.AbstractCommonService;
import com.lhy.si.sec.user.model.SecUser;

import tk.mybatis.mapper.common.Mapper;

/**
* @ClassName: SecUserService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月25日 下午8:58:59
* @Copyright: Copyright (c) 2017 wisedu
*/
@Service
@CacheConfig(cacheNames="secUser")
public class SecUserService extends AbstractCommonService<SecUser> implements ISecUserService{

	/**
	* @Title: SecUserService
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param mapper    设定文件
	*/
	public SecUserService(@Qualifier("secUserMapper") Mapper<SecUser> mapper) {
		super(mapper);
	}
	
	/**
	* {@inheritDoc}
	* @Description: TODO: 缓存
	*/
	@Cacheable(key = "'exists'+#id")
	public boolean userExists(int id) {
		return super.existsWithPrimaryKey(id);
	}
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Cacheable(key = "#id")
	public SecUser getUser(int id) {
		return super.selectByPrimaryKey(id);
	}
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Caching(evict={@CacheEvict(key = "#record.id"),@CacheEvict(key = "'exists'+#record.id")})
	public int insertUser(SecUser record) {
		return super.insertSelective(record);
	}
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Caching(evict={@CacheEvict(key = "#record.id")})
	public int updateById(SecUser record) {
		return super.updateByPrimaryKeySelective(record);
	}

}
