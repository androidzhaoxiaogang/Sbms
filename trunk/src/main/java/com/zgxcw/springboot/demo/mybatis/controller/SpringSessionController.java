package com.zgxcw.springboot.demo.mybatis.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zgxcw.springboot.framework.utils.DateUtil;

/**
 * spring session测试用例
 * @description 
 * @author liangkq
 * @date 2016年2月24日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
@Controller
@RequestMapping("/session")
public class SpringSessionController { 
	
	private SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.STYLE_TIMESTAMP24);
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request ,Model model){
		HttpSession session = request.getSession();
		//sessionId
		String sessionId = session.getId();
		//最大生存时间
		int maxInactiveInterval = session.getMaxInactiveInterval();
		long lastAccTime=session.getLastAccessedTime();
		long ctime = session.getCreationTime();
		String lastAccTimeStr = sdf.format(new Date(lastAccTime));
		String creationTime = sdf.format(new Date(ctime));
		Enumeration<String> names = session.getAttributeNames();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		while(names.hasMoreElements()){
			Map<String ,Object > map = new HashMap<String, Object>();
			String name = names.nextElement();
			Object value = session.getAttribute(name);
			map.put("key", name);
			map.put("value",value);
			list.add(map);
		}
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("lastAccTime", lastAccTimeStr);
		model.addAttribute("maxInactiveInterval", maxInactiveInterval);
		model.addAttribute("creationTime", creationTime);
		model.addAttribute("list", list);
		
		return "session/index";
	}

	@RequestMapping("/set")
	public String set(String sessionKey ,String sessionValue ,String time,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute(sessionKey, sessionValue);
		if(time != null && !"".equals(time)){
			session.setMaxInactiveInterval(Integer.parseInt(time));
		}
		return "redirect:index "; 
	}
	@RequestMapping("/remove")
	public String remove(HttpServletRequest request,String sessionKey){
		HttpSession session = request.getSession();
		session.removeAttribute(sessionKey);
		return "redirect:index "; 
	}
	@RequestMapping("/quit")
	public String quit(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:index "; 
	}
}
