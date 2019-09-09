package com.hyxy.dao;

import com.hyxy.entity.Jurisdiction;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JurisdictionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Jurisdiction record);

    Jurisdiction selectByPrimaryKey(Integer id);

    List<Jurisdiction> selectAll();

    int updateByPrimaryKey(Jurisdiction record);
}