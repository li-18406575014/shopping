package com.hyxy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hyxy.entity.Userxx;

public class Intercept implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getRequestURL());
		System.out.println(request.getServletPath());
		Userxx userxx = (Userxx) request.getSession().getAttribute("userxx");
		if (userxx == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("在controller控制器执行完后，呈现视图之前调用拦截器");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("controller控制器执行完后，呈现视图之后调用的拦载器，释放资源");
	}

}
