/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.service.impl
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsampleshiro.domain.Menu;
import com.example.springbootsampleshiro.mapper.IMenuMapper;
import com.example.springbootsampleshiro.service.IMenuService;

/**
 * @ClassName: MenuServiceImpl.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:25:59 
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuMapper menuMapper;
	
	@Override
	public List<Menu> queryFirstMenus() {
		return menuMapper.queryFirstMenus();
	}

	@Override
	public List<Menu> queryChildMenus(String menuId) {
		return menuMapper.queryChildMenus(menuId);
	}

	@Override
	public List<Menu> querAllMenus() {
		List<Menu> firstMenus = queryFirstMenus();
		for (Menu menu : firstMenus) {
			findChildren(menu);
		}
		return firstMenus;
	}
	
	private void findChildren(Menu menu) {
		List<Menu> menus = queryChildMenus(menu.getId());
		menu.setChildren(menus);
		if (menus.size() == 0) {
			menu.setState("undefined");
		} else {
			menu.setState("closed");
			for (Menu m : menus) {
				findChildren(m);
			}
		}
	}
}
