package com.lhy.si.sec.api.service;

import java.util.List;

import com.lhy.si.sec.api.model.SecApi;

/**
* @ClassName: ISecApiService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 下午4:10:15
* @Copyright: Copyright (c) 2017 wisedu
*/
public interface ISecApiService {
	
	 List<SecApi> getApiByRoleId(int roleId);
}