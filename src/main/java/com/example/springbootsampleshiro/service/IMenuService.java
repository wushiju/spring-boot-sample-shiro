/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.service 
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.service;

import java.util.List;

import com.example.springbootsampleshiro.domain.Menu;

/**   
 * @ClassName: IMenuService.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:23:48
 */
public interface IMenuService {

	/**
	 * 查询一级菜单
	 * 
	 * @return
	 */
	List<Menu> queryFirstMenus();

	/**
	 * 查询子菜单
	 * 
	 * @param menuId
	 * @return
	 */
	List<Menu> queryChildMenus(String menuId);

	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Menu> querAllMenus();
}
