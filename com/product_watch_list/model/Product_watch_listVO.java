package com.product_watch_list.model;

import java.io.Serializable;

public class Product_watch_listVO implements Serializable{
	private Integer pwlSN;
	private Integer productSN;
	private Integer userId;	
	
	
	
	public Product_watch_listVO() {
		super();
	}
	
	public Product_watch_listVO(Integer productSN, Integer userId) {
		super();
		this.productSN = productSN;
		this.userId = userId;
	}

	public Product_watch_listVO(Integer pwlSN, Integer productSN, Integer userId) {
		super();
		this.pwlSN = pwlSN;
		this.productSN = productSN;
		this.userId = userId;
	}
	
	public Integer getPwlSN() {
		return pwlSN;
	}
	public void setPwlSN(Integer pwlSN) {
		this.pwlSN = pwlSN;
	}
	public Integer getProductSN() {
		return productSN;
	}
	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Product_Watch_ListVO" + "\n" + "[pwlSN=" + pwlSN + ", productSN=" + productSN + ", userId=" + userId + "]" + "\n";
	}
}
