package com.hyxy.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hyxy.util.Intercept;

//@Configuration
public class InterceptConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用于排除拦截
		InterceptorRegistration addInterceptor = registry.addInterceptor(new Intercept());
		// 排除配置,排除所有静态文件和不拦截的路径
		addInterceptor.addPathPatterns("/**"); // 过滤全部请求
		// 排除配置,排除所有静态文件
//	       addInterceptor.excludePathPatterns("/**/*.jpg"); // jpg静态文件不拦截
//		addInterceptor.excludePathPatterns("/img/**"); // jpg静态文件不拦截
//		addInterceptor.excludePathPatterns("/userController/select"); // 配置不拦截的页面
		// 或
		 addInterceptor.excludePathPatterns("/**/*.jpg","/user/select");
		 addInterceptor.excludePathPatterns("/error","/LoginContoller/login","/userLogin","/css/**","/images/**","/img/**","/font/**","/js/**","/login.jsp");
	}
}
