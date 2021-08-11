package com.order_list.model;

import java.util.List;
import java.util.Scanner;

public class Test_Order_list {	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		//addOrderList(Order_ListVO orderList)
//		Order_listVO orderList = new Order_listVO();
//		orderList.setProductSpecId(new Integer(12010));
//		orderList.setOrderSN(new Integer(16002));
//		orderList.setOrderCost(new Integer(390));
//		orderList.setPurchaseQuantity(new Integer(1));
//		orderList.setProductRate(new Integer(3));
//		orderList.setProductFeedback("快速排汗");
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
//		orderList.setOrderListSN(new Integer(18006));
//		orderList.setProductSpecId(new Integer(12011));
//		orderList.setOrderSN(new Integer(16002));
//		orderList.setOrderCost(new Integer(390));
//		orderList.setPurchaseQuantity(new Integer(1));
//		orderList.setProductRate(new Integer(3));
//		orderList.setProductFeedback("快速排汗");
//		Order_listDAO dao = new Order_listDAO();
//		dao.updateOrderList(orderList);
//		System.out.println("Successfully");
		
		//getOneOrderList(Integer orderListSN)
//		System.out.println("Please input orderListSN you want to query");
//		Integer orderListSN = sc.nextInt();
//		Order_listDAO dao = new Order_listDAO();
//		System.out.println(dao.getOneOrderList(orderListSN));
		
		//getOneOrderListByOrder(Integer orderSN)
//		Order_listDAO dao = new Order_listDAO();
//		System.out.println(dao.getOneOrderListByOrder(new Integer(16001)));
		
		//getAllOrderList()
		Order_listDAO dao = new Order_listDAO();
		List<Order_listVO> list = dao.getAllOrderList();
		for(Order_listVO orderList : list) {
			System.out.println(orderList);
		}
	}

}
