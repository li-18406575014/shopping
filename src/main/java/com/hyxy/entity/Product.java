package com.hyxy.entity;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "shopping1", type = "product")
//indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
//type类型 可以理解为表名
public class Product {

	private Integer id;
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String productName;

	private String productImg;

	private String productPrice;

	private Date createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Product(Integer id, String productName, String productImg, String productPrice, Date createtime) {
		super();
		this.id = id;
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.createtime = createtime;
	}

	public Product() {
		super();
	}
}
