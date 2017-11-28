package com.lhy.si.sec.role.mapper;

import java.util.List;

import com.lhy.si.sec.role.model.SecRole;

public interface SecRoleCustomMapper {
	
	List<SecRole> getRoleByUserId(int userId);
}