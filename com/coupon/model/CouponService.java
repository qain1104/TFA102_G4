package com.coupon.model;

import java.sql.Timestamp;
import java.util.List;

public class CouponService {
	private CouponDAO_interface dao;
	
	public CouponService(){
		dao = new CouponDAO();
	}
	
	public CouponVO addCoupon(String couponInfo, String couponName, Timestamp couponStarting, 
			Timestamp couponEnding,String couponSN, Integer couponDiscount) {
		
		CouponVO coupon = new CouponVO();
		coupon.setCouponInfo(couponInfo);
		coupon.setCouponName(couponName);
		coupon.setCouponStarting(couponStarting);
		coupon.setCouponEnding(couponEnding);
		coupon.setCouponSN(couponSN);
		coupon.setCouponDiscount(couponDiscount);
		dao.addCoupon(coupon);
		return coupon;
	}
	
	public CouponVO updateCoupon(Integer couponId, String couponInfo, String couponName, 
			Timestamp couponStarting, Timestamp couponEnding,String couponSN, Integer couponDiscount) {
		
	CouponVO coupon = new CouponVO();
	coupon.setCouponId(couponId);
	coupon.setCouponInfo(couponInfo);
	coupon.setCouponName(couponName);
	coupon.setCouponStarting(couponStarting);
	coupon.setCouponEnding(couponEnding);
	coupon.setCouponSN(couponSN);
	coupon.setCouponDiscount(couponDiscount);
	dao.updateCoupon(coupon);
	return coupon;
	}
	
	public void deleteCoupon(Integer couponId) {
		dao.deleteCoupon(couponId);
	}
	
	public CouponVO getCoupon(Integer couponId) {
		return dao.getCoupon(couponId);
	}
	
	public List<CouponVO> getAll() {
		return dao.getAll();
	}
	
	public CouponVO getCouponBySn(String couponSN) {
		return dao.getCouponBySn(couponSN);
	}
}
