package com.zgxcw.springboot.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * @Title: DateUtil
 * @Description: 日期相关工具类
 * @Author: zhao yongping
 * @DateTime: 2015年10月6日 下午1:51:53
 * @Version: 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.cn All Rights Reserved
 * @Company: 诸葛修车网
 */
public class DateUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String STYLE_DATE1    =  "yyyy-MM-dd"; //2000-05-21
  public static final String STYLE_TIMESTAMP1 =  "yyyy-MM-dd hh:mm:ss"; //2000-05-21 08:30:00
  public static final String STYLE_TIMESTAMP24=  "yyyy-MM-dd HH:mm:ss"; //2000-05-21 20:30:00
  
  public static final String STYLE_DATE2    =  "yyyy.MM.dd"; //2000.05.21
  public static final String STYLE_TIMESTAMP2 =  "yyyy.MM.dd hh:mm:ss";         //2000.05.21 20:30:00
  
  public static final String STYLE_DATE3    =  "dd/MM/yyyy";            //2000-05-21
  public static final String STYLE_TIMESTAMP3 =  "dd/MM/yyyy hh:mm:ss";         //2000-05-21
  
  public static final String STYLE_DATE4    =  "EEE, MMM d, ''yy";                //->>  Wed, July 10, '96
  public static final String STYLE_DATE5    =  "h:mm a";                          //->>  12:08 PM 
  public static final String STYLE_DATE6    =  "hh 'o''clock' a, zzzz";           //->>  12 o'clock PM, Pacific Daylight Time
  public static final String STYLE_DATE7    =  "K:mm a, z";                       //->>  0:00 PM, PST 
  public static final String STYLE_DATE8    =  "yyyyy.MMMMM.dd GGG hh:mm aaa";    //->>  1996.July.10 AD 12:08 PM
  public static final String STYLE_DATE9    =  "yyyy/MM/dd";              // 2005/03/12
  public static final String STYLE_DATE10   =  "yyyy/MM/dd/HH:mm:ss";         // 2005/03/12/20:30:00
  public static final String STYLE_DATE11   =  "yyyyMM"; //201311
  public static final String STYLE_DATE12   =  "yyyyMMdd";
  
  
  public static final String STYLE_TIME   =  "hh:mm:ss";
  public static final String STYLE_FULL   =  "yyyy.MM.dd G 'at' hh:mm:ss z";   // ->>  1996.07.10 AD at 15:08:56 PDT
	public static void main(String[] args){
		System.out.println("当前毫秒数"+System.currentTimeMillis());
		System.out.println("***"+todayStartMillis());
		System.out.println("***"+todayEndMillis());
		System.out.println("***"+monthStartMillis());
		System.out.println("***"+monthEndMillis());
	}
	
	/**
	 * 日期字符串转毫秒数
	 * @param date
	 * @return
	 */
	public static long format(String date){
		if(null != date && !date.equals("")){
			try {
				return sdf.parse(date).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return 0l;
	}
	
	/**
	 * 获取当天0点的毫秒数
	 * @return
	 */
	public static long todayStartMillis(){
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return (cal.getTimeInMillis());
	}
	/**
	 * 获取当天24点的毫秒数
	 * @return
	 */
	public static long todayEndMillis(){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 24); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return (cal.getTimeInMillis()); 
	} 
	/**
	 * 获取指定日期0点的毫秒数
	 * @return
	 */
	public static long dateStartMillis(Long date){
		Calendar cal = Calendar.getInstance(); 
		cal.setTimeInMillis(date);
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return (cal.getTimeInMillis());
	}
	/**
	 * 获取指定日期24点的毫秒数
	 * @return
	 */
	public static long dateEndMillis(Long date){ 
		Calendar cal = Calendar.getInstance(); 
		cal.setTimeInMillis(date);
		cal.set(Calendar.HOUR_OF_DAY, 24); 
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MINUTE, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return (cal.getTimeInMillis()); 
	} 
	/**
	 * 获取当月0点的毫秒数
	 * @return
	 */
	public static long monthStartMillis(){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH)); 
		return (cal.getTimeInMillis()); 
	} 
	/**
	 * 获取当月24点的毫秒数
	 * @return
	 */
	public static long monthEndMillis(){ 
		Calendar cal = Calendar.getInstance(); 
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		cal.set(Calendar.HOUR_OF_DAY, 24); 
		return (cal.getTimeInMillis()); 
	} 
	
	

	/**
	 * 返回两个日期相差的天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 相差天数
	 */
	public static int getDateSpace(Long startDate, Long endDate) {
		Calendar calst = Calendar.getInstance();
		;
		Calendar caled = Calendar.getInstance();

		calst.setTimeInMillis(startDate);
		caled.setTimeInMillis(endDate);

		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}

}
