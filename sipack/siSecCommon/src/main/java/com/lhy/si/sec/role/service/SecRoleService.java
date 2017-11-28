package com.lhy.si.sec.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lhy.si.common.service.AbstractCommonService;
import com.lhy.si.sec.role.mapper.SecRoleCustomMapper;
import com.lhy.si.sec.role.model.SecRole;

import tk.mybatis.mapper.common.Mapper;

/**
* @ClassName: SecRoleService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 上午11:16:31
* @Copyright: Copyright (c) 2017 wisedu
*/
@Service
public class SecRoleService extends AbstractCommonService<SecRole> implements ISecRoleService {
	
	@Autowired
	SecRoleCustomMapper secRoleCustomMapper;
	
	/**
	* @Title: SecRoleService
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param mapper    设定文件
	*/
	public SecRoleService(@Qualifier("secRoleMapper")Mapper<SecRole> mapper) {
		super(mapper);
	}
	
	@Cacheable(cacheNames="role",key="#userId")
	public List<SecRole> getRoleByUserId(int userId){
		return secRoleCustomMapper.getRoleByUserId(userId);
	}

}
