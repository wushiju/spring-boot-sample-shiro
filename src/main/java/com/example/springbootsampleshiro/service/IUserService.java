/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.service 
 * @author: wusj   
 * @date: 2017年12月7日 下午4:23:48 
 */
package com.example.springbootsampleshiro.service;

import com.example.springbootsampleshiro.domain.User;

/**   
 * @ClassName: IUserService.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:23:48 
 */
public interface IUserService {

	/**   
	 * @Description: 该函数的功能描述
	 *
	 * @param userName
	 * @return User
	 *
	 * @version: v1.0.0
	 * @author: wusj
	 * @date: 2017年12月8日 上午10:19:34 
	 */
	User findUserByUserName(String userName);

}
