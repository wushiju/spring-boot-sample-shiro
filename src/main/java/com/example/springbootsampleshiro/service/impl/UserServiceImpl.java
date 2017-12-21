/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.service.impl 
 * @author: wusj   
 * @date: 2017年12月8日 上午10:20:00 
 */
package com.example.springbootsampleshiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsampleshiro.domain.User;
import com.example.springbootsampleshiro.mapper.IUserMapper;
import com.example.springbootsampleshiro.service.IUserService;

/**   
 * @ClassName: UserServiceImpl.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月8日 上午10:20:00 
 **/
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserMapper userMapper;
	
	/* (non-Javadoc)
	 * @see com.example.springbootsampleshiro.service.IUserService#findUserByUserId(java.lang.String)
	 */
	@Override
	public User findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUserName(userName);
	}

}
