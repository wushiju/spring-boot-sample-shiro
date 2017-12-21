/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.shiro 
 * @author: wusj   
 * @date: 2017年12月13日 下午3:44:21 
 */
package com.example.springbootsampleshiro.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName: ShiroLoginFilter.java
 * @Description: 功能描述
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月13日 下午3:44:21
 **/
public class ShiroLoginFilter {
//	public class ShiroLoginFilter extends AdviceFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.web.servlet.AdviceFilter#preHandle(javax.servlet.
	 * ServletRequest, javax.servlet.ServletResponse)
	 */
//	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
        if (StringUtils.isNotBlank(requestedWith) &&
                requestedWith.equals("XMLHttpRequest")) {//如果是ajax返回指定格式数据
        	Map<String, Object> result = new HashMap<String, Object>();
			result.put("flag", false);
			result.put("msg", "权限不足！");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(JSON.toJSONString(result));
        } else {//如果是普通请求进行重定向
            httpServletResponse.sendRedirect("/403");
        }
        return false;
	}
}
