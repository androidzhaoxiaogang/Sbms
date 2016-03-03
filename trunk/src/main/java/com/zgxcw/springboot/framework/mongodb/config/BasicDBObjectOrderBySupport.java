package com.zgxcw.springboot.framework.mongodb.config;

import com.mongodb.BasicDBObject;
import com.zgxcw.springboot.framework.mongodb.enums.OrderBy;

/**
 * 排序规则扩展
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年2月2日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class BasicDBObjectOrderBySupport extends BasicDBObject{
	private static final long serialVersionUID = 4858103358325723804L;

	/**
	 * 排序规则 
	 * @param orderBy
	 * @param key
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 下午2:04:57
	 */
	public BasicDBObjectOrderBySupport $orderBy(String key,OrderBy orderBy){
		this.put(key, orderBy == OrderBy.ASC ? 1 : -1);
		return this;
	} 
}
