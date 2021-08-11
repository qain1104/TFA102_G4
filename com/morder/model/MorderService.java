package com.morder.model;

import java.sql.Timestamp;
import java.util.List;

public class MorderService {
	private MorderDAO_interface dao;
	
	public MorderService() {
		dao = new MorderDAO();
	}
	
	public MorderVO addMorder(Integer userId, Integer couponId, Timestamp purchaseDate, 
			Integer totalPrice, Integer orderPayment, String orderCard, String orderCardYear, 
			String orderCardMonth, Timestamp orderCompleteDate, Integer orderDeliveyTypeId, 
			String receiver, String receiverPhone, String receiverAddress, Integer storeId, 
			String storeName, String storeAddress, Timestamp shippingDate, Timestamp deliveryDate, 
			Integer deliveryStatus) {
		
		MorderVO morder = new MorderVO();
		morder.setUserId(userId);
		morder.setCouponId(couponId);
		morder.setPurchaseDate(purchaseDate);
		morder.setTotalPrice(totalPrice);
		morder.setOrderPayment(orderPayment);
		morder.setOrderCard(orderCard);
		morder.setOrderCardYear(orderCardYear);
		morder.setOrderCardMonth(orderCardMonth);
		morder.setOrderCompleteDate(orderCompleteDate);
		morder.setOrderDeliveyTypeId(orderDeliveyTypeId);
		morder.setReceiver(receiver);
		morder.setReceiverPhone(receiverPhone);
		morder.setReceiverAddress(receiverAddress);
		morder.setStoreId(storeId);
		morder.setStoreName(storeName);
		morder.setStoreAddress(storeAddress);
		morder.setShippingDate(shippingDate);
		morder.setDeliveryDate(deliveryDate);
		morder.setDeliveryDate(deliveryDate);
		dao.addMorder(morder);
		return morder;
	}
	
	public MorderVO updateMorder(Integer orderSN, Integer userId, Integer couponId, 
			Timestamp purchaseDate, Integer totalPrice, Integer orderPayment, String orderCard, 
			String orderCardYear, String orderCardMonth, Timestamp orderCompleteDate, 
			Integer orderDeliveyTypeId, String receiver, String receiverPhone, 
			String receiverAddress, Integer storeId, String storeName, String storeAddress, 
			Timestamp shippingDate, Timestamp deliveryDate, Integer deliveryStatus) {
		
		MorderVO morder = new MorderVO();
		morder.setOrderSN(orderSN);
		morder.setUserId(userId);
		morder.setCouponId(couponId);
		morder.setPurchaseDate(purchaseDate);
		morder.setTotalPrice(totalPrice);
		morder.setOrderPayment(orderPayment);
		morder.setOrderCard(orderCard);
		morder.setOrderCardYear(orderCardYear);
		morder.setOrderCardMonth(orderCardMonth);
		morder.setOrderCompleteDate(orderCompleteDate);
		morder.setOrderDeliveyTypeId(orderDeliveyTypeId);
		morder.setReceiver(receiver);
		morder.setReceiverPhone(receiverPhone);
		morder.setReceiverAddress(receiverAddress);
		morder.setStoreId(storeId);
		morder.setStoreName(storeName);
		morder.setStoreAddress(storeAddress);
		morder.setShippingDate(shippingDate);
		morder.setDeliveryDate(deliveryDate);
		morder.setDeliveryDate(deliveryDate);
		dao.updateMorder(morder);
		return morder;
	}
	
	public void deleteMorder(Integer orderSN) {
		dao.deleteMorder(orderSN);
	}
	
	public MorderVO getMorder(Integer orderSN) {
		return dao.getMorder(orderSN);
	}
	
	public List<MorderVO> getAllMorder() {
		return dao.getAllMorder();
	}
	
	public MorderVO getMorderByUser(Integer userId) {
		return dao.getMorderByUser(userId);
	}

}
