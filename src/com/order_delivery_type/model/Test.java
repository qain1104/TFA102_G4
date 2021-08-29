package com.order_delivery_type.model;

import java.util.List;
import java.util.Scanner;

public class Test {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		//addOrderDeliveryType()
//		System.out.println("Please input deliveryType");
//		String type = sc.next();
//		System.out.println("Please input deliveryFee");
//		Integer fee = sc.nextInt();
//		Order_delivery_typeDAO odtDAO = new Order_delivery_typeDAO();
//		odtDAO.addOrderDeliveryType(new Order_delivery_typeVO(type, fee));
//		System.out.println("Successfully");
		
//		//deleteOrderDeliveryType()
//		System.out.println("Please input Id of delivery type you want to delete");
//		Integer typeId = sc.nextInt();
//		Order_delivery_typeDAO odtDAO = new Order_delivery_typeDAO();
//		odtDAO.deleteOrderDeliveryType(typeId);
//		System.out.println("Successfully");
		
//		//updateOrderDeliveryType()
//		System.out.println("Please input Id of delivery type you wnat to update");
//		Integer typeId = sc.nextInt();
//		System.out.println("Please input the type you want to update");
//		String deliveryType = sc.next();
//		System.out.println("Please input the fee you want to update");
//		Integer fee = sc.nextInt();
//		Order_delivery_typeDAO odtDAO = new Order_delivery_typeDAO();
//		odtDAO.updateOrderDeliveryType(new Order_delivery_typeVO(typeId, deliveryType, fee));
//		System.out.println("Successfully");
		
		//getOneType()
//		System.out.println("Please input Id of delivery type you want to query");
//		Integer typeId = sc.nextInt();
//		Order_delivery_typeDAO odtDAO = new Order_delivery_typeDAO();
//		System.out.println(odtDAO.getOneType(typeId));
		
		
//		//getAllType()
		Order_delivery_typeDAO odtDAO = new Order_delivery_typeDAO();
		List<Order_delivery_typeVO> list = odtDAO.getAllType();
		for(Order_delivery_typeVO vo : list) {
			System.out.print(vo);
		}
	}

}
