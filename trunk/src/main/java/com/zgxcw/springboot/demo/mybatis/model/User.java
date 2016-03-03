package com.zgxcw.springboot.demo.mybatis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * mongodb测试实体
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月28日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class User implements Serializable{
	private static final long serialVersionUID = -3278125634712249949L;
	
	public User(){}
	
	private String _id;
	private String userName;
	private String passWord;
	private City city;
	private String createTime;
	private Integer age;
	

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Override
	public String toString() {
		return "User [_id=" + _id + ", userName=" + userName + ", passWord=" + passWord + ", city=" + city + "]";
	}
	
}
