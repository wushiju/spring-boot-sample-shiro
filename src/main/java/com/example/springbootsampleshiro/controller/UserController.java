/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.controller
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springbootsampleshiro.domain.User;

/**
 * @ClassName: UserController.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:29:52
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger log = Logger.getLogger(UserController.class);

	@RequestMapping("/index")
	public String index() {
		log.info("用户管理主页...");
		return "user/index";
	}

//	@RequestMapping("/login")
//	public String login(String userName, String password) {
//		System.out.println("userName=" + userName);
//		System.out.println("password=" + password);
//		Map<String, Object> retMap = new HashMap<String, Object>();
//		try {
//			Subject subject = SecurityUtils.getSubject();
//			User user = (User) subject.getPrincipal();
//			if (user == null) {
//				retMap.put("retCode", "0001");
//				retMap.put("retMsg", "用户名或者密码错误！");
//			} else {
//				UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);
//				subject.login(upToken); // 完成登录
//			}
//		} catch (Exception e) {
//			retMap.put("retCode", "0001");
//			retMap.put("retMsg", "操作失败，请重试！");
//		}
//		return "main/index";
//	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String userName, String password) {
		log.info("用户登陆...");
		System.out.println("userName=" + userName);
		System.out.println("password=" + password);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Subject subject = SecurityUtils.getSubject();
			User user = (User) subject.getPrincipal();
			if (user == null) {
				retMap.put("retCode", "0001");
				retMap.put("retMsg", "用户名或者密码错误！");
			} else {
				UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);
				subject.login(upToken); // 完成登录
				retMap.put("retCode", "0000");
				retMap.put("retMsg", "登录成功！");
			}
		} catch (Exception e) {
			retMap.put("retCode", "0001");
			retMap.put("retMsg", "操作失败，请重试！");
		}
		return retMap;
	}
	
	
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("用户注销...");
		Map<String, Object> resMap = new HashMap<String, Object>(3);
		resMap.put("success", true);
		resMap.put("msg", "注销成功！");
		resMap.put("msgCode", "000000");
		return resMap;
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request, HttpServletResponse response) {
		log.info("用户新增...");
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		System.out.println("userCode=" + userCode);
		System.out.println("password=" + password);
		Map<String, Object> resMap = new HashMap<String, Object>(3);
		resMap.put("success", true);
		resMap.put("msg", "登陆成功！");
		resMap.put("msgCode", "000000");
		return resMap;
	}
}
