package com.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	void addCoupon(CouponVO coupon);
	void deleteCoupon(Integer couponId);
	void updateCoupon(CouponVO coupon);
	CouponVO getCoupon(Integer couponId);
	CouponVO getCouponBySn(String couponSN);
	List<CouponVO> getAll();

}
