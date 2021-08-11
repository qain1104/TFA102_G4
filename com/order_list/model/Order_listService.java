package com.order_list.model;

import java.util.List;

public class Order_listService {
	private Order_listDAO_interface dao;
	
	public Order_listService() {
		dao = new Order_listDAO();
	}
	
	public Order_listVO addOrderList(Integer productSpecId, Integer orderSN, Integer orderCost,
			Integer purchaseQuantity, Integer productRate, String productFeedback) {
		
		Order_listVO orderList = new Order_listVO();
		orderList.setProductSpecId(productSpecId);
		orderList.setOrderSN(orderSN);
		orderList.setOrderCost(orderCost);
		orderList.setPurchaseQuantity(purchaseQuantity);
		orderList.setProductRate(productRate);
		orderList.setProductFeedback(productFeedback);
		dao.addOrderList(orderList);
		return orderList;
	}
	
	public Order_listVO updateOrderList(Integer orderListSN, Integer productSpecId, Integer orderSN, 
			Integer orderCost, Integer purchaseQuantity, Integer productRate, String productFeedback) {
		
		Order_listVO orderList = new Order_listVO();
		orderList.setOrderListSN(orderListSN);
		orderList.setProductSpecId(productSpecId);
		orderList.setOrderSN(orderSN);
		orderList.setOrderCost(orderCost);
		orderList.setPurchaseQuantity(purchaseQuantity);
		orderList.setProductRate(productRate);
		orderList.setProductFeedback(productFeedback);
		dao.addOrderList(orderList);
		return orderList;
	}
	
	public void deleteOrderList(Integer orderListSN) {
		dao.deleteOrderList(orderListSN);
	}
	
	public Order_listVO getOneOrderList(Integer orderListSN) {
		return dao.getOneOrderList(orderListSN);
	}
	
	public Order_listVO getOneOrderListByOrder(Integer orderSN) {
		return dao.getOneOrderListByOrder(orderSN);
	}
	
	public List<Order_listVO> getAllOrderList(){
		return dao.getAllOrderList();
	}
}
