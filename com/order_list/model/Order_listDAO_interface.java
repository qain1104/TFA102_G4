package com.order_list.model;

import java.util.List;

public interface Order_listDAO_interface {
	void addOrderList(Order_listVO orderList);
	void deleteOrderList(Integer orderListSN);
	void updateOrderList(Order_listVO orderList);
	Order_listVO getOneOrderList(Integer orderListSN);
	Order_listVO getOneOrderListByOrder(Integer orderSN);
	List<Order_listVO> getAllOrderList();

}
