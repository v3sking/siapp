package com.lhy.si.sec.role.service;

import java.util.List;

import org.springframework.http.HttpMethod;

import com.lhy.si.sec.role.model.SecRole;

/**
* @ClassName: ISecRoleService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 下午3:50:34
* @Copyright: Copyright (c) 2017 wisedu
*/
public interface ISecRoleService {
	
	List<SecRole> getRoleByUserId(int userId);
}
