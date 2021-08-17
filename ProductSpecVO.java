package com.productspec.model;
import java.sql.Date;

public class ProductSpecVO implements java.io.Serializable{
	private Integer productSpecId;
	private Integer productSN;
	private Integer productStock;
	private Integer productPrice;
	private String productSpec;
	
	public ProductSpecVO() {
		super();		
	}

	public Integer getProductSpecId() {
		return productSpecId;
	}
	public void setProductSpecId(Integer productSpecId) {
		this.productSpecId = productSpecId;
	}
	public Integer getProductSN() {
		return productSN;
	}
	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	@Override
	public String toString() {
		return "ProductSpecVO [productSpecId=" + productSpecId + ", productSN=" + productSN + ", productStock="
				+ productStock + ", productPrice=" + productPrice + ", productSpec=" + productSpec + "]";
	}
	
	
}
