package com.hyxy.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.hyxy.entity.Commodity;

public interface CommodityDao extends ElasticsearchRepository<Commodity, Integer>{



}

