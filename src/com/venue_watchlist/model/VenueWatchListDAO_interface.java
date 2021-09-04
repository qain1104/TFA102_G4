package com.venue_watchlist.model;

import java.util.*;

public interface VenueWatchListDAO_interface {
	public void insert(VenueWatchListVO venueWatchListVO);
    public void update(VenueWatchListVO venueWatchListVO);
    public void delete(Integer venueWatchListSN);
    public VenueWatchListVO findByPrimaryKey(Integer venueWatchListSN);
    public List<VenueWatchListVO> getAll();
	public List<VenueWatchListVO> getWatchListByUser(Integer userId);
}
