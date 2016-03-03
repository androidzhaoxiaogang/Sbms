package com.zgxcw.springboot.framework.mongodb.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ho.yaml.Yaml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * mongodb配置
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月29日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
@Configuration
public class MongoConfig {
	private static final String PATH = "\\config\\datasource.yml";
	
	public static String dbName;
	
	public MongoConfig(){};
	
	/**
	 * 
	 * @return 
	 * @author huangke
	 * @throws UnknownHostException 
	 * @date 2016年1月28日 下午3:32:31
	 */
	private static MongoDataSourceHolder load() throws UnknownHostException{
		try {
			File f = new File(System.getProperty("user.dir")+PATH);
			FileInputStream fis=new FileInputStream(f.getAbsolutePath());
			MongoDataSourceHolder dataSourceHolder = Yaml.loadType(fis, MongoDataSourceHolder.class);
			return dataSourceHolder;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Bean(name="mongoDataSource")
	public MongoClient mongoDataSource(){
		MongoClient client = null;
		try {
			MongoDataSourceHolder holder = MongoConfig.load();
			List<Map<String, Object>> addresses = holder.getMongo().getAddresses();
			List<ServerAddress> addrs = new ArrayList<>();
			if(null != addresses ){
				ServerAddress address = null;
				for (Map<String, Object> s : addresses) {
					address = new ServerAddress(s.get("host").toString(),Integer.parseInt(s.get("port").toString()));
					addrs.add(address);
				}
			}
			client = new MongoClient(addrs,holder.getMongo().getBuilder().build());
			MongoConfig.dbName = holder.getMongo().getDbName();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return client;
	}
}
