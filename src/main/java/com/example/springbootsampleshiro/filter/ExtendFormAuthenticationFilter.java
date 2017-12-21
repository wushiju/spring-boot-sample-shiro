/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.filter 
 * @author: wusj   
 * @date: 2017年12月15日 下午3:21:56 
 */
package com.example.springbootsampleshiro.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springbootsampleshiro.service.IUserService;

/**
 * @ClassName: ExtendFormAuthenticationFilter.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月15日 下午3:21:56
 **/
public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = Logger.getLogger(ExtendFormAuthenticationFilter.class);

	@Autowired
	private IUserService userService;
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		log.info("--------------------------访问被拒绝[ExtendFormAuthenticationFilter.onAccessDenied]加载...");
		
		if (this.isLoginRequest(request, response)) {
			if (this.isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return this.executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				return true;
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
			}

			saveRequestAndRedirectToLogin(request, response);
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onLoginSuccess(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.subject.Subject, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		
		log.info("--------------------------登陆成功[ExtendFormAuthenticationFilter.onLoginSuccess]加载...");
		
//		String userName = token.getPrincipal().toString();
//		User user = userService.findUserByUserName(userName);
//		// ----------以上代码你可以删除-------------------
//		((HttpServletRequest) request).getSession().setAttribute("CURRENT_USER", user);

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {// 不是ajax请求
			issueSuccessRedirect(request, response);
		} else {
			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();
			out.println("{\"success\":true,\"message\":\"登入成功\"}");
			out.flush();
			out.close();
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onLoginFailure(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationException, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		
		log.info("--------------------------登陆失败[ExtendFormAuthenticationFilter.onLoginFailure]加载...");
		
		if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {// 不是ajax请求
			setFailureAttribute(request, e);
			return true;
		}
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String message = e.getClass().getSimpleName();
			if ("IncorrectCredentialsException".equals(message)) {
				out.println("{\"success\":false,\"message\":\"密码错误\"}");
			} else if ("UnknownAccountException".equals(message)) {
				out.println("{\"success\":false,\"message\":\"账号不存在\"}");
			} else if ("LockedAccountException".equals(message)) {
				out.println("{\"success\":false,\"message\":\"账号被锁定\"}");
			} else {
				out.println("{\"success\":false,\"message\":\"未知错误\"}");
			}
			out.flush();
			out.close();
		} catch (IOException e1) {
			log.error(e1);
		}
		return false;
	}
}
