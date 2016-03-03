package com.zgxcw.springboot.framework.mongodb.config;

import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.zgxcw.springboot.framework.utils.StringUtil;

/**
 * BasicDBObject扩展模版类
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年2月2日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class BasicDBObjectQuerySupport extends BasicDBObject{
	private static final long serialVersionUID = -7946868390920636603L;
	
	private Pattern pattern = null;
	
	/**
	 * 模糊查询
	 * @param field
	 * @param keyword
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 下午1:25:37
	 */
	public BasicDBObjectQuerySupport $like(String field,String keyword){
		if(StringUtil.isNotNull(keyword)){
			pattern=Pattern.compile("^.*"+keyword+".*$");  
			this.put(field, pattern);
		}
		return this;
	}
	
	/**
	 * key 大于等于gteValue 并且 小于等于lteValue<br/>
	 * 逻辑判断
	 * @param key 目标字段
	 * @param gteValue 大于等于
	 * @param lteValue 小于等于
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 下午1:28:49
	 */
	public BasicDBObjectQuerySupport $gteAndLte(String key,String gteValue,String lteValue){
		if(StringUtil.isNotNull(gteValue) && StringUtil.isNotNull(lteValue)){
			this.append(QueryOperators.AND, new BasicDBObject[]{
					new BasicDBObject().append(key, new BasicDBObject().append(QueryOperators.GTE, gteValue)),
					new BasicDBObject().append(key, new BasicDBObject().append(QueryOperators.LTE, lteValue))
			});
		}
		return this;
	}
	
	/**
	 * key大于 gtValue 并且小于 ltValue
	 * @param key 目标字段
	 * @param gtValue 大于
	 * @param ltValue 小于
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 下午1:51:48
	 */
	public BasicDBObjectQuerySupport $gtAndLt(String key,String gtValue,String ltValue){
		if(StringUtil.isNotNull(gtValue) && StringUtil.isNotNull(ltValue)){
			this.append(QueryOperators.AND, new BasicDBObject[]{
					new BasicDBObject().append(key, new BasicDBObject().append(QueryOperators.GT, gtValue)),
					new BasicDBObject().append(key, new BasicDBObject().append(QueryOperators.LT, ltValue))
			});
		}
		return this;
	}
	
	
	public static void main(String[] args) {
		BasicDBObjectQuerySupport support = new BasicDBObjectQuerySupport();
		support.$gteAndLte("exceptionTime", "2016-02-02 12:52:01", "2016-02-02 12:52:05");
		System.out.println(support);
	}
	
}
