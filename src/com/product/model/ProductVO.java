package com.product.model;
import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable{
	private Integer productSN;
	private Integer corpUserId;
	private String productName;
	private Integer productClass;
	private String productDetail;
	private String productBrand;
	private Timestamp productOnsale;
	private Integer productStatus;
	
	public ProductVO() {
		super();		
	}
	
	public Integer getProductSN() {
		return productSN;
	}
	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}
	public Integer getCorpUserId() {
		return corpUserId;
	}
	public void setCorpUserId(Integer corpUserId) {
		this.corpUserId = corpUserId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductClass() {
		return productClass;
	}
	public void setProductClass(Integer productClass) {
		this.productClass = productClass;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public Timestamp getProductOnsale() {
		return productOnsale;
	}
	public void setProductOnsale(Timestamp productOnsale) {
		this.productOnsale = productOnsale;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	@Override
	public String toString() {
		return "ProductVO [productSN=" + productSN + ", corpUserId=" + corpUserId + ", productName=" + productName
				+ ", productClass=" + productClass + ", productDetail=" + productDetail + ", productBrand="
				+ productBrand + ", productOnsale=" + productOnsale + ", productStatus=" + productStatus + "]";
	}
	
}
