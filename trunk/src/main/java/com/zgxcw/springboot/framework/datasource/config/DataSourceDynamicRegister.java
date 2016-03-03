package com.zgxcw.springboot.framework.datasource.config;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.zgxcw.springboot.demo.mybatis.conf.MyBatisConfig;
import com.zgxcw.springboot.framework.datasource.enums.DataSourceType;

/**
 * 动态数据源注册类
 * 此类实现ImportBeanDefinitionRegistrar<br/>
 * 在程序的入口必须添加<code>@Import(DynamicDataSourceRegister.class)</code>开启读写分离
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月26日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
@Component
public class DataSourceDynamicRegister implements ImportBeanDefinitionRegistrar{
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceDynamicRegister.class);
    
    /**
     * 注册数据源
     * @author huangke
	 * @date 2016年1月26日 下午10:36:32
     */
	@Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    	
    	DataSource reader = DataSourceConfig.load().getDataSource().getReader();
    	DataSource writer = DataSourceConfig.load().getDataSource().getWriter();
    	
        Map<Object, Object> target = new HashMap<Object, Object>();
        target.put(DataSourceType.WRITE, writer);
        target.put(DataSourceType.READ, reader);

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DataSourceDynamic.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", writer);
        mpv.addPropertyValue("targetDataSources", target);
        registry.registerBeanDefinition("datasource", beanDefinition);

        logger.info("数据库连接初始化完成");
        System.out.println("数据库连接初始化完成");
    }
    
}
