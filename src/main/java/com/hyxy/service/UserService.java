package com.hyxy.service;

import java.util.Map;
import java.util.Set;

import com.hyxy.entity.Userxx;

public interface UserService {

	Userxx selectLogin(Map<String, String> map);

	Userxx getUserName(String username);

	Set<String> getRole(String username);

	Set<String> getPermissions(String username);

}
