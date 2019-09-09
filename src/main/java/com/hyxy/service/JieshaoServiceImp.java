package com.hyxy.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hyxy.dao.JieshaoMapper;
import com.hyxy.entity.Jieshao;

@Service
public class JieshaoServiceImp implements JieshaoService {

	@Resource
	private JieshaoMapper JieshaoMapper;
	@Override
	public List<Jieshao> selectAll() {
		return JieshaoMapper.selectAll();
	}
	@Override
	public Jieshao selectId(Integer id) {
		return JieshaoMapper.selectId(id);
	}
	@Override
	public void addIncreased(Map<String, Object> map) {
		JieshaoMapper.addIncreased(map);
	}
	@Override
	public void delectId(Integer id) {
		JieshaoMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void updataJS(Map<String, Object> map) {
		JieshaoMapper.updataJS(map);
	}

}
