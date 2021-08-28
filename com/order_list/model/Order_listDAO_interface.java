package com.order_list.model;

import java.util.List;
import java.util.Map;

import com.morder.model.MorderVO;
import com.product.model.ProductVO;
import com.productspec.model.ProductSpecVO;

public interface Order_listDAO_interface {
	void addOrderList(Order_listVO orderList);
	void updateOrderList(Order_listVO orderList);
	Order_listVO getOneOrderList(Integer orderListSN);
	List<Order_listVO> getOrderListByOrder(Integer orderSN); // �Q��orderSN����orderlist 
	List<Order_listVO> getAllOrderList();
	List<ProductSpecVO> orderListGetInfo(List<Order_listVO> orderList); // �Q��orderlist���첣�~����
	List<Order_listVO> specGetOrderlist(Integer productSpecId); // �Q�β��~���Ӯ���q�����
}
