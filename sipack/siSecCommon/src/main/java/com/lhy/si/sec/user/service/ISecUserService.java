package com.lhy.si.sec.user.service;

import com.lhy.si.sec.user.model.SecUser;

/**
* @ClassName: ISecUserService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年11月26日 下午4:22:46
* @Copyright: Copyright (c) 2017 wisedu
*/
public interface ISecUserService {
	
	SecUser getUser(int id);
	
}
