package com.zgxcw.springboot.framework.mongodb.config;

import java.io.Serializable;
import java.util.List;


/**
 * mongodb数据源参数
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月29日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class MongoDataSourceHolder implements Serializable{
	private static final long serialVersionUID = -4390537955197350239L;
	
	public MongoDataSourceHolder(){}
	
	private MongoDataSourceCluster mongo;//数据源

	public MongoDataSourceCluster getMongo() {
		return mongo;
	}

	public void setMongo(MongoDataSourceCluster mongo) {
		this.mongo = mongo;
	}
}
