package com.order_delivery_type.model;

import java.io.Serializable;

public class Order_delivery_typeVO implements Serializable{
	private Integer orderDeliveryTypeId;
	private String deliveryType;
	private Integer deliveryFee;
	

	public Order_delivery_typeVO() {
		super();
	}
	public Order_delivery_typeVO(String deliveryType, Integer deliveryFee) {
		super();
		this.deliveryType = deliveryType;
		this.deliveryFee = deliveryFee;
	}
	public Order_delivery_typeVO(Integer orderDeliveryTypeId, String deliveryType, 
			Integer deliveryFee) {
		super();
		this.orderDeliveryTypeId = orderDeliveryTypeId;
		this.deliveryType = deliveryType;
		this.deliveryFee = deliveryFee;
	}
	public Integer getOrderDeliveryTypeId() {
		return orderDeliveryTypeId;
	}
	public void setOrderDeliveryTypeId(Integer orderDeliveryTypeId) {
		this.orderDeliveryTypeId = orderDeliveryTypeId;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public Integer getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(Integer deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	@Override
	public String toString() {
		return "Order_Delivery_TypeVO" + "\n" + "[orderDeliveryTypeId : " + orderDeliveryTypeId + ", deliveryType : " + deliveryType
				+ ", deliveryFee : " + deliveryFee + "]" + "\n";
	}
}
