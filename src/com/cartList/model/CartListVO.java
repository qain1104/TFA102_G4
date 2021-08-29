package com.cartList.model;

import java.io.Serializable;

public class CartListVO implements Serializable{
	private Integer userId;
	private Integer productSpecId;
	private Integer itemQuantity;
	
	public CartListVO() {
		super();
	}

	public CartListVO(Integer userId, Integer productSpecId, Integer itemQuantity) {
		super();
		this.userId = userId;
		this.productSpecId = productSpecId;
		this.itemQuantity = itemQuantity;
	}
	
	public CartListVO(Integer productSpecId, Integer itemQuantity) {
		super();
		this.productSpecId = productSpecId;
		this.itemQuantity = itemQuantity;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductSpecId() {
		return productSpecId;
	}

	public void setProductSpecId(Integer productSpecId) {
		this.productSpecId = productSpecId;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Override
	public String toString() {
		return "CartList" + "\n" +"[userId=" + userId + ", productSpecId=" + productSpecId
				+ ", itemQuantity=" + itemQuantity + "]" + "\n";
	}

	
	
	
}
