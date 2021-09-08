package com.venue.model;

import java.io.Serializable;
import java.util.Arrays;

public class VenueVO implements Serializable {
	
	private Integer venueSN;
	private Integer corpUserId;
	private Integer venueStatus;
	private String venueName;
	private Integer venueClass;
	private String venueInfo;
	private Integer venuePrice;
	private String venueAddress;
	private byte[] venuePic;
	private Integer venueAccommodate;
	private String venuePhone;

	public Integer getVenueSN() {
		return venueSN;
	}

	public void setVenueSN(Integer venueSN) {
		this.venueSN = venueSN;
	}

	public Integer getCorpUserId() {
		return corpUserId;
	}

	public void setCorpUserId(Integer corpUserId) {
		this.corpUserId = corpUserId;
	}

	public Integer getVenueStatus() {
		return venueStatus;
	}

	public void setVenueStatus(Integer venueStatus) {
		this.venueStatus = venueStatus;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public Integer getVenueClass() {
		return venueClass;
	}

	public void setVenueClass(Integer venueClass) {
		this.venueClass = venueClass;
	}

	public String getVenueInfo() {
		return venueInfo;
	}

	public void setVenueInfo(String venueInfo) {
		this.venueInfo = venueInfo;
	}

	public Integer getVenuePrice() {
		return venuePrice;
	}

	public void setVenuePrice(Integer venuePrice) {
		this.venuePrice = venuePrice;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public byte[] getVenuePic() {
		return venuePic;
	}

	public void setVenuePic(byte[] venuePic) {
		this.venuePic = venuePic;
	}

	public Integer getVenueAccommodate() {
		return venueAccommodate;
	}

	public void setVenueAccommodate(Integer venueAccommodate) {
		this.venueAccommodate = venueAccommodate;
	}

	public String getVenuePhone() {
		return venuePhone;
	}

	public void setVenuePhone(String venuePhone) {
		this.venuePhone = venuePhone;
	}

	@Override
	public String toString() {
		return "VenueVO [venueSN=" + venueSN + ", corpUserId=" + corpUserId + ", venueStatus=" + venueStatus
				+ ", venueName=" + venueName + ", venueClass=" + venueClass + ", venueInfo=" + venueInfo
				+ ", venuePrice=" + venuePrice + ", venueAddress=" + venueAddress + ", venuePic="
				+ Arrays.toString(venuePic) + ", venueAccommodate=" + venueAccommodate + ", venuePhone=" + venuePhone
				+ "]";
	}
}
