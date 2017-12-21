/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.controller
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootsampleshiro.service.IMenuService;

/**
 * @ClassName: MainController.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:28:39 
 */
@Controller
@RequestMapping("/main")
public class MainController {

	
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		return "main/index";
	}
}
