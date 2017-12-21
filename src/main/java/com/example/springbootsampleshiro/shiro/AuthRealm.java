/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.shiro 
 * @author: wusj   
 * @date: 2017年12月8日 上午10:16:30 
 */
package com.example.springbootsampleshiro.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springbootsampleshiro.domain.Menu;
import com.example.springbootsampleshiro.domain.Role;
import com.example.springbootsampleshiro.domain.User;
import com.example.springbootsampleshiro.service.IUserService;

/**   
 * @ClassName: AuthRealm.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月8日 上午10:16:30 
 **/
public class AuthRealm extends AuthorizingRealm {

	private Logger log = Logger.getLogger(AuthRealm.class);
	
	@Autowired
	private IUserService userService;
	
	/* 授权
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("--------------------------授权认证[AuthRealm.doGetAuthorizationInfo]加载...");
		// 获取session中的用户
		User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
		List<String> permissions = new ArrayList<String>();
		Set<Role> roles = user.getRoles();
		if (roles.size() > 0) {
			for (Role role : roles) {
				Set<Menu> menus = role.getMenus();
				if (menus.size() > 0) {
					for (Menu menu : menus) {
						permissions.add(menu.getId());
					}
				}
			}
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		return info;
	}

	/* 登陆认证
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken aToken) throws AuthenticationException {
		log.info("--------------------------登陆认证[AuthRealm.doGetAuthenticationInfo]加载...");
		UsernamePasswordToken token = (UsernamePasswordToken) aToken;

		if (token.getUsername() == null || "".equals(token.getUsername())) {
			throw new AuthenticationException("用户名不能为空");
		}
		User user = userService.findUserByUserName(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException("用户名没找到");
		} else if (user.getPassword().equals(new String(token.getPassword()))) {
			return new SimpleAuthenticationInfo(user.getUser_name(), user.getPassword(), this.getName());
		} else {
			throw new AuthenticationException("登陆认证失败");
		}
	}
	
	
}
