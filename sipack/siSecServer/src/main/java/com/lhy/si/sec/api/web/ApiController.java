package com.lhy.si.sec.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.ResponseResultFactory;
import com.lhy.si.sec.api.model.SecApi;
import com.lhy.si.sec.api.service.SecApiService;

/**
* @ClassName: RoleController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 下午4:54:36
* @Copyright: Copyright (c) 2017 wisedu
*/
@RestController
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	private SecApiService secApiService;
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
	
	@RequestMapping(value="getByRoleId",method=RequestMethod.GET)
	public ResponseResult<List<SecApi>> getByRoleId(Integer roleId) {
		List<SecApi> list = secApiService.getApiByRoleId(roleId);
		ResponseResult<List<SecApi>> successObject = responseResultFactory.getSuccessObject();
		successObject.setData(list);
		return successObject;
	}
	
}