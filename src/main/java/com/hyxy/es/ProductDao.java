package com.hyxy.es;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.hyxy.entity.Product;

@Mapper
public interface ProductDao extends ElasticsearchRepository<Product, Integer>{



}