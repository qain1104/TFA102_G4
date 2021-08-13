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
	public ProductImageVO(Integer productImageSN, Integer productSN, byte[] productImage) {
		super();
		this.productImageSN = productImageSN;
		this.productSN = productSN;
		this.productImage = productImage;
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
	
}
