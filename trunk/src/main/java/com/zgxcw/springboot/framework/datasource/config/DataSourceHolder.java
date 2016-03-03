package com.zgxcw.springboot.framework.datasource.config;

import java.io.Serializable;


/**
 * 数据源
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月28日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class DataSourceHolder implements Serializable{
	private static final long serialVersionUID = 7124539854435503443L;
	
	public DataSourceHolder(){}
	
	private DataSourceCluster dataSource;

	public DataSourceCluster getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSourceCluster dataSource) {
		this.dataSource = dataSource;
	}
}
