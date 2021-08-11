package com.morder.model;

import java.util.List;

import com.coupon.model.CouponVO;

public interface MorderDAO_interface {
	void addMorder(MorderVO morder);
	void deleteMorder(Integer orderSN);
	void updateMorder(MorderVO morder);
	MorderVO getMorder(Integer orderSN);
	MorderVO getMorderByUser(Integer userId);
	List<MorderVO> getAllMorder();
}
