package com.hyxy.service;

import java.util.List;
import java.util.Map;

import com.hyxy.entity.Jieshao;

public interface JieshaoService {

	List<Jieshao> selectAll();

	Jieshao selectId(Integer id);

	void addIncreased(Map<String, Object> map);

	void delectId(Integer id);

	void updataJS(Map<String, Object> map);

	List<Jieshao> selectFenYe(Integer begin, Integer number);



}
