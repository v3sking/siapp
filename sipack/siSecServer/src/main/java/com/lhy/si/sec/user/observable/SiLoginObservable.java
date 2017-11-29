package com.lhy.si.sec.user.observable;

import java.util.Observable;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lhy.si.common.observer.SiActionObserver;
import com.lhy.si.sec.user.model.SecUser;

@Component
public class SiLoginObservable extends Observable{
	
	private final Logger logger = LoggerFactory.getLogger(SiLoginObservable.class); 
	
	@PostConstruct
	private void init(){
		//查询数据库 加入登录观察者数据
		SiActionObserver sao = new SiActionObserver("", "", "");
		super.addObserver(sao);
	}
	
	@Override
	public void notifyObservers(Object arg) {
		logger.info("开始发送用户登录通知");
		SecUser user = (SecUser)arg;
		super.notifyObservers(arg);
	}
}
