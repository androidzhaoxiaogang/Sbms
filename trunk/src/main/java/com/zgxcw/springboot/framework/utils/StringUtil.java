package com.zgxcw.springboot.framework.utils;

/**
 * 字符串工具类
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年2月2日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class StringUtil {
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 下午1:32:06
	 */
	public static boolean isNotNull(String str){
		return null != str && !"".equals(str);
	}
}
