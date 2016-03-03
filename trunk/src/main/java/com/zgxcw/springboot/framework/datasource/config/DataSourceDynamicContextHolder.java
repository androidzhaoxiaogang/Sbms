package com.zgxcw.springboot.framework.datasource.config;

import com.zgxcw.springboot.framework.datasource.enums.DataSourceType;

/**
 * 动态数据源持有者<br/>
 * 为dataSource提供读写支持
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月26日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class DataSourceDynamicContextHolder {
	private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<DataSourceType>();
	
	/**
	 * 切换数据源
	 * @param dataSourceType
	 * @author huangke
	 * @date 2016年1月26日 下午10:35:31
	 */
    public static void setDataSourceType(DataSourceType dataSourceType) {
        contextHolder.set(dataSourceType);
    }
    
    /**
     * 获取当前数据源
     * @return
     * @author huangke
     * @date 2016年1月26日 下午10:35:50
     */
    public static DataSourceType getDataSourceType() {
        return contextHolder.get();
    }
    
    /**
     * 清空数据源 
     * @author huangke
     * @date 2016年1月26日 下午10:36:03
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
