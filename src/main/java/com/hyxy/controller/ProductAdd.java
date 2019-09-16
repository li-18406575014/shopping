package com.hyxy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyxy.entity.Product;
import com.hyxy.es.ProductDao;

@Controller
public class ProductAdd {

	@Resource
	private ProductDao productDao;

	// 添加文档
	@RequestMapping("add")
	public void add() {
//        List<Product> list = new ArrayList<>();
//        list.add(new Product(1, "华为", "http://image.baidu.com/13123.jpg", "32", new Date()));
//        list.add(new Product(2, "苹果", "http://image.baidu.com/13123.jpg", "32", new Date()));

		// 新增一个对象，把对象转成json格式传入到es数据库中
//       productDao.save(new Product(3, "小米手机7", "http://image.baidu.com/13123.jpg", "32", new Date()));

		// 批量新增
//       productDao.saveAll(list);
		// System.out.println("成功");

		System.out.println("-----------------------------------查询单个数据------------------------------------");
		// 查询单个数据
		Product product = new Product();
		product = productDao.findById(1).get();
		System.out.println(product.getId());

		// 查询所有数据
		System.out.println("-----------------------------------查询所有数据------------------------------------");
		Iterable<Product> lists = productDao.findAll();
		for (Product product2 : lists) {
			System.out.println(product2.getId());
		}

		// 分页+排序
		System.out.println("-----------------------------------分页+排序------------------------------------");
		// 构建查询条件
		NativeSearchQueryBuilder nqb = new NativeSearchQueryBuilder();
		nqb.withQuery(QueryBuilders.matchAllQuery());
		// 分页：page--当前第几页，size---每页多少条
		Integer page = 0;
		Integer size = 2;
		nqb.withPageable(PageRequest.of(page, size));
		// 排序
		nqb.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
		// 搜索，获取结果
		Page<Product> pages = productDao.search(nqb.build());
		Long total = pages.getTotalElements();
		System.out.println("总条数 = " + total);
		// 总页数
		System.out.println("总页数 = " + pages.getTotalPages());
		// 当前页
		System.out.println("当前页：" + pages.getNumber());
		// 每页大小
		System.out.println("每页条数：" + pages.getSize());
		System.out.println("-----------------------------------使用foreach打印------------------------------------");
		for (Product product2 : pages) {
			System.out.println(product2.getId() + "----" + product2.getProductName());
		}
		System.out.println("-----------------------------------转化为list打印------------------------------------");
		List<Product> list = pages.getContent();
		for (Product product2 : list) {
			System.out.println(product2.getId() + "----" + product2.getProductName());
		}
		System.out.println("-----------------------------------使用迭代器打印------------------------------------");
		Iterator<Product> iterator = pages.iterator();
		while (iterator.hasNext()) {
			product = iterator.next();
			System.out.println(product.getId() + "----" + product.getProductName());
		}
		// 指定查询字段
		System.out.println("-----------------------------------指定查询字段------------------------------------");
		nqb.withFields("id", "productName");
		// 搜索，获取结果
		Page<Product> pages1 = productDao.search(nqb.build());
		// 循环获取数据
		for (Product product1 : pages1) {
			System.out.println(product1.getId() + "----" + product1.getProductName());
		}
		// 条件查询
		System.out.println("-----------------------------------条件查询1------------------------------------");
		// 添加查询条件
		/**
		 * matchQuery---使用默认分词器对搜索词进行一个一个分词， 搜索词分词后与目标查询字段进行匹配，若分词中的任意一个词与目标字段匹配成功则可查询到。
		 * 
		 * termQuery---精确查询，不会对搜索词进行分词处理 作为一个整体与目标字段进行匹配，若完全匹配则可查询到。
		 * 
		 * fuzzyQuery---模糊查询，不会对搜索词进行分词处理，包含即可 只要目标字段中包含搜索词则可查询到。 如果搜索词为两个或多个字，也看作一个整体
		 */
//		nqb.withQuery(QueryBuilders.matchQuery("productName", "小手"));
//		nqb.withQuery(QueryBuilders.termQuery("productName", "小"));
		nqb.withQuery(QueryBuilders.fuzzyQuery("productName", "小"));
		// 查询
		Page<Product> page1 = productDao.search(nqb.build());
		// 循环获取数据
		for (Product product1 : page1) {
			System.out.println(product1.getProductName());
		}
		System.out.println("-----------------------------------条件查询2------------------------------------");
		/**
		 * bool联合查询 must: 文档必须完全匹配条件 :AND should: 至少满足一个条件 :OR mustNot: 文档必须不匹配条件:NOT
		 */
		nqb.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("productName", "手"))
				.must(QueryBuilders.matchQuery("productPrice", "32")));
		Page<Product> page2 = productDao.search(nqb.build());

		// 循环获取数据
		for (Product product1 : page2) {
			System.out.println(product1.getProductName() + "---" + product1.getId());
		}

		System.out.println("-----------------------------------条件查询3------------------------------------");
		// 小于
		RangeQueryBuilder lte = QueryBuilders.rangeQuery("productPrice").lte(39);
		// 大于
		RangeQueryBuilder gte = QueryBuilders.rangeQuery("productPrice").gte(30);
		/**
		 * filter---过滤查询
		 */
		nqb.withQuery(QueryBuilders.boolQuery().filter(lte).filter(gte));
		// 查询
		Page<Product> page3 = productDao.search(nqb.build());
		// 循环获取数据
		for (Product product3 : page3) {
			System.out.println(product3.getProductName());
		}

		System.out.println("-----------------------------------使用中文分词器------------------------------------");
		/**
		 * 使用中文分词器
		 */
		// 构建查询条件

		/**
		 * matchQuery---分词查询 .analyzer("ik_max_word")-----中文分词
		 * .analyzer("standard")-----系统默认分词
		 */
		// title字段---使用的是ik_max_word分词器,搜索内容也使用了ik_max_word分词器，分成了一个词"谁是"
		// 我 是 中国人 中国 国人
		nqb.withQuery(QueryBuilders.matchQuery("productName", "不是华为"));
		// category字段---使用的是standard系统默认分词,搜索内容也使用了默认分词器，分成单个汉字"谁""是"
		// queryBuilder.withQuery(QueryBuilders.matchQuery("category", "谁是"));

		// 对搜索内容指定分词器进行分词搜索
		// queryBuilder.withQuery(QueryBuilders.matchQuery("title","谁是").analyzer("standard"));
		// queryBuilder.withQuery(QueryBuilders.matchQuery("category",
		// "谁是").analyzer("ik_max_word"));

		// 查询
		Page<Product> page4 = productDao.search(nqb.build());

		// 循环获取数据
		for (Product product4 : page4) {
			System.out.println(product4.getProductName());
		}

		System.out.println("-----------------------------------使用中文分词器多字段搜索------------------------------------");
		/**
		 * 多字段搜索
		 */
		// 构建查询条件

		// 多字段搜索
		nqb.withQuery(QueryBuilders.multiMatchQuery("7", "productName", "productPrice"));

		// 查询
		Page<Product> page5 = productDao.search(nqb.build());

		// 循环获取数据
		for (Product product5 : page5) {
			System.out.println(product5.getProductName());
		}
	}
}
