package com.coupon.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponVO implements Serializable{
	private Integer couponId;
	private String couponInfo;
	private String couponName;
	private Timestamp couponStarting;
	private Timestamp couponEnding;
	private String couponSN;
	private Integer couponDiscount;
	
	public CouponVO() {
		super();
	}
	public CouponVO(Integer couponId, String couponInfo, String couponName, Timestamp couponStarting,
			Timestamp couponEnding, String couponSN, Integer couponDiscount) {
		super();
		this.couponId = couponId;
		this.couponInfo = couponInfo;
		this.couponName = couponName;
		this.couponStarting = couponStarting;
		this.couponEnding = couponEnding;
		this.couponSN = couponSN;
		this.couponDiscount = couponDiscount;
	}
	public CouponVO(String couponInfo, String couponName, Timestamp couponStarting,
			Timestamp couponEnding, String couponSN, Integer couponDiscount) {
		super();
		this.couponInfo = couponInfo;
		this.couponName = couponName;
		this.couponStarting = couponStarting;
		this.couponEnding = couponEnding;
		this.couponSN = couponSN;
		this.couponDiscount = couponDiscount;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public String getCouponInfo() {
		return couponInfo;
	}
	public void setCouponInfo(String couponInfo) {
		this.couponInfo = couponInfo;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Timestamp getCouponStarting() {
		return couponStarting;
	}
	public void setCouponStarting(Timestamp couponStarting) {
		this.couponStarting = couponStarting;
	}
	public Timestamp getCouponEnding() {
		return couponEnding;
	}
	public void setCouponEnding(Timestamp couponEnding) {
		this.couponEnding = couponEnding;
	}
	public String getCouponSN() {
		return couponSN;
	}
	public void setCouponSN(String couponSN) {
		this.couponSN = couponSN;
	}
	public Integer getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(Integer couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	@Override
	public String toString() {
		return "CouponVO" + "\n" + "[couponId=" + couponId + ", couponInfo=" + couponInfo + ", couponName=" + couponName
				+ ", couponStarting=" + couponStarting + ", couponEnding=" + couponEnding + ", couponSN=" + couponSN
				+ ", couponDiscount=" + couponDiscount + "]" + "\n";
	}
	

}
