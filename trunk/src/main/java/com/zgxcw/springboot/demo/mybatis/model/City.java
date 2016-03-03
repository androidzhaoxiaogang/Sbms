package com.zgxcw.springboot.demo.mybatis.model;

import java.io.Serializable;

public class City implements Serializable{
	private static final long serialVersionUID = 2928698568140253633L;
	public City(){}
	private String cityName;
	private String cityCode;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", cityCode=" + cityCode + "]";
	}
}
