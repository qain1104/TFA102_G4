package com.rental_list.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class RentalListVO implements Serializable {
	private Integer rentalListSN;
	private Integer venueSN;
	private Integer userId;
	private Integer returnStatus;
	private Timestamp rentalDate;
	private String venueReview;
	private byte[] beforeUse;
	private byte[] afterUse;
	private String rentalTime;
	
	public Integer getRentalListSN() {
		return rentalListSN;
	}
	public void setRentalListSN(Integer rentalListSN) {
		this.rentalListSN = rentalListSN;
	}
	public Integer getVenueSN() {
		return venueSN;
	}
	public void setVenueSN(Integer venueSN) {
		this.venueSN = venueSN;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}
	public Timestamp getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Timestamp rentalDate) {
		this.rentalDate = rentalDate;
	}
	public String getVenueReview() {
		return venueReview;
	}
	public void setVenueReview(String venueReview) {
		this.venueReview = venueReview;
	}
	public byte[] getBeforeUse() {
		return beforeUse;
	}
	public void setBeforeUse(byte[] beforeUse) {
		this.beforeUse = beforeUse;
	}
	public byte[] getAfterUse() {
		return afterUse;
	}
	public void setAfterUse(byte[] afterUse) {
		this.afterUse = afterUse;
	}
	public String getRentalTime() {
		return rentalTime;
	}
	public void setRentalTime(String rentalTime) {
		this.rentalTime = rentalTime;
	}
}
