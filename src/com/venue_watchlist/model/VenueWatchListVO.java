package com.venue_watchlist.model;

import java.io.Serializable;

public class VenueWatchListVO implements Serializable{
	
	private Integer venueWatchListSN;
	private Integer venueSN;
	private Integer userId;
	
	public VenueWatchListVO() {
		super();
	}
	
	public VenueWatchListVO(Integer venueSN, Integer userId) {
		super();
		this.venueSN = venueSN;
		this.userId = userId;
	}

	public VenueWatchListVO(Integer venueWatchListSN, Integer venueSN, Integer userId) {
		super();
		this.venueWatchListSN = venueWatchListSN;
		this.venueSN = venueSN;
		this.userId = userId;
	}
	
	
	
	public Integer getVenueWatchListSN() {
		return venueWatchListSN;
	}
	public void setVenueWatchListSN(Integer venueWatchListSN) {
		this.venueWatchListSN = venueWatchListSN;
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
}
