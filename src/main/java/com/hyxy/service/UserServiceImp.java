package com.hyxy.service;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hyxy.dao.UserxxMapper;
import com.hyxy.entity.Userxx;

@Service
public class UserServiceImp implements UserService {

	@Resource
	private UserxxMapper userxxMapper;

	//根据username和password在数据库中查询
	@Override
	public Userxx selectLogin(Map<String, String> map) {
		Userxx userxx = userxxMapper.selectLogin(map);
		return userxx;
	}

	//根据username在数据库中查询
	@Override
	public Userxx getUserName(String username) {
		Userxx userxx = userxxMapper.getUserName(username);
		return userxx;
	}

	//查询用户对应的角色
	@Override
	public Set<String> getRole(String username) {
		return userxxMapper.getRole(username);
	}

	//查询用户对应角色的权限
	@Override
	public Set<String> getPermissions(String username) {
		return userxxMapper.getPermissions(username);
	}
}
