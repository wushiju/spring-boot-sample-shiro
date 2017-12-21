/**   
 * Copyright © 2017 wushiju@foxmail.com
 * 
 * @Package: com.example.springbootsampleshiro.shiro 
 * @author: wusj   
 * @date: 2017年12月7日 下午4:47:39 
 */
package com.example.springbootsampleshiro.shiro;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

/**   
 * @ClassName: ShiroConfiguration.java
 * @Description: Shiro配置类
 *
 * @version: v1.0.0
 * @author: wusj
 * @date: 2017年12月7日 下午4:47:39 
 **/
@Configuration
public class ShiroConfiguration {

	private Logger log = Logger.getLogger(ShiroConfiguration.class);
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		
		log.error("--------------------------shiro权限配置[ShiroConfiguration.shiroFilter]加载...");
		
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		
		// 配置登录的url和登录成功的url
		bean.setLoginUrl("/user/login.do");
		bean.setSuccessUrl("/main/index.do");
		
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/user/login", "authc");
		filterChainDefinitionMap.put("/user/logout", "anon");
		filterChainDefinitionMap.put("/*.do", "authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}
	
	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		log.error("--------------------------shiro安全事务管理器[ShiroConfiguration.securityManager]加载...");
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}
	
	// 配置自定义的权限登录器
	@Bean(name = "authRealm")
	public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
		log.error("--------------------------shiro自定义权限登陆器[ShiroConfiguration.authRealm]加载...");
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}
	
	// 配置自定义的密码比较器
	@Bean(name = "credentialsMatcher")
	public CredentialsMatcher credentialsMatcher() {
		log.error("--------------------------shiro自定义密码比较器[ShiroConfiguration.credentialsMatcher]加载...");
		return new CredentialsMatcher();
	}
	
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
    	log.error("--------------------------[ShiroConfiguration.lifecycleBeanPostProcessor]加载...");
        return new LifecycleBeanPostProcessor();
    }
    
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
    	log.error("--------------------------[ShiroConfiguration.DefaultAdvisorAutoProxyCreator]加载...");
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
    	log.error("--------------------------[ShiroConfiguration.authorizationAttributeSourceAdvisor]加载...");
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
