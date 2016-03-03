package com.zgxcw.springboot.framework.mongodb.config;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mongodb.ServerAddress;

/**
 * 数据源
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月29日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class MongoDataSourceCluster implements Serializable {
	private static final long serialVersionUID = -1382163294873504775L;
	
	public MongoDataSourceCluster(){}
	
	private MongoBuilder builder;//连接池参数
	private List<Map<String, Object>> addresses;//连接池地址
	private String dbName;//数据库名称

	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public MongoBuilder getBuilder() {
		return builder;
	}
	public void setBuilder(MongoBuilder builder) {
		this.builder = builder;
	}
	public List<Map<String, Object>> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Map<String, Object>> addresses) {
		this.addresses = addresses;
	}
}
