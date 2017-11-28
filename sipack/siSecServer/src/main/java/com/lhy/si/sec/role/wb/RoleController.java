package com.lhy.si.sec.role.wb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.ResponseResultFactory;
import com.lhy.si.sec.role.model.SecRole;
import com.lhy.si.sec.role.service.SecRoleService;

/**
* @ClassName: RoleController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 下午4:54:36
* @Copyright: Copyright (c) 2017 wisedu
*/
@RestController
@RequestMapping("role")
public class RoleController {
	
	@Autowired
	private SecRoleService secRoleService;
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
	@RequestMapping(value="getByUserId",method=RequestMethod.GET)
	public ResponseResult<List<SecRole>> getByUserId(Integer userId) {
		List<SecRole> roleByUserId = secRoleService.getRoleByUserId(userId);
		ResponseResult<List<SecRole>> successObject = responseResultFactory.getSuccessObject();
		successObject.setData(roleByUserId);
		return successObject;
	}
	
}