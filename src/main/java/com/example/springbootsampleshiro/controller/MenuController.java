/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.controller
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springbootsampleshiro.domain.Menu;
import com.example.springbootsampleshiro.service.IMenuService;

/**
 * @ClassName: MenuController.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:28:49 
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
    /**
     * 查询子菜单
     * @param session
     * @param response
     * @param menuId
     * @param model
     * @return
     */
    @RequestMapping(value="/queryChildMenus")
    @ResponseBody
    public List<Menu> queryChildMenus(HttpSession session, HttpServletResponse response, String menuId, Model model) {
    	List<Menu> childMenuList = menuService.queryChildMenus(menuId);			
    	return childMenuList;
    }
    
    /**
     * 查询一级菜单
     * @param session
     * @param response
     * @param menuId
     * @param model
     * @return
     */
    @RequestMapping(value="/querFirstMenus")
    @ResponseBody
    public List<Menu> querFirstMenus(HttpSession session, HttpServletResponse response, String menuId, Model model) {
    	List<Menu> childMenuList = menuService.queryFirstMenus();
    	return childMenuList;
    }
    
    /**
     * 查询所有菜单
     * @param session
     * @param response
     * @param menuId
     * @param model
     * @return
     */
    @RequestMapping(value="/querAllMenus")
    @ResponseBody
    public List<Menu> querAllMenus(HttpSession session, HttpServletResponse response, String menuId, Model model) {
    	List<Menu> childMenuList = menuService.querAllMenus();
    	return childMenuList;
    }
}
