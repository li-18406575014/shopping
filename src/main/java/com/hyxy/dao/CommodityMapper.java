package com.hyxy.dao;

import com.hyxy.entity.Commodity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Commodity record);

    Commodity selectByPrimaryKey(Integer id);

    List<Commodity> selectAll();

    int updateByPrimaryKey(Commodity record);

    //添加商品到数据库
	void addCommodity(Map<String, Object> map);

	//修改数据
	void updataJS(Map<String, Object> map);

}