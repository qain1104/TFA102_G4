package com.productimage.model;
import java.sql.Blob;
import java.sql.Date;

public class ProductImageVO implements java.io.Serializable{
	private Integer productImageSN;
	private Integer productSN;
	private Blob productImage;
	
	public ProductImageVO() {
		super();		
	}

	public Integer getProductImageSN() {
		return productImageSN;
	}
	public void setProductImageSN(Integer productImageSN) {
		this.productImageSN = productImageSN;
	}
	public Integer getProductSN() {
		return productSN;
	}
	public void setProductSN(Integer productSN) {
		this.productSN = productSN;
	}
	public Blob getProductImage() {
		return productImage;
	}
	public void setProductImage(Blob productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "ProductImageVO [productImageSN=" + productImageSN + ", productSN=" + productSN + ", productImage="
				+ productImage + "]";
	}
	
	
	
}
