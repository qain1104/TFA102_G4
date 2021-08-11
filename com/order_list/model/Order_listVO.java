package com.order_list.model;

import java.io.Serializable;

public class Order_listVO implements Serializable{
	private Integer orderListSN;
	private Integer productSpecId;
	private Integer orderSN;
	private Integer orderCost;
	private Integer purchaseQuantity;
	private Integer productRate;
	private String productFeedback;
	
	public Order_listVO() {
		super();
	}

	public Order_listVO(Integer orderListSN, Integer productSpecId, Integer orderSN, Integer orderCost,
			Integer purchaseQuantity, Integer productRate, String productFeedback) {
		super();
		this.orderListSN = orderListSN;
		this.productSpecId = productSpecId;
		this.orderSN = orderSN;
		this.orderCost = orderCost;
		this.purchaseQuantity = purchaseQuantity;
		this.productRate = productRate;
		this.productFeedback = productFeedback;
	}

	public Order_listVO(Integer productSpecId, Integer orderSN, Integer orderCost, Integer purchaseQuantity,
			Integer productRate, String productFeedback) {
		super();
		this.productSpecId = productSpecId;
		this.orderSN = orderSN;
		this.orderCost = orderCost;
		this.purchaseQuantity = purchaseQuantity;
		this.productRate = productRate;
		this.productFeedback = productFeedback;
	}
	
	// -- builder pattern -- 
	
	private Order_listVO(Order_listVO.Builder builder) {
		productSpecId = builder.productSpecId;
		orderSN = builder.orderSN;
		orderCost = builder.orderCost;
		purchaseQuantity = builder.purchaseQuantity;
		productRate = builder.productRate;
		productFeedback = builder.productFeedback;
	}
	
	public static class Builder{
		private Integer orderListSN = new Integer(0);
		private Integer productSpecId = new Integer(0);
		private Integer orderSN = new Integer(0);
		private Integer orderCost = new Integer(0);
		private Integer purchaseQuantity = new Integer(0);
		private Integer productRate = new Integer(0);
		private String productFeedback = "";
		
		public Order_listVO.Builder setOrderListSN(Integer OrderListSN) {
			this.orderListSN = OrderListSN;
			return this;
		}
		
		public Order_listVO.Builder setProductSpecId(Integer productSpecId) {
			this.productSpecId = productSpecId;
			return this;
		}
		
		public Order_listVO.Builder setOrderSN(Integer orderSN) {
			this.orderSN = orderSN;
			return this;
		}
		
		public Order_listVO.Builder setOrderCost(Integer orderCost) {
			this.orderCost = orderCost;
			return this;
		}
		
		public Order_listVO.Builder setPurchaseQuantity(Integer purchaseQuantity) {
			this.purchaseQuantity = purchaseQuantity;
			return this;
		}
		
		public Order_listVO.Builder setPurductFeedback(String productFeedback) {
			this.productFeedback = productFeedback;
			return this;
		}
		
		public Order_listVO.Builder setPurductRate(Integer productRate) {
			this.productRate = productRate;
			return this;
		}
		
		public Order_listVO build() {
			return new Order_listVO(this);
		}
	}
	
	// -- builder pattern --

	public Integer getOrderListSN() {
		return orderListSN;
	}

	public void setOrderListSN(Integer orderListSN) {
		this.orderListSN = orderListSN;
	}

	public Integer getProductSpecId() {
		return productSpecId;
	}

	public void setProductSpecId(Integer productSpecId) {
		this.productSpecId = productSpecId;
	}

	public Integer getOrderSN() {
		return orderSN;
	}

	public void setOrderSN(Integer orderSN) {
		this.orderSN = orderSN;
	}

	public Integer getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(Integer orderCost) {
		this.orderCost = orderCost;
	}

	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Integer getProductRate() {
		return productRate;
	}

	public void setProductRate(Integer productRate) {
		this.productRate = productRate;
	}

	public String getProductFeedback() {
		return productFeedback;
	}

	public void setProductFeedback(String productFeedback) {
		this.productFeedback = productFeedback;
	}

	@Override
	public String toString() {
		return "Order_ListVO" + "\n" + "[orderListSN=" + orderListSN + ", productSpecId=" + productSpecId + ", orderSN=" + orderSN
				+ ", orderCost=" + orderCost + ", purchaseQuantity=" + purchaseQuantity + ", productRate=" + productRate
				+ ", productFeedback=" + productFeedback + "]" + "\n";
	}
	
	
}
