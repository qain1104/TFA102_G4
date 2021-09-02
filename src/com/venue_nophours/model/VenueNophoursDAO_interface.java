package com.venue_nophours.model;

import java.util.List;

import com.venue.model.VenueVO;

public interface VenueNophoursDAO_interface {
	public void insert(VenueNophoursVO venueNophoursVO);
    public void update(VenueNophoursVO venueNophoursVO);
    public void delete(Integer venueNophoursSN);
    public VenueNophoursVO findByPrimaryKey(Integer venueNophoursSN);
    public List<VenueNophoursVO> getAll();
}
