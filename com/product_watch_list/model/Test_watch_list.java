package com.product_watch_list.model;

import java.util.List;
import java.util.Scanner;

public class Test_watch_list {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//addWatchList(Product_Watch_ListVO watchList)
//		System.out.println("Please input productSN you want to add");
//		Integer productSN = sc.nextInt();
//		System.out.println("Please input userId you want to add");
//		Integer userId = sc.nextInt();
//		Product_watch_listDAO dao = new Product_watch_listDAO();
//		dao.addWatchList(new Product_watch_listVO(productSN, userId));
//		System.out.println("Successfully");
		
//		//deleteWatchList(Integer pwlSN)
//		System.out.println("Please input pwlSN you want to delete");
//		Integer pwlSN = sc.nextInt();
//		Product_watch_listDAO dao = new Product_watch_listDAO();
//		dao.deleteWatchList(pwlSN);
//		System.out.println("Successfully");
		
		
//		//updateWatchList(Product_Watch_ListVO watchList)
//		System.out.println("Please input pwlSN you want to update");
//		Integer pwlSN = sc.nextInt();
//		System.out.println("Please input productSN you want to update");
//		Integer productSN = sc.nextInt();
//		System.out.println("Please input userId you want to update");
//		Integer userId = sc.nextInt();
//		Product_watch_listDAO dao = new Product_watch_listDAO();
//		Product_watch_listVO watchList = new Product_watch_listVO(pwlSN, productSN, userId);
//		dao.updateWatchList(watchList);
//		System.out.println("Successfully");
		
		//getWatchList(Integer pwlSN)
//		System.out.println("Please input pwlsn you want to query");
//		Integer pwlSN = sc.nextInt();
//		Product_watch_listDAO dao = new Product_watch_listDAO();
//		System.out.println(dao.getWatchList(pwlSN));
		
		//getWatchListByUser(Integer userId)
//		Product_watch_listDAO dao = new Product_watch_listDAO();
//		System.out.println(dao.getWatchListByUser(new Integer(1003)));
		
		//getAll()
		Product_watch_listDAO dao = new Product_watch_listDAO();
		System.out.println(dao.getWatchListByUser(1002));
//		List<Product_watch_listVO> list = dao.getAll();
//		for(Product_watch_listVO watchList : list) {
//			System.out.println(watchList);
//		}
	}
}
