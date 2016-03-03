package com.zgxcw.springboot.framework.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zgxcw.springboot.demo.mybatis.model.User;

/**
 * mongo工具类 
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月29日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class MongoUtil {
	
	private static final Integer Private_Static_Final = 26;//Modifier
	
	/**
	 * 构造db数据对象(递归)
	 * @param t
	 * @return
	 * @author huangke
	 * @date 2016年1月29日 下午6:08:32
	 */
	public static  <T> DBObject getObject(T t){
		BasicDBObject object = new BasicDBObject();
		Field fields[] = t.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				Object o = f.get(t);
				if(null != o && !"".equals(o)){
					if(instanceOf(o)) object.put(f.getName(), o);
					else object.put(f.getName(), getObject(o));
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * 批量转换
	 * @param t
	 * @return
	 * @author huangke
	 * @date 2016年2月1日 下午2:34:46
	 */
	public static <T> List<DBObject> getObject(List<T> ts){
		if(null != ts && ts.size() != 0){
			List<DBObject> result = new ArrayList<DBObject>();
			for (T t : ts) {
				result.add(MongoUtil.getObject(t));
			}
			return result;
		}
		else{
			return null;
		}
	}
	
	/**
	 * 判断对象是否自定义
	 * @param o
	 * @return
	 * @author huangke
	 * @date 2016年2月1日 上午10:05:27
	 */
	public static boolean instanceOf(Object o){
		return  o instanceof String    || o instanceof Integer ||
				o instanceof Character || o instanceof Boolean ||
				o instanceof Long      || o instanceof Byte    ||
				o instanceof Float     || o instanceof Double  ||
				o instanceof Short     ;
	}
	
	/**
	 * 反泛化(递归)
	 * @param clazz
	 * @param object
	 * @return
	 * @author huangke
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @date 2016年2月1日 上午11:02:12
	 */
	public static <T> T getClass(Class<T> clazz,DBObject object) throws InstantiationException, IllegalAccessException{
		Field fields[] = clazz.getDeclaredFields();
		T t = clazz.newInstance();
		try {  
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				if (f.getModifiers() != Private_Static_Final && null != object.get(f.getName()) ) {
					if (instanceOf(object.get(f.getName()))) 
						f.set(t, object.get(f.getName()));
					else
						f.set(t, getClass(f.getType(), (DBObject)object.get(f.getName())));
				}
			}
		}catch (Throwable e) {
			e.printStackTrace();
		}
		return t;
		
	}
	
	public static void main(String[] args) {
		try {
			DBObject obj = new BasicDBObject();
			obj.put("_id", "8aae2391-b216-44fa-84ec-7a7e0f66b35e");
			obj.put("serialVersionUID", -3278125634712249949l);
			obj.put("userName", "测试用例insert");
			obj.put("passWord", "112233");
			DBObject city = new BasicDBObject();
			city.put("cityName", "上海");
			city.put("cityCode", "PRO_001");
			city.put("serialVersionUID", 2928698568140253633l);
			obj.put("city", city);
			User user = MongoUtil.getClass(User.class, obj);
		}  catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
