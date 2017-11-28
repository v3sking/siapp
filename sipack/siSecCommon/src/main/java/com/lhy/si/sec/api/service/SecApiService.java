package com.lhy.si.sec.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lhy.si.common.service.AbstractCommonService;
import com.lhy.si.sec.api.mapper.SecApiCustomMapper;
import com.lhy.si.sec.api.model.SecApi;

import tk.mybatis.mapper.common.Mapper;

/**
* @ClassName: SecApiServcice
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 上午11:38:52
* @Copyright: Copyright (c) 2017 wisedu
*/
@Service
public class SecApiService extends AbstractCommonService<SecApi>  implements ISecApiService{
	
	@Autowired
	SecApiCustomMapper secApiCustomMapper;
	/**
	* @Title: SecApiServcice
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param mapper    设定文件
	*/
	public SecApiService(@Qualifier("secApiMapper") Mapper<SecApi> mapper) {
		super(mapper);
	}
	
	@Cacheable(cacheNames="api",key="#roleId")
	public List<SecApi> getApiByRoleId(int roleId) {
		return secApiCustomMapper.getApiByRoleId(roleId);
	}

}
