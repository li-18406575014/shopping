package com.hyxy.dao;

import com.hyxy.entity.Userxx;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserxxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userxx record);

    Userxx selectByPrimaryKey(Integer id);

    List<Userxx> selectAll();

    int updateByPrimaryKey(Userxx record);

	Userxx selectLogin(Map<String, String> map);

	Userxx getUserName(String username);

	//查询用户对应的角色
	Set<String> getRole(String username);

	//查询用户对应角色的权限
	Set<String> getPermissions(String username);
}