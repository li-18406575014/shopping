package com.hyxy.dao;

import com.hyxy.entity.Userxx;
import java.util.List;
import java.util.Map;

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
}