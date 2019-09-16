package com.hyxy.dao;

import com.hyxy.entity.Jieshao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JieshaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Jieshao record);

    Jieshao selectByPrimaryKey(Integer id);

    List<Jieshao> selectAll();

    int updateByPrimaryKey(Jieshao record);

	Jieshao selectId(Integer id);

	void addIncreased(Map<String, Object> map);

	void updataJS(Map<String, Object> map);

	List<Jieshao> selectFenYe(Integer begin, Integer number);

}