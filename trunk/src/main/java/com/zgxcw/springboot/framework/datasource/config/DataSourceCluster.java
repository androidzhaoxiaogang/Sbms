package com.zgxcw.springboot.framework.datasource.config;

import java.io.Serializable;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源类型持有者
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月28日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class DataSourceCluster implements Serializable{
	private static final long serialVersionUID = -400751397730508505L;
	
	private DruidDataSource reader;//读库
	private DruidDataSource writer;//主库
	
	public DruidDataSource getReader() {
		return reader;
	}
	public void setReader(DruidDataSource reader) {
		this.reader = reader;
	}
	public DruidDataSource getWriter() {
		return writer;
	}
	public void setWriter(DruidDataSource writer) {
		this.writer = writer;
	}
	
}
