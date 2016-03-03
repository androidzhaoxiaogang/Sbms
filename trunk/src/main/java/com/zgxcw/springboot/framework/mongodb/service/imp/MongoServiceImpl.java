package com.zgxcw.springboot.framework.mongodb.service.imp;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.zgxcw.springboot.framework.mongodb.config.MongoConfig;
import com.zgxcw.springboot.framework.mongodb.enums.Collection;
import com.zgxcw.springboot.framework.mongodb.service.MongoService;
import com.zgxcw.springboot.framework.utils.MongoUtil;

/**
 * mongodb 接口实现
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月29日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
@Service
@SuppressWarnings("deprecation")
public class MongoServiceImpl implements MongoService {
	private static final Logger logger = LoggerFactory.getLogger(MongoServiceImpl.class);
	
	private static final String _mid = "_id";//mongoid
	
	@Resource(name="mongoDataSource")
    private MongoClient dataSource;
	
	@Override
	public <T> String insert(T t) {
		DBObject obj = MongoUtil.getObject(t);
        WriteResult result = this.getCollectioin(t).insert(obj);
		return result.getLastError().ok() ? obj.get(_mid).toString() : null;
	}
	
	@Override
	public <T> boolean insert(List<T> t) {
		if(null != t && t.size() != 0){
			WriteResult result = this.getCollectioin(t.get(0)).insert(MongoUtil.getObject(t));
			return result.getLastError().ok();
		}
		else{
			return false;
		}
	}

	@Override
	public <T> String insert(Collection collection, T t) {
		DBObject obj = MongoUtil.getObject(t);
		WriteResult result = this.getCollectioin(collection).insert(MongoUtil.getObject(t));
		return result.getLastError().ok() ? obj.get(_mid).toString() : null;
	}
	
	@Override
	public <T> boolean insert(Collection collection, List<T> t) {
		WriteResult result = this.getCollectioin(collection).insert(MongoUtil.getObject(t));
		return result.getLastError().ok();
	}

	@Override
	public <T> boolean update(T t) {
		WriteResult result = this.getCollectioin(t).save(MongoUtil.getObject(t));
		return result.getLastError().ok();
	}

	@Override
	public <T> boolean update(Collection collection, T t) {
		WriteResult result = this.getCollectioin(collection).save(MongoUtil.getObject(t));
		return result.getLastError().ok();
	}

	@Override
	public <T> boolean delete(Class<T> clazz,String _id) {
		DBObject obj = new BasicDBObject(_mid, _id);
		WriteResult result = this.getCollectioin(clazz).remove(obj);
		return result.getLastError().ok();
	}

	@Override
	public <T> boolean delete(Collection collection,Class<T> clazz, String _id) {
		DBObject obj = new BasicDBObject(_mid, _id);
		WriteResult result = this.getCollectioin(collection).remove(obj);
		return result.getLastError().ok();
	}

	@Override
	public <T> T query(Class<T> clazz, String _id) {
		DBObject obj = new BasicDBObject(_mid, _id);
		DBObject object = this.getCollectioin(clazz).findOne(obj);
		try {
			if (null != object) {
				return  MongoUtil.getClass(clazz, object);
			}
			else{
				return null;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return null;
	}
	
	@Override
	public <T> List<T> query(Class<T> clazz, DBObject query) {
		DBCursor cursor = this.getCollectioin(clazz).find(query);
		List<T> result = new ArrayList<T>();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				result.add(MongoUtil.getClass(clazz, obj));
			}
		}catch (Throwable e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return result;
	}
	
	@Override
	public <T> List<T> query(Class<T> clazz, DBObject query,DBObject order, Integer page, Integer limit) {
		DBCursor cursor = null;
		cursor = this.getCollectioin(clazz).find(query).skip((page-1) * limit).sort(order).limit(limit);
		List<T> result = new ArrayList<T>();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				result.add(MongoUtil.getClass(clazz, obj));
			}
		}catch (Throwable e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return result;
	}
	
	@Override
	public <T> Long getCount(Class<T> clazz, DBObject query) {
		return this.getCollectioin(clazz).getCount(query);
	}

	@Override
	public <T> T query(Collection collection, Class<T> clazz, String _id) {
		DBObject obj = new BasicDBObject(_mid, _id);
		DBObject object = this.getCollectioin(collection).findOne(obj);
		try {
			if (null != object) {
				return  MongoUtil.getClass(clazz, object);
			}
			else{
				return null;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return null;
	}
	
	@Override
	public <T> List<T> query(Collection collection, Class<T> clazz, DBObject query) {
		DBCursor cursor = this.getCollectioin(collection).find(query);
		List<T> result = new ArrayList<T>();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				result.add(MongoUtil.getClass(clazz, obj));
			}
		}catch (Throwable e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return result;
	}
	
	@Override
	public <T> List<T> query(Collection collection, Class<T> clazz, DBObject query, DBObject order, Integer page,Integer limit) {
		DBCursor cursor = null;
		cursor = this.getCollectioin(collection).find(query).skip((page-1) * limit).sort(order).limit(limit);
		List<T> result = new ArrayList<T>();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				result.add(MongoUtil.getClass(clazz, obj));
			}
		}catch (Throwable e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return result;
	}

	@Override
	public <T> Long getCount(Collection collection, DBObject query) {
		return this.getCollectioin(collection).getCount(query);
	}
	
	
	
	/************************************utils*******************************/
	/**
	 * 获取集合
	 * @param t
	 * @return
	 * @author huangke
	 * @date 2016年1月29日 下午5:55:36
	 */
	private <T> DBCollection getCollectioin(T t){
        DBCollection collection = dataSource.getDB(MongoConfig.dbName).getCollection( t.getClass().getSimpleName().toLowerCase() );
		return collection;
	}
	/**
	 * 获取集合
	 * @param t
	 * @return
	 * @author huangke
	 * @date 2016年1月29日 下午5:55:36
	 */
	private <T> DBCollection getCollectioin(Collection col){
        DBCollection collection = dataSource.getDB(MongoConfig.dbName).getCollection( col.toString().toLowerCase() );
		return collection;
	}
	/**
	 * 获取集合
	 * @param t
	 * @return
	 * @author huangke
	 * @date 2016年1月29日 下午5:55:36
	 */
	private <T> DBCollection getCollectioin(Class<T> clazz){
        DBCollection collection = dataSource.getDB(MongoConfig.dbName).getCollection( clazz.getSimpleName().toLowerCase() );
		return collection;
	}

	

	

}
