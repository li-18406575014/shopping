package com.hyxy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyxy.dao.CommodityMapper;
import com.hyxy.entity.Commodity;

@Service
public class CommodityServiceImp implements CommodityService {

	@Autowired
	private CommodityMapper CommodityMapper;
	@Override
	public void addCommodity(Map<String, Object> map) {
		//添加商品
		CommodityMapper.addCommodity(map);
	}
	//查询数据库中的商品
	@Override
	public List<Commodity> servlceCommodity() {
		List<Commodity> list = CommodityMapper.selectAll();
		return list;
	}
	//根据id删除数据
	@Override
	public void delectId(Integer id) {
		CommodityMapper.deleteByPrimaryKey(id);
	}
	//根据id查询数据库中的数据
	@Override
	public Commodity selectId(Integer id) {
		return CommodityMapper.selectByPrimaryKey(id);
	}
	//修改数据
	@Override
	public void updataJS(Map<String, Object> map) {
		CommodityMapper.updataJS(map);
		
	}
}
