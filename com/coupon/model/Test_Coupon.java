package com.coupon.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Test_Coupon {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		//addCoupon(CouponVO coupon)
//		System.out.println("Please input couponInfo");
//		String couponInfo = sc.next();
//		System.out.println("Please input couponName");
//		String couponName = sc.next();
//		System.out.println("Please input couponStarting");
//		String couponStarting = sc.next();
//		System.out.println("Please input couponEnding");
//		String couponEnding = sc.next();
//		System.out.println("Please input couponSN");
//		String couponSN = sc.next();
//		System.out.println("Please input couponDiscount");
//		Integer couponDiscount = sc.nextInt();
//		
//		Timestamp startingTimestamp = translatedToTimestamp(couponStarting);
//		Timestamp endingTimestamp = translatedToTimestamp(couponEnding);
//		
//		CouponVO coupon = new CouponVO(couponInfo, couponName, startingTimestamp, endingTimestamp, couponSN, couponDiscount);
//		CouponDAO dao = new CouponDAO();
//		dao.addCoupon(coupon);
//		System.out.println("Successfully");
		
//		//deleteCoupon(Integer couponId)
//		System.out.println("Please input couponId you want to delete");
//		Integer couponId = sc.nextInt();
//		CouponDAO dao = new CouponDAO();
//		dao.deleteCoupon(couponId);
//		System.out.println("Successfully");
		
//		//updateCouponV(CouponVO coupon)
//		System.out.println("Please input couponId you want to update");
//		Integer couponId = sc.nextInt();
//		System.out.println("Please input couponInfo you want to update");
//		String couponInfo = sc.next();
//		System.out.println("Please input couponName you want to update");
//		String couponName = sc.next();
//		System.out.println("Please input couponStarting you want to update");
//		String couponStarting = sc.next();
//		System.out.println("Please input couponEnding you want to update");
//		String couponEnding = sc.next();
//		System.out.println("Please input couponSN you want to update");
//		String couponSN = sc.next();
//		System.out.println("Please input couponDiscount you want to update");
//		Integer couponDiscount = sc.nextInt();
//		
//		Timestamp startingTimestamp = translatedToTimestamp(couponStarting);
//		Timestamp endingTimestamp = translatedToTimestamp(couponEnding);
//		
//		CouponVO coupon = new CouponVO(couponId, couponInfo, couponName, startingTimestamp, endingTimestamp, couponSN, couponDiscount);
//		CouponDAO dao = new CouponDAO();
//		dao.updateCoupon(coupon);
//		System.out.println("Successfully");
		
		//getCoupon(Integer couponId)
//		System.out.println("Please input couponId you want to query");
//		Integer couponId = sc.nextInt();
//		CouponDAO coupon = new CouponDAO();
//		System.out.println(coupon.getCoupon(couponId));
		
		//getCoupon(String couponSN)
//		System.out.println("Please input couponSN you want to query");
//		String couponSN = sc.next();
//		CouponDAO coupon = new CouponDAO();
//		System.out.println(coupon.getCouponBySn(couponSN));
		
		//getAll()
		CouponDAO coupon = new CouponDAO();
		List<CouponVO> list = coupon.getAll();
		for(CouponVO c : list) {
			System.out.println(c);
		}
	
		
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
