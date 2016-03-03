package com.zgxcw.springboot.framework.utils.file;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取Properties文件
 * 
 */
public class PropertyUtil {

	private static PropertyUtil instance = null;

	/**
	 * 创建本类的单例
	 * <p/>
	 * getInstance(单例)
	 * 
	 * @return ReadProperties对象
	 */
	public static PropertyUtil getInstance() {
		if (instance == null) {
			instance = new PropertyUtil();
		}
		return instance;
	}

	// 根据key读取value
	public String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream(filePath);
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//
	public Properties readFile(String filePath) {
		Properties props = new Properties();
		try {
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream(filePath);
			props.load(in);
			return props;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
