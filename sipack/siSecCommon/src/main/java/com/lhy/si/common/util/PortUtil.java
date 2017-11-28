package com.lhy.si.common.util;

import java.util.Random;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @ClassName: PortUtil
* @Description: 端口工具类
* @author  luanhy
* @date 2017年8月19日 下午3:30:18
* @Copyright: Copyright (c) 2017 wisedu
*/
public class PortUtil {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private int maxRandomCount = 1000;
	
	private int randomCount = 0;
	
	public void resetCount(){
		randomCount = 0;
	}

	/**
	 * 
	 * @Title: isPortAvailable
	 * @Description: 端口是否未被用
	 * @param port
	 * @return
	 */
	public boolean isPortAvailable(int port) {
		try {
			
			String[] commond = new String[2];
			commond[0] = "netstat";
			String encoding ="gbk";
			SystemCommandUtil systemCommandUtil = new SystemCommandUtil();
			if (systemCommandUtil.isWindows()) {
				commond[1] = "-aon";
			} else {
				commond[1] = "-anp";
				encoding = "utf-8";
			}
			String ret = systemCommandUtil.excuteCmdMultiThread(commond, encoding);
			boolean matches = Pattern.compile("(.+)("+port+"\\s+)(.*)").matcher(ret).find();
			return !matches;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @Title: getRandomPort
	 * @Description: 获取随机端口号
	 * @param minPort
	 * @param maxPort
	 * @return
	 */
	private int getRandomPort(int minPort, int maxPort) {
		Random random = new Random();
		int s = random.nextInt(maxPort) % (maxPort - minPort + 1) + minPort;
		return s;
	}

	/**
	 * 
	 * @Title: getUnAvailablePort
	 * @Description:获取未被占用的随机端口号
	 * @param minPort
	 * @param maxPort
	 * @return
	 */
	public int getUnAvailableRandomPort(int minPort, int maxPort) {
		if ((++randomCount) > maxRandomCount) {
			throw new RuntimeException("无法从" + minPort + "到" + maxPort + "绑定ehcache rmi同步端口号,请检查端口占用情况");
		}
		int randomPort = getRandomPort(minPort, maxPort);
		if (!isPortAvailable(randomPort)) {
			return getUnAvailableRandomPort(minPort, maxPort);
		}
		return randomPort;
	}

}
