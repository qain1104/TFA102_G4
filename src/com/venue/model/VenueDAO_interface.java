package com.venue.model;

import java.util.List;

import com.venue.model.*;

public interface VenueDAO_interface {
	 public void insert(VenueVO venueVO);
     public void update(VenueVO venueVO);
     public void delete(Integer venueSN);
     public VenueVO findByPrimaryKey(Integer venueSN);
     public List<VenueVO> getAll();
}
