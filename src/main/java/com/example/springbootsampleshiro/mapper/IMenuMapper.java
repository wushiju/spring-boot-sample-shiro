/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.mapper
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.mapper;

import java.util.List;

import com.example.springbootsampleshiro.domain.Menu;

/**
 * @ClassName: IMenuMapper.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:26:25 
 */
public interface IMenuMapper {

	/**
	 * 查询一级菜单
	 * @return
	 */
	List<Menu> queryFirstMenus();
	
	List<Menu> queryChildMenus(String menuId);
}
