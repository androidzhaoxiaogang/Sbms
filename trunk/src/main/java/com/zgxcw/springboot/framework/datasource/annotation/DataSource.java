package com.zgxcw.springboot.framework.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zgxcw.springboot.framework.datasource.enums.DataSourceType;

/**
 *1. 数据源类型注解，com..service..impl.*.*全路径扫描service包下的impl子包<br/>
 *
 *2. 此注解定义类型只能添加在方法头部,扫描到的方法受同一事务管理<br/>
 *
 *3. 当不指定数据源类型时,<code>@DataSource</code>默认使用主库
 *<pre>
 *<code>@DataSource</code>
 *public void insert(){
 * //do something ...
 *}
 *</pre>
 *
 *4. 也可显示指定数据类型为DataSourceType.WRITE,作用同3
 *<pre>
 *<code>@DataSource(DataSourceType.WRITE)</code>
 *public void insert(){
 * //do something ...
 *}
 *</pre>
 *5. 当不指定此注解时默认使用主库,作用同3
 *<pre>
 *public void insert(){
 * //do something ...
 *}
 *</pre>
 *6. 当使用<code>@DataSource(DataSourceType.READ)</code>方式时切换到读库<br/>
 **<pre>
 *<code>@DataSource(DataSourceType.READ)</code>
 *public void insert(){
 * //do something ...
 *}
 *</pre>
 * @author huangke
 * @description 
 * @project SpringBootMicroService
 * @date 2016年1月26日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
	DataSourceType value()  default DataSourceType.WRITE;
}
