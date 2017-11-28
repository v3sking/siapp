package com.lhy.si.sec.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhy.si.common.util.ResponseResultFactory;
import com.lhy.si.sec.user.service.SecUserService;

@RestController
@RequestMapping("userM")
public class UserManageController {
	
	@Autowired
	private SecUserService secUserService;
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
}
