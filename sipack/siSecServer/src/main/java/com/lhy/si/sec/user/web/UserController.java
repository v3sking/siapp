package com.lhy.si.sec.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lhy.si.auth.token.StatelessToken;
import com.lhy.si.auth.token.manager.TokenManager;
import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.IpAddressUtil;
import com.lhy.si.common.util.MacUtil;
import com.lhy.si.common.util.ResponseResultFactory;
import com.lhy.si.sec.user.model.SecUser;
import com.lhy.si.sec.user.service.SecUserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private SecUserService secUserService;
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
	@Autowired
	private TokenManager tokenManager;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public ResponseResult<String> login(String id, String password) {
		ResponseResult<String> successObject = responseResultFactory.getSuccessObject();
		SecUser selectByPrimaryKey = secUserService.getUser(Integer.parseInt(id));
		if (selectByPrimaryKey != null && selectByPrimaryKey.getPassword() != null && 
				selectByPrimaryKey.getPassword().equals(password)) {
			StatelessToken createToken = tokenManager.createToken(String.valueOf(id));
			successObject.setData(createToken.getToken());
		}else{
			successObject.setRet(ResponseResult.FAIL).setMsg("账号或密码不正确");
		}
		return successObject;
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public ResponseResult<Object> register(SecUser secUser) {
		secUser.setStatus(0);
		return responseResultFactory.getSuccessObject(secUserService.insertSelective(secUser));
	}
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	public ResponseResult<SecUser> get(Integer id) {
		return responseResultFactory.getSuccessObject(secUserService.getUser(id));
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public ResponseResult<List<SecUser>> list() {
		return responseResultFactory.getSuccessObject(secUserService.selectAll());
	}
}
