package com.hyxy.service;

import java.util.List;
import java.util.Map;

import com.hyxy.entity.Commodity;

public interface CommodityService {

	//添加商品
	void addCommodity(Map<String, Object> map);

	//查询数据库中的商品
	List<Commodity> servlceCommodity();

	//根据id删除数据
	void delectId(Integer id);

	//根据id查询数据库中的数据
	Commodity selectId(Integer id);
	
	//修改数据
	void updataJS(Map<String, Object> map);

}
