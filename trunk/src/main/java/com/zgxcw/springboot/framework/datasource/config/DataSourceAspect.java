package com.zgxcw.springboot.framework.datasource.config;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.zgxcw.springboot.framework.datasource.annotation.DataSource;
import com.zgxcw.springboot.framework.datasource.enums.DataSourceType;

/**
 * 数据源切入
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月26日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
@Aspect
@Configuration
public class DataSourceAspect {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceDynamicRegister.class);
	
	/**
	 * 定义切入点
	 * 任意返回类型
	 * 对service包下所有方法进行拦劫
	 * @author huangke
	 * @date 2016年1月26日 下午10:36:32
	 */
	@Pointcut("execution(* com..service..impl.*.*(..))")
	private void any(){}//声明一个切入点
	
	/**
	 * 定义前置通知
	 * 参数为切入点的方法
	 * 增加附加条件得到参数,args(拦劫方法的参数名)
	 * @author huangke
	 * @date 2016年1月26日 下午10:36:32
	 */
	@Before("any()")
	public void beforeNotice(JoinPoint point){
		Object target = point.getTarget();
        String method = point.getSignature().getName();

        Class<?> clazz = target.getClass();//.getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz.getMethod(method, parameterTypes);
            if (null != m) {
            	if(m.isAnnotationPresent(DataSource.class)){
            		DataSource data = m.getAnnotation(DataSource.class);
                    DataSourceDynamicContextHolder.setDataSourceType(data.value());
                    if (data.value() == DataSourceType.READ) {
    					logger.info("切换到读库");
    					System.err.println("切换到读库");
    				}
                    else{
                    	logger.info("切换到主库");
    					System.err.println("切换到主库");
                    }
            	}else{
            		DataSourceDynamicContextHolder.setDataSourceType(DataSourceType.WRITE);
            		logger.info("切换到主库");
					System.err.println("切换到主库");
            	}
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.info("读写库切换异常" + e.getMessage());
        }

		
	}
	
	/**
	 * 定义后置通知
	 * 可以接收返回结果,如果接收返回结果,只能拦劫有返回结果的方法
	 * 如果没有返回结果@AfterRuturning("any()")
	 * @author huangke
	 * @date 2016年1月26日 下午10:36:32
	 */
	@AfterReturning(pointcut = "any()",returning="result")
	public void afterNotice(String result){
		//System.out.println("后置通知:"+result);
	}
	
	
	/**
	 * 定义最终通知
	 * @author huangke
	 * @date 2016年1月26日 下午10:36:32
	 */
	@After("any()")
	public void finallyNotice(){
		//System.out.println("最终通知");
	}
	
	/**
	 * 定义例外通知
	 * @author huangke
	 * @date 2016年1月26日 下午10:36:32
	 */
	@AfterThrowing(pointcut = "any()",throwing = "e")
	public void exceptionNotice(Exception e){
		System.err.println("拦劫到异常: "+e);
		logger.info(e.getMessage());
	}
	
	/**
	 * 定义环绕通知
	 * 固定参数不可变
	 * @author huangke
	 * @date 2016年1月26日 下午10:36:32
	 */
	@Around("any()")
	public Object aroundNotice(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = pjp.proceed(); //必须执行此方法,否则被拦劫方法不会执行
		return obj;
	}

}
