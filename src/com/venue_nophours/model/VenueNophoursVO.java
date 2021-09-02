package com.venue_nophours.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class VenueNophoursVO implements Serializable{
	
	private Integer venueNophoursSN;
	private Integer venueSN;
	private Timestamp venueDate;
	private String venueNophours;
	
	public Integer getVenueNophoursSN() {
		return venueNophoursSN;
	}
	public void setVenueNophoursSN(Integer venueNophoursSN) {
		this.venueNophoursSN = venueNophoursSN;
	}
	public Integer getVenueSN() {
		return venueSN;
	}
	public void setVenueSN(Integer venueSN) {
		this.venueSN = venueSN;
	}
	public Timestamp getVenueDate() {
		return venueDate;
	}
	public void setVenueDate(Timestamp venueDate) {
		this.venueDate = venueDate;
	}
	public String getVenueNophours() {
		return venueNophours;
	}
	public void setVenueNophours(String venueNophours) {
		this.venueNophours = venueNophours;
	}
	@Override
	public String toString() {
		return "VenueNophoursVO [venueNophoursSN=" + venueNophoursSN + ", venueSN=" + venueSN + ", venueDate="
				+ venueDate + ", venueNophours=" + venueNophours + "]";
	}
}
