package com.morder.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Test_Morder {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//addMorder(MorderVO morder)
		MorderVO morder = new MorderVO();
		morder.setUserId(new Integer(1001));
//		morder.setCouponId(null);
		morder.setPurchaseDate(translatedToTimestamp("20210625-000000"));
		morder.setTotalPrice(new Integer(590));
		morder.setOrderPayment(new Integer(0));
		morder.setOrderCard("0432-0444-0944-5673");
		morder.setOrderCardYear("2028");
		morder.setOrderCardMonth("09");
		morder.setOrderCompleteDate(translatedToTimestamp("20210628-000000"));
		morder.setOrderDeliveyTypeId(new Integer(14001));
		morder.setReceiver("Amy");
		morder.setReceiverPhone("0932443083");
		morder.setReceiverAddress("台北市信義區永吉路150號");
//		morder.setStoreId(null);
//		morder.setStoreName("");
//		morder.setStoreAddress("");
//		morder.setShippingDate(translatedToTimestamp("20210630-000000"));
//		morder.setDeliveryDate(translatedToTimestamp("20210701-120000"));
		MorderDAO dao = new MorderDAO();
		dao.addMorder(morder);
//		System.out.println("Successfully");
		
//		//deleteMorder(Integer orderSN)
//		MorderDAO dao = new MorderDAO();
//		dao.deleteMorder(new Integer(16005));
//		System.out.println("Successfully");
	
		//updateMorder(MorderVO morder)
//		MorderVO morder = new MorderVO();
//		morder.setOrderSN(new Integer(16006));
//		morder.setUserId(new Integer(1001));
//		morder.setCouponId(new Integer(15002));
//		morder.setPurchaseDate(translatedToTimestamp("20210625-000000"));
//		morder.setTotalPrice(new Integer(1990));
//		morder.setOrderPayment(new Integer(0));
//		morder.setOrderCard("");
//		morder.setOrderCardYear("");
//		morder.setOrderCardMonth("");
//		morder.setOrderCompleteDate(translatedToTimestamp("20210628-000000"));
//		morder.setOrderDeliveyTypeId(new Integer(14001));
//		morder.setReceiver("TONY");
//		morder.setReceiverPhone("0932443083");
//		morder.setReceiverAddress("台北市信義區永吉路46號");
//		morder.setStoreId(null);
//		morder.setStoreName("");
//		morder.setStoreAddress("");
//		morder.setShippingDate(translatedToTimestamp("20210630-000000"));
//		morder.setDeliveryDate(translatedToTimestamp("20210701-120000"));
//		MorderDAO dao = new MorderDAO();
//		dao.updateMorder(morder);
//		System.out.println("Successfully");
		
		
		//queryMorder(Integer orderSN)
//		MorderDAO dao = new MorderDAO();
//		System.out.println("Please input orderSN you want to query");
//		Integer orderSN = sc.nextInt();
//		System.out.println(dao.getMorder(orderSN));
		
		//queryMorderByUser(Integer userId)
//		MorderDAO dao = new MorderDAO();
//		System.out.println(dao.getMorderByUser(new Integer(1001)));
		
		//queryAllMorder()
//		MorderDAO dao = new MorderDAO();
//		List<MorderVO> list = dao.getAllMorder();
//		for(MorderVO morder : list) {
//			System.out.println(morder);
//		}
	}
	public static Timestamp translatedToTimestamp(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Date date = null;
		try {
			// get java.util.Date
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// use java.util.Date.getTime to translate to Timestamp
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
}
