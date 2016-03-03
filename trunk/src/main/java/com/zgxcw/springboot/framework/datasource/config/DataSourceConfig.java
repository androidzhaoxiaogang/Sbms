package com.zgxcw.springboot.framework.datasource.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.ho.yaml.Yaml;



/**
 * 数据源获取
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月28日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class DataSourceConfig {
	
	private static final String PATH = "\\config\\datasource.yml";
	
	private DataSourceConfig(){};
	/**
	 * 
	 * @return 
	 * @author huangke
	 * @date 2016年1月28日 下午3:32:31
	 */
	public static DataSourceHolder load(){
		try {
			File f = new File(System.getProperty("user.dir")+PATH);
			FileInputStream fis=new FileInputStream(f.getAbsolutePath());
			DataSourceHolder dataSourceHolder = Yaml.loadType(fis, DataSourceHolder.class);
			return dataSourceHolder;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
