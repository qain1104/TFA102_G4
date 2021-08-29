package com.order_list.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.productspec.model.ProductSpecService;
import com.productspec.model.ProductSpecVO;

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
		dao.updateOrderList(orderList);
		return orderList;
	}
	
	public Order_listVO getOneOrderList(Integer orderListSN) {
		return dao.getOneOrderList(orderListSN);
	}
	
	public List<Order_listVO> getOrderListByOrder(Integer orderSN) {
		return dao.getOrderListByOrder(orderSN);
	}
	
	public List<Order_listVO> getAllOrderList(){
		return dao.getAllOrderList();
	}
	
	public List<ProductSpecVO> orderListGetInfo(List<Order_listVO> orderList){
		return dao.orderListGetInfo(orderList);
	}
	
	public List<Order_listVO> specGetOrderlist(Integer productSpecId){
		return dao.specGetOrderlist(productSpecId);
	}
	
	// ���~ --> ���~���� --> �q����� --> ���� : ���o�Ӳ��~����������
	public Integer getProductRate(Integer productSN) {
		ProductSpecService specService = new ProductSpecService();
		List<ProductSpecVO> specList = specService.getProductSpec(productSN);
		
		Integer totalRate = 0; // �q���~���Ӯ���q����ӦC��A�A�q�q����ӦC���rate�[�W�h
		Integer total = 0; // �[�`�U�ӭq����ӦC���ƶq
		for(ProductSpecVO specVO : specList) {
			List<Order_listVO> orderList = dao.specGetOrderlist(specVO.getProductSpecId());
			total += orderList.size();
			for(Order_listVO orderListVO : orderList) {
				totalRate += orderListVO.getProductRate();
			}
		}
		try {
			return totalRate/total;			
		} catch (NumberFormatException e) {
			return 0;
		} catch (ArithmeticException e) {
			return 0;
		}
	}
	
	// ����Ӳ��~�������ε���
	public List<Order_listVO> productRateReview(Integer productSN) {
		List<Order_listVO> rateReview = new ArrayList<Order_listVO>();
		ProductSpecService specService = new ProductSpecService();
		List<ProductSpecVO> specList = specService.getProductSpec(productSN);


		for(ProductSpecVO specVO : specList) {
			List<Order_listVO> orderList = dao.specGetOrderlist(specVO.getProductSpecId());
			
			for(Order_listVO list : orderList) {
				if(list.getProductFeedback() != null && list.getProductRate() != null) {
					rateReview.add(list);
				}
			}
		}
		return rateReview;
	}

	
}
