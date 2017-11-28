//package com.lhy.si.common.util;
//
//import java.io.File;
//import java.util.Iterator;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class TomcatUtil {
//	
//	private static final Logger logger = LoggerFactory.getLogger(TomcatUtil.class);
//	
//	private static String port = null;
//	
//	/**
//	 * 
//	* @Title: getPort
//	* @Description: 获取当前tomcat端口号：
//	* @return String    返回类型
//	* @return
//	 */
//	public static String getPort(){
//		Lock lock  = new ReentrantLock();
//		lock.lock();
//		try{
//			if(port == null){
//				port = getPort1();
//			}
//		}finally{
//		  lock.unlock();
//		}
//		return port;
//	}
//	
//	private static String getPort1(){
//		String path = System.getProperty("catalina.home");
//		if(path == null){
//			logger.info("catalina.home为空，采用默认路径");
//			path ="D:/apache-tomcat-8.0.44-2";
//		}
//		logger.info("catalina.home:{}",path);
//		File f = new File(path+"/conf/server.xml");
//		if(!f.exists()){
//			throw new RuntimeException("server文件："+f.getAbsolutePath()+"不存在");
//		}
//		SAXReader s = new SAXReader();
//		Document read = null;
//		try {
//			read = s.read(f);
//		} catch (DocumentException e) {
//			throw new RuntimeException(e);
//		}
//		String port = "";
//		Iterator elementIterator = read.getRootElement().element("Service").elementIterator("Connector");
//		if(	elementIterator.hasNext()){
//			Element next = (Element)elementIterator.next();
//			String attributeValue = next.attributeValue("protocol");
//			if(attributeValue.equals("HTTP/1.1")){
//				port = next.attributeValue("port");
//				return port;
//			}
//		}
//		return port;
//	}
//	
//}
