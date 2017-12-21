/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.shiro 
 * @author: wusj   
 * @date: 2017年12月8日 上午10:37:59 
 */
package com.example.springbootsampleshiro.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**   
 * @ClassName: CredentialsMatcher.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月8日 上午10:37:59 
 **/
public class CredentialsMatcher extends SimpleCredentialsMatcher {
//public class CredentialsMatcher {

	private Logger log = Logger.getLogger(CredentialsMatcher.class);
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.authc.credential.SimpleCredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		log.info("--------------------------自定义密码比较器[CredentialsMatcher.doCredentialsMatch]加载...");
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		// 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
		String inPassword = new String(utoken.getPassword());
		// 获得数据库中的密码
		String dbPassword = (String) info.getCredentials();
		// 进行密码的比对
		return this.equals(inPassword, dbPassword);
	}
}
