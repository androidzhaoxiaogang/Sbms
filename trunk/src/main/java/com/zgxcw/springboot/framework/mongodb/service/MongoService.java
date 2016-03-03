package com.zgxcw.springboot.framework.mongodb.service;

import java.util.List;

import com.mongodb.DBObject;
import com.zgxcw.springboot.framework.mongodb.enums.Collection;

/**
 * Mongodb服务接口
 * 1. 支持单条数据CRUD
 * 2. 支持数据批量CRUD
 * 3. 支持指定表空间数据操作
 * 4. 支持对象嵌套操作(自定义对象)
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月28日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public interface MongoService {
	
	/**
	 * 插入一条数据<br/>
	 * 插入的集合目标为T
	 * @param t
	 * @return 插入数据的_id
	 * @author huangke
	 * @date 2016年1月28日 下午8:36:33
	 */
	public <T> String insert(T t);
	
	/**
	 * 批量插入数据<br/>
	 * 插入的集合目标为T
	 * @param t
	 * @return 插入结果
	 * @author huangke
	 * @date 2016年2月1日 下午2:31:11
	 */
	public <T> boolean insert(List<T> t);
	
	/**
	 * 插入一条数据 - 指定集合
	 * @param collection
	 * @param t
	 * @return 插入数据的_id
	 * @author huangke
	 * @date 2016年1月28日 下午8:41:43
	 */
	public <T> String insert(Collection collection,T t);
	
	/**
	 * 批量插入数据 - 指定集合<br/>
	 * @param t
	 * @return 插入结果
	 * @author huangke
	 * @date 2016年2月1日 下午2:31:11
	 */
	public <T> boolean insert(Collection collection,List<T> t);
	
	/**
	 * 根据_id更新一条数据<br/>
	 * 更新的集合目标为T
	 * <br/>
	 * 如果在collection内已经存在一个和x对象相同的"_id"的记录,<br/>
	 * mongodb就会把x对象替换collection内已经存在的记录，否则将会插入x对象，<br/>
	 * 如果x内没有_id,系统会自动生成一个再插入。相当于上面update语句的upsert=true,multi=false的情况。
	 * @param t
	 * @return
	 * @author huangke
	 * @param <T>
	 * @date 2016年1月28日 下午8:39:27
	 */
	public <T> boolean update(T t);
	
	/**
	 * 匹配更新一条数据-指定集合
	 *  <br/>
	 * 如果在collection内已经存在一个和x对象相同的"_id"的记录,<br/>
	 * mongodb就会把x对象替换collection内已经存在的记录，否则将会插入x对象，<br/>
	 * 如果x内没有_id,系统会自动生成一个再插入。相当于上面update语句的upsert=true,multi=false的情况。
	 * @param collection
	 * @param t
	 * @return
	 * @author huangke
	 * @date 2016年1月28日 下午8:47:17
	 */
	public <T> boolean update(Collection collection,T t);
	
	/**
	 * 删除一条数据<br/>
	 * 目标集合为T
	 * @param t
	 * @return
	 * @author huangke
	 * @date 2016年1月28日 下午8:48:11
	 */
	public <T> boolean delete(Class<T> clazz,String _id);
	
	/**
	 * 删除一条数据-指定集合
	 * @param collection
	 * @param t
	 * @return
	 * @author huangke
	 * @param <T>
	 * @date 2016年1月28日 下午8:48:49
	 */
	public <T> boolean delete(Collection collection,Class<T> clazz,String _id);
	
	/**
	 * 根据id和集合类型获取一条数据
	 * @param clazz
	 * @param id
	 * @return
	 * @author huangke
	 * @date 2016年1月28日 下午8:31:49
	 */
	public <T> T query(Class<T> clazz,String _id);
	
	/**
	 * 根据条件获得数据数据列表
	 * @param clazz
	 * @param query
	 * @return
	 * @author huangke
	 * @date 2016年2月1日 下午3:27:44
	 */
	public <T> List<T> query(Class<T> clazz,DBObject query);
	
	/**
	 * 带分页的自定义查询功能<br/>
	 * 
	 * @param clazz 反泛化类型
	 * @param query 为null时查询所有数据
	 * @param order 排序规则，为null时默认排序 <br/>
	 * key = 排序字段  value = 排序方式(-1降序 1升序)
	 * @param page	当前页
	 * @param limit	每页显示条数
	 * @return 
	 * @author huangke
	 * @date 2016年2月1日 下午4:34:14
	 */
	public <T> List<T> query(Class<T> clazz,DBObject query,DBObject order,Integer page,Integer limit);
	
	/**
	 * 带分页的自定义查询功能（总数）
	 * @param clazz
	 * @param query
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 上午11:05:15
	 */
	public <T> Long getCount(Class<T> clazz,DBObject query);
	
	/**
	 * 获得一条数据 -指定集合
	 * @param collection
	 * @param clazz
	 * @param id
	 * @return
	 * @author huangke
	 * @date 2016年1月28日 下午8:50:36
	 */
	public <T> T  query(Collection collection,Class<T> clazz,String _id);
	
	/**
	 * 根据条件获得指定集合的数据列表
	 * @param clazz
	 * @param query
	 * @return
	 * @author huangke
	 * @date 2016年2月1日 下午3:27:44
	 */
	public <T> List<T> query(Collection collection,Class<T> clazz,DBObject query);
	
	/**
	 * 带分页的自定义查询功能 -指定集合<br/>
	 * 
	 * @param clazz 反泛化类型
	 * @param query 为null时查询所有数据
	 * @param order 排序规则，为null时默认排序 <br/>
	 * key = 排序字段  value = 排序方式(-1降序 1升序)
	 * @param page	当前页
	 * @param limit	每页显示条数
	 * @return 
	 * @author huangke
	 * @date 2016年2月1日 下午4:34:14
	 */
	public <T> List<T> query(Collection collection,Class<T> clazz,DBObject query,DBObject order,Integer page,Integer limit);
	
	/**
	 * 带分页的自定义查询功能（总数）-指定集合
	 * @param clazz
	 * @param query
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 上午11:05:15
	 */
	public <T> Long getCount(Collection collection,DBObject query);
	
}
