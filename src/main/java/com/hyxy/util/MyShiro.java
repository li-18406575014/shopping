package com.hyxy.util;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hyxy.entity.Userxx;
import com.hyxy.service.UserService;

public class MyShiro extends  AuthorizingRealm{

	@Resource
	private UserService UserService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	//用户登录验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		//根据username在数据库中查询
		Userxx userxx = UserService.getUserName(username);
		if (userxx != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userxx.getName(), userxx.getPassword(),"");
			return authcInfo;
		}else {
			return null;
		}
		
	}

}
