package com.productimage.model;
import java.sql.Blob;
import java.sql.Date;

public class ProductImageVO implements java.io.Serializable{
	private Integer productImageSN;
	private Integer productSN;
	private byte[] productImage;
	
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
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "ProductImageVO [productImageSN=" + productImageSN + ", productSN=" + productSN + ", productImage="
				+ productImage + "]";
	}
	
	
	
}
