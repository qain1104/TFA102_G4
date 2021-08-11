package com.order_delivery_type.model;

import java.util.List;

public class Order_delivery_typeService {
	private Order_delivery_typeDAO_interface dao;
	
	public Order_delivery_typeService() {
		dao = new Order_delivery_typeDAO();
	}
	
	public Order_delivery_typeVO addOrderDeliveryType(String deliveryType, 
			Integer deliveryFee) {
		
		Order_delivery_typeVO type = new Order_delivery_typeVO();
		type.setDeliveryType(deliveryType);
		type.setDeliveryFee(deliveryFee);
		dao.addOrderDeliveryType(type);
		return type;
	}
	
	public Order_delivery_typeVO updateOrderDeliveryType(Integer orderDeliveryTypeId, 
			String deliveryType, Integer deliveryFee) {
		
		Order_delivery_typeVO type = new Order_delivery_typeVO();
		type.setOrderDeliveryTypeId(orderDeliveryTypeId);
		type.setDeliveryType(deliveryType);
		type.setDeliveryFee(deliveryFee);
		dao.addOrderDeliveryType(type);
		return type;
	}
	
	public void deleteOrderDeliveryType(Integer orderDeliveryTypeId) {
		dao.deleteOrderDeliveryType(orderDeliveryTypeId);
	}
	
	public Order_delivery_typeVO getOneType(Integer orderDeliveryTypeId) {
		return dao.getOneType(orderDeliveryTypeId);
	}
	
	public List<Order_delivery_typeVO> getAllType() {
		return dao.getAllType();
	}

}
