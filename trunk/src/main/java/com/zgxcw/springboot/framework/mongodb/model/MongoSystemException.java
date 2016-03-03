package com.zgxcw.springboot.framework.mongodb.model;

import java.io.Serializable;

/**
 * 系统异常实体
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年2月1日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class MongoSystemException implements Serializable{

	private static final long serialVersionUID = 5608096666436015077L;
	
	public MongoSystemException(){}
	
	private String _id;// >> mongoid - primary key
	private String appName; // >> appName
	private String modelName; // >> modelName
	private String className; // >> className
	private String methodName; // >> methodName
	private String parameters; // >> parameters;
	private String exceptionTrace; // >> exceptionTrace
	private String exceptionTime; // >> exceptionTime

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public String getExceptionTrace() {
		return exceptionTrace;
	}
	public void setExceptionTrace(String exceptionTrace) {
		this.exceptionTrace = exceptionTrace;
	}
	public String getExceptionTime() {
		return exceptionTime;
	}
	public void setExceptionTime(String exceptionTime) {
		this.exceptionTime = exceptionTime;
	}
	
}
