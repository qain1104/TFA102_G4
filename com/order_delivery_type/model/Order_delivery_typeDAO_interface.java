package com.order_delivery_type.model;

import java.util.List;

public interface Order_delivery_typeDAO_interface {
	void addOrderDeliveryType(Order_delivery_typeVO type);
	void deleteOrderDeliveryType(Integer orderDeliveryTypeId);
	void updateOrderDeliveryType(Order_delivery_typeVO type);
	Order_delivery_typeVO getOneType(Integer orderDeliveryTypeId);
	List<Order_delivery_typeVO> getAllType();

}
