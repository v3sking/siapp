package com.lhy.si.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
* @ClassName: SystemCommandUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author  luanhy
* @date 2017年8月19日 下午3:32:27
* @Copyright: Copyright (c) 2017 wisedu
*/
public class SystemCommandUtil {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	public boolean isWindows() {
		String osName = System.getProperty("os.name");
		return (osName.indexOf("Windows") != -1);
	}

	/** 执行外部程序,并获取标准输出 */
	public String excuteCmdMultiThread(String[] cmd, String encoding) {
		Process p  = null;
		try {
			p = Runtime.getRuntime().exec(cmd);
			/* 为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞 */
			Thread t = new Thread(new InputStreamRunnable(p.getErrorStream(), "ErrorStream"));
			t.start();
			/* "标准输出流"就在当前方法中读取 */
			encoding = StringUtils.isEmpty(encoding)?"gbk":encoding;
			String string = "";
			//string = IOUtils.toString(p.getInputStream(), encoding);
			return string;
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			p.destroy();
		}
		return null;
	}

	/** 读取InputStream的线程 */
	class InputStreamRunnable implements Runnable {
		BufferedReader bReader = null;
		String type = null;

		public InputStreamRunnable(InputStream is, String _type) {
			try {
				bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
				type = _type;
			} catch (Exception ex) {
			}
		}

		public void run() {
			String line = null;
			try {
				while ((line = bReader.readLine()) != null) {
					logger.error(line);
				}
				bReader.close();
			} catch (Exception ex) {
			}
		}
	}
}
