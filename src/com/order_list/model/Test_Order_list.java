package com.order_list.model;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.product.model.ProductVO;
import com.productspec.model.ProductSpecVO;

public class Test_Order_list {	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		//addOrderList(Order_ListVO orderList)
//		Order_listVO orderList = new Order_listVO();
//		orderList.setProductSpecId(new Integer(12010));
//		orderList.setOrderSN(new Integer(16002));
//		orderList.setOrderCost(new Integer(390));
//		orderList.setPurchaseQuantity(new Integer(1));
//		Order_listDAO dao = new Order_listDAO();
//		dao.addOrderList(orderList);
//		System.out.println("Successfully");
		
		//Test Builder	
//		Order_listVO vo = new Order_listVO.Builder()
//			.setProductSpecId(new Integer(12011))
//			.setOrderSN(new Integer(16003))
//			.setOrderCost(new Integer(390))
//			.setPurchaseQuantity(new Integer(1))
//			.build();
//		Order_listDAO dao = new Order_listDAO();
//		dao.addOrderList(vo);
		
		//deleteOrderList(Integer orderListSN)
//		System.out.println("Please input ordeListSN you want to delete");
//		Integer orderListSN = sc.nextInt();
//		Order_listDAO dao = new Order_listDAO();
//		dao.deleteOrderList(orderListSN);
//		System.out.println("Successfully");
//		
		//updateOrderList(Order_ListVO orderList)
//		Order_listVO orderList = new Order_listVO();
//		orderList.setOrderListSN(new Integer(18010));
//		orderList.setProductSpecId(new Integer(12011));
//		orderList.setOrderSN(new Integer(16002));
//		orderList.setOrderCost(new Integer(390));
//		orderList.setPurchaseQuantity(new Integer(1));
//		orderList.setProductRate(new Integer(3));
//		orderList.setProductFeedback("§Ö³t±Æ¦½");
//		Order_listDAO dao = new Order_listDAO();
//		List<Order_listVO> list = dao.specGetOrderlist(12001);
//		System.out.println(list);
//		dao.updateOrderList(orderList);
//		System.out.println("Successfully");
		
		//getOneOrderList(Integer orderListSN)
//		System.out.println("Please input orderListSN you want to query");
//		Integer orderListSN = sc.nextInt();
//		Order_listDAO dao = new Order_listDAO();
//		System.out.println(dao.getOneOrderList(orderListSN));
		
		//getOrderListByOrder(Integer orderSN)
//		Order_listService service = new Order_listService();
//		List<Order_listVO> list = service.getOrderListByOrder(16001);
//		Map<ProductSpecVO, ProductVO> map = service.orderListGetInfo(list);
//		for(ProductSpecVO vo : map.keySet()) {
//			System.out.println(vo.getProductSpecId());
//		}
//		for(ProductVO vo : map.values()) {
//			System.out.println(vo.getProductName());
//		}

		
		//getAllOrderList()
//		Order_listDAO dao = new Order_listDAO();
//		List<Order_listVO> list = dao.getAllOrderList();
//		for(Order_listVO orderList : list) {
//			System.out.println(orderList);
//		}
		
		//getProductRate(Integer productSN)
		Order_listService se = new Order_listService();

//		System.out.println(se.getProductRate(11001));
	}

}
