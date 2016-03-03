package com.zgxcw.springboot.demo.mybatis.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zgxcw.springboot.demo.mybatis.model.City;
import com.zgxcw.springboot.demo.mybatis.model.User;
import com.zgxcw.springboot.framework.mongodb.config.BasicDBObjectOrderBySupport;
import com.zgxcw.springboot.framework.mongodb.config.BasicDBObjectQuerySupport;
import com.zgxcw.springboot.framework.mongodb.enums.Collection;
import com.zgxcw.springboot.framework.mongodb.enums.OrderBy;
import com.zgxcw.springboot.framework.mongodb.model.MongoSystemException;
import com.zgxcw.springboot.framework.mongodb.service.MongoService;
import com.zgxcw.springboot.framework.utils.DateUtil;

/**
 * mongo rest接口测试用例
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年2月1日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
@Controller
@RequestMapping("/mongo")
public class MongoController { 
	
	private Integer limit = 15;
	
	@Autowired
	private MongoService mongoService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.STYLE_TIMESTAMP24);
	
	@RequestMapping("/index")
	public String index(MongoSystemException mongoSystemException,HttpServletRequest request,Model model){
		String p = request.getParameter("page");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		//分页参数
		int page = 1;
		page = null != p ? Integer.parseInt(p) : page;
		
		//构建查询条件
		BasicDBObjectQuerySupport query = new BasicDBObjectQuerySupport();
		query.$like("appName", mongoSystemException.getAppName())
			 .$like("modelName", mongoSystemException.getModelName())
			 .$like("className", mongoSystemException.getClassName())
			 .$like("methodName", mongoSystemException.getMethodName())
			 .$like("parameters", mongoSystemException.getParameters())
			 .$like("exceptionTrace", mongoSystemException.getExceptionTrace())
			 .$gteAndLte("exceptionTime", startTime, endTime);
		//构建排序规则
		BasicDBObjectOrderBySupport order = new BasicDBObjectOrderBySupport();
		order.$orderBy("exceptionTime",OrderBy.ASC);
		//查询结果
		List<MongoSystemException> list = mongoService.query(MongoSystemException.class, query, order, page, limit);
		
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		model.addAttribute("limit",limit);
		model.addAttribute("count",mongoService.getCount(MongoSystemException.class, query));
		model.addAttribute("mongoSystemException",mongoSystemException);
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
		return "mongo/index";
	}
	
	@InitBinder("mongoSystemException")
	public void bindStoreBeanDto(WebDataBinder binder){
		binder.setFieldDefaultPrefix("mongoSystemException.");
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>新增数据<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//
	/**/@RequestMapping("/insert")
	public String insert(){
		User user = new User();
		user.setUserName("测试用例insertCollection");
		user.setPassWord("1111111");
		user.set_id(UUID.randomUUID().toString());
		City city = new City();
		city.setCityCode("PRO_001");
		city.setCityName("上海");
		user.setCity(city);
		user.setAge(123);
		user.setCreateTime(sdf.format(new Date()));
		return mongoService.insert(user) + "";
	}
	
	@RequestMapping("/insertBatch")
	public String insertBatch(){
		List<User> list = new ArrayList<User>();
		User user = null;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.set_id(UUID.randomUUID().toString());
			user.setUserName("测试用例insertBatch"  + i);
			user.setCreateTime(sdf.format(new Date()));
			user.setPassWord("1111111");
			user.setAge(i);
			City city = new City();
			city.setCityCode("PRO_00"+i);
			city.setCityName("上海"+i);
			user.setCity(city);
			list.add(user);
		}
		return mongoService.insert(list) + "";
	}
	
	@RequestMapping("/insertCollection")
	public String insertCollection(){
		User user = new User();
		user.set_id(UUID.randomUUID().toString());
		user.setUserName("测试用例insertCollection");
		user.setCreateTime(sdf.format(new Date()));
		user.setPassWord("1111111");
		user.setAge(123);
		City city = new City();
		city.setCityCode("PRO_001");
		city.setCityName("上海");
		user.setCity(city);
		
		return mongoService.insert(Collection.T_USER,user) + "";
	}
	
	@RequestMapping("/insertCollectionBatch")
	public String insertCollectionBatch(){
		List<User> list = new ArrayList<User>();
		User user = null;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.set_id(UUID.randomUUID().toString());
			user.setUserName("测试用例insertCollectionBatch"  + i);
			user.setPassWord("1111111");
			user.setCreateTime(sdf.format(new Date()));
			user.setAge(i);
			City city = new City();
			city.setCityCode("PRO_00"+i);
			city.setCityName("上海"+i);
			user.setCity(city);
			list.add(user);
		}
		return mongoService.insert(Collection.T_USER,list) + "";
	}
	
	

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>根据id查询数据<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//
	@RequestMapping("/query/{id}")
	public String query(@PathVariable String id){
		User user = new User();
		user.setUserName("测试用例insertCollection");
		user.setPassWord("1111111");
		return mongoService.query(User.class,id) + "";
	}
	
	@RequestMapping("/queryCollection/{id}")
	public String queryCollection(@PathVariable String id){
		User user = new User();
		user.setUserName("测试用例insertCollection");
		user.setPassWord("1111111");
		return mongoService.query(Collection.T_USER,User.class,id) + "";
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>根据id更新<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//
	@RequestMapping("/update/{id}")
	public String update(@PathVariable String id){
		User user = mongoService.query(User.class,id);
		user.setUserName("名字被修改了");
		return mongoService.update(user) + "";
	}
	
	@RequestMapping("/updateCollection/{id}")
	public String updateCollection(@PathVariable String id){
		User user = mongoService.query(Collection.T_USER,User.class,id);
		user.setUserName("名字被修改了");
		return mongoService.update(Collection.T_USER,user) + "";
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>根据id删除<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable String id){
		return mongoService.delete(User.class, id) + "";
	}
	
	@RequestMapping("/deletCollection/{id}")
	public String deletCollection(@PathVariable String id){
		return mongoService.delete(Collection.T_USER,User.class, id) + "";
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>高级查询<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//
	//queryObject = new BasicDBObject().append("id",  new BasicDBObject().append(QueryOperators.GT, 10)); 
	@RequestMapping("/queryPager/{page}")
	public String query1(@PathVariable Integer page){
		DBObject obj = new BasicDBObject();
		obj.put("_id", -1);
		return mongoService.query(User.class, null,obj,page,2) + "";
	}
}
