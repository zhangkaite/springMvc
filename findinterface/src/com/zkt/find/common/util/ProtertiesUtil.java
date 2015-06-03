package com.zkt.find.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProtertiesUtil {
	private static Logger logger = LogManager.getLogger(ProtertiesUtil.class);
	public static String getValue(String propertiesname, String key) {
		String value = "";
		Properties properties = new Properties();
		Thread currentThread = Thread.currentThread();
		ClassLoader contextClassLoader = currentThread.getContextClassLoader();
		InputStream propertiesStream = contextClassLoader.getResourceAsStream(propertiesname);
		if (propertiesStream != null) {
			try {
				properties.load(propertiesStream);
				value = properties.getProperty(key);
				logger.info("获取的信息:"+value);
			} catch (IOException e) {
				e.printStackTrace();	
			}
		} else {
			System.out.println("not found");
		}
		return value;
	}

}
