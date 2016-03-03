package com.zgxcw.springboot.framework.mongodb.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.zgxcw.springboot.framework.mongodb.model.MongoSystemException;
import com.zgxcw.springboot.framework.mongodb.service.MongoService;
import com.zgxcw.springboot.framework.utils.DateUtil;

@Aspect
@Configuration
public class MongoAspect {
	@Autowired
	private MongoService mongoService;
	private SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.STYLE_TIMESTAMP24);
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
		MongoSystemException exception = new MongoSystemException();
		exception.set_id(UUID.randomUUID().toString().replace("-", ""));
		exception.setAppName("B2B新平台");
		exception.setClassName(point.getTarget().getClass().getName());
		exception.setModelName("省市维护");
		exception.setMethodName(point.getSignature().getName());
		exception.setParameters(getArgString(point.getArgs()));
		exception.setExceptionTrace(getExceptionString());
		exception.setExceptionTime(sdf.format(new Date()));
		mongoService.insert(exception);
	}
	
	private String getArgString(Object[] args){
		String str = "";
		if(null != args){
			for (Object object : args) {
				str += object.toString() + ";";
			}
		}
		return str;
	}
	
	/**
	 * 模拟异常
	 * @return
	 * @author huangke
	 * @date 2016年2月2日 下午2:46:26
	 */
	private String getExceptionString(){
		try {
			return sdf.parse("-_-").toString();
		} catch (Exception e) {
			return getStackTrace(e);
		}
	}
	
	/**
     * 将异常堆栈转换为字符串
     * @param aThrowable 异常
     * @return String
     */
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString().replace("\r\n", "<br/>");
    }
}
