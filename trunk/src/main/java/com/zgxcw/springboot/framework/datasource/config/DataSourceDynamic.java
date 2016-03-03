package com.zgxcw.springboot.framework.datasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源bean 定义
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月26日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class DataSourceDynamic extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceDynamicContextHolder.getDataSourceType();
	}

}
