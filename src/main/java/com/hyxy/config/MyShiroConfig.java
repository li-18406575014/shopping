package com.hyxy.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hyxy.util.MyShiro;

@Configuration
public class MyShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		// 加载管理器
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 未登录跳转到的页面或url，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login.jsp");
		// 未授权跳转到的页面或url
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthor.jsp");

		// 添加shiro内置过滤器
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 配置不需要验证过滤器
//		map.put("/", "anon");
		map.put("/LoginContoller/login", "anon");
		map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/img/**", "anon");
		map.put("/font/**", "anon");
		map.put("/images/**", "anon");
		map.put("/error", "anon");
//		map.put("/unauthor.jsp", "anon");

		// 需要登录验证的过滤器
		//      map.put("/admin", "authc");

		// 角色授权过滤器,必须拥有该角色才能访问,多个参数时写 roles["admin,user"]
		/*
		 * 
		 * 
		 * // 权限授权过滤器,必须拥有该权限，才能访问 
		 */
//		map.put("/student", "add");
//		map.put("/teacher", "select");
		// 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		// 保证除定义url外的所有url都必须是已登录的


		// 角色授权过滤器,必须拥有该角色才能访问,多个参数时写 roles["admin,user"]
//	      map.put("/student", "roles[管理员]");
//	      map.put("/teacher", "roles[编辑员]");
	      
	      // 权限授权过滤器,必须拥有该权限，才能访问
//	      map.put("/add", "perms[add]");
//	      map.put("/select", "perms[select]");
	      
		map.put("/**", "user");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	/**
	 * 注入 securityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置自定义realm,不能直接 new必须要使用 @Bean 注入 MyRealm，
		securityManager.setRealm(myShiro());
		return securityManager;
	}

	/**
	 * 自定义身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 */
	@Bean
	public MyShiro myShiro() {
		MyShiro myShiro = new MyShiro();
		return myShiro;
	}

}