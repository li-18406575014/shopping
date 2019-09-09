package com.hyxy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("MyShiroController")
public class MyShiroController {

	@RequestMapping("add")
	public String add() {
		// 获取到Subject
		Subject subject = SecurityUtils.getSubject();
		Boolean b = subject.isPermitted("add");
		if (b == true) {
			return "index";
		} else {
			return "redirect:/unauthor.jsp";
		}
	}

	@RequestMapping("select")
	public String select() {
		// 获取到Subject
		Subject subject = SecurityUtils.getSubject();
		Boolean b = subject.isPermitted("select");
		if (b == true) {
			return "index";
		} else {
			return "redirect:/unauthor.jsp";
		}
	}

	@RequestMapping("student")
	public String student() {
		// 获取到Subject
		Subject subject = SecurityUtils.getSubject();
		Boolean b = subject.hasRole("编译员");
		if (b == true) {
			return "index";
		} else {
			return "redirect:/unauthor.jsp";
		}
	}

	@RequestMapping("teacher")
	public String teacher() {
		// 获取到Subject
		Subject subject = SecurityUtils.getSubject();
		Boolean b = subject.hasRole("管理员");
		if (b == true) {
			return "index";
		} else {
			return "redirect:/unauthor.jsp";
		}
	}
}
