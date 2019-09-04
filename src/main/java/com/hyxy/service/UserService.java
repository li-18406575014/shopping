package com.hyxy.service;

import java.util.Map;

import com.hyxy.entity.Userxx;

public interface UserService {

	Userxx selectLogin(Map<String, String> map);

	Userxx getUserName(String username);

}
