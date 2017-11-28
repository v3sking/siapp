package com.lhy.si.sec.api.mapper;

import java.util.List;

import com.lhy.si.sec.api.model.SecApi;

public interface SecApiCustomMapper {
	
	List<SecApi> getApiByRoleId(int roleId);
}