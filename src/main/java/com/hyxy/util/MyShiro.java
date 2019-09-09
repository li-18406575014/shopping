package com.hyxy.util;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hyxy.entity.Userxx;
import com.hyxy.service.UserService;

public class MyShiro extends  AuthorizingRealm{

	@Resource
	private UserService UserService;
	
	//用户权限验证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		// 获取到当前登录用户的认证信息
	      SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	      // 给用户授权角色
	      authorizationInfo.setRoles(UserService.getRole(username));
	   // 给用户授权权限
	      authorizationInfo.setStringPermissions(UserService.getPermissions(username));
	      System.out.println(UserService.getRole(username));
	      System.out.println(UserService.getPermissions(username));
	      return authorizationInfo;
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
