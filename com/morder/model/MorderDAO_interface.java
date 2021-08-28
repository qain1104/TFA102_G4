package com.morder.model;

import java.util.List;

import com.coupon.model.CouponVO;
import com.order_list.model.Order_listVO;

public interface MorderDAO_interface {
	void addMorder(MorderVO morder);
	void deleteMorder(Integer orderSN);
	void updateMorder(MorderVO morder);
	MorderVO getMorder(Integer orderSN);
	List<MorderVO> getMorderByUser(Integer userId);
	List<MorderVO> getAllMorder();
	// �s�W�q��P�ɤ]�s�W�q����ӡA�^�ǭq��s��
	Integer addMorderWithList(MorderVO morder, List<Order_listVO> list);

}
