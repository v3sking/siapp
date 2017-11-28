package com.lhy.si.weibo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lhy.si.common.model.ResponseResult;
import com.lhy.si.common.util.ResponseResultFactory;

/**
* @ClassName: WeiboController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 下午6:52:12
* @Copyright: Copyright (c) 2017 wisedu
*/
@RestController
@RequestMapping("weibo")
public class WeiboController {
	
	@Autowired
	private ResponseResultFactory responseResultFactory;
	
//	@RequestMapping(value="nearFeed1",method=RequestMethod.GET)
//	public ResponseResult<String> nearFeed1() {
//		return responseResultFactory.getSuccessObject("123", "附近有100个人");
//	}
	
	@RequestMapping(value="friendFeed",method=RequestMethod.GET)
	public ResponseResult<String> friendFeed() {
		return responseResultFactory.getSuccessObject("123", "我有100个好友");
	}
}
