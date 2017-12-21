/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.controller
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: SystemController.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:29:08
 */
@Controller
public class SystemController {

	@RequestMapping("/")
	public String index() {
		return "login";
	}
}
