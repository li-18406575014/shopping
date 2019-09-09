package com.hyxy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyxy.service.UserService;

@Controller
@RequestMapping("LoginContoller")
public class LoginContoller {
	@Resource
	private UserService UserService;

	@RequestMapping("login")
	@ResponseBody
	public Map<String, String> login(@RequestParam Map<String, String> map,HttpServletRequest request) {
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
//		Userxx userxx = UserService.selectLogin(map);
		Map<String, String> map2 = new HashMap<String, String>();
//		if (userxx != null) {
//			request.getSession().setAttribute("userxx", userxx);
			map2.put("message", "成功");
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(map.get("username"),map.get("password"));
//		}
		try {
			subject.login(token);
			return map2;
		} catch (Exception e) {
			map2.put("message", "");
			return map2;
		}
		
	}
	
	@RequestMapping("logining")
	public String logining() {
		System.out.println("wode");
		return "index";
	}

}
