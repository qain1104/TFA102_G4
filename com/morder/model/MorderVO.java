package com.morder.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;

import com.sun.org.apache.bcel.internal.generic.Type;

public class MorderVO implements Serializable{
	private Integer orderSN;
	private Integer userId;
	private Integer couponId;
	private Timestamp purchaseDate;
	private Integer totalPrice;
	private Integer orderPayment;
	private String orderCard;
	private String orderCardYear;
	private String orderCardMonth;
	private Timestamp orderCompleteDate;
	private Integer orderDeliveyTypeId;
	private String receiver;
	private String receiverPhone;
	private String receiverAddress;
	private Integer storeId;
	private String storeName;
	private String storeAddress;
	private Timestamp shippingDate;
	private Timestamp deliveryDate;
	private Integer deliveryStatus;
	
	public MorderVO() {
		super();
	}
	
	
	
	public MorderVO(Integer orderSN, Integer userId, Integer couponId, Timestamp purchaseDate, 
			Integer totalPrice, Integer orderPayment, String orderCard, String orderCardYear, 
			String orderCardMonth, Timestamp orderCompleteDate, Integer orderDeliveyTypeId, 
			String receiver, String receiverPhone, String receiverAddress, Integer storeId, 
			String storeName, String storeAddress, Timestamp shippingDate, Timestamp deliveryDate, 
			Integer deliveryStatus) {
		super();
		this.orderSN = orderSN;
		this.userId = userId;
		this.couponId = couponId;
		this.purchaseDate = purchaseDate;
		this.totalPrice = totalPrice;
		this.orderPayment = orderPayment;
		this.orderCard = orderCard;
		this.orderCardYear = orderCardYear;
		this.orderCardMonth = orderCardMonth;
		this.orderCompleteDate = orderCompleteDate;
		this.orderDeliveyTypeId = orderDeliveyTypeId;
		this.receiver = receiver;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.shippingDate = shippingDate;
		this.deliveryDate = deliveryDate;
		this.deliveryStatus = deliveryStatus;
	}
    
	public MorderVO(Integer userId, Integer couponId, Timestamp purchaseDate, Integer totalPrice, 
			Integer orderPayment, String orderCard, String orderCardYear, String orderCardMonth, 
			Timestamp orderCompleteDate, Integer orderDeliveyTypeId, String receiver, 
			String receiverPhone, String receiverAddress, Integer storeId, String storeName, 
			String storeAddress, Timestamp shippingDate, Timestamp deliveryDate, 
			Integer deliveryStatus) {
		super();
		this.userId = userId;
		this.couponId = couponId;
		this.purchaseDate = purchaseDate;
		this.totalPrice = totalPrice;
		this.orderPayment = orderPayment;
		this.orderCard = orderCard;
		this.orderCardYear = orderCardYear;
		this.orderCardMonth = orderCardMonth;
		this.orderCompleteDate = orderCompleteDate;
		this.orderDeliveyTypeId = orderDeliveyTypeId;
		this.receiver = receiver;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.shippingDate = shippingDate;
		this.deliveryDate = deliveryDate;
		this.deliveryStatus = deliveryStatus;
	}
	 
	

	public Integer getOrderSN() {
		return orderSN;
	}

	public void setOrderSN(Integer orderSN) {
		this.orderSN = orderSN;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(Integer orderPayment) {
		this.orderPayment = orderPayment;
	}

	public String getOrderCard() {
		return orderCard;
	}

	public void setOrderCard(String orderCard) {
		this.orderCard = orderCard;
	}

	public String getOrderCardYear() {
		return orderCardYear;
	}

	public void setOrderCardYear(String orderCardYear) {
		this.orderCardYear = orderCardYear;
	}

	public String getOrderCardMonth() {
		return orderCardMonth;
	}

	public void setOrderCardMonth(String orderCardMonth) {
		this.orderCardMonth = orderCardMonth;
	}

	public Timestamp getOrderCompleteDate() {
		return orderCompleteDate;
	}

	public void setOrderCompleteDate(Timestamp orderCompleteDate) {
		this.orderCompleteDate = orderCompleteDate;
	}

	public Integer getOrderDeliveyTypeId() {
		return orderDeliveyTypeId;
	}

	public void setOrderDeliveyTypeId(Integer orderDeliveyTypeId) {
		this.orderDeliveyTypeId = orderDeliveyTypeId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Timestamp getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Timestamp shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Integer getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getStoreAddress() {
		return storeAddress;
	}
	
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	@Override
	public String toString() {
		return "MorderVO" + "\n" + "[orderSN=" + orderSN + ", userId=" + userId + ", couponId=" + couponId + ", purchaseDate="
				+ purchaseDate + ", totalPrice=" + totalPrice + ", orderPayment=" + orderPayment + ", orderCard="
				+ orderCard + ", orderCardYear=" + orderCardYear + ", orderCardMonth=" + orderCardMonth
				+ ", orderCompleteDate=" + orderCompleteDate + ", orderDeliveyTypeId=" + orderDeliveyTypeId
				+ ", receiver=" + receiver + ", receiverPhone=" + receiverPhone + ", receiverAddress=" + receiverAddress
				+ ", storeId=" + storeId + ", storeName=" + storeName + ", storeAddress=" + storeAddress
				+ ", shippingDate=" + shippingDate + ", deliveryDate=" + deliveryDate + ", deliveryStatus="
				+ deliveryStatus + "]" + "\n";
	}

	
}
