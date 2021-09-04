package com.venue_watchlist.model;

import java.util.List;

public class VenueWatchListService {

	private VenueWatchListDAO_interface dao;

	public VenueWatchListService() {
		dao = new VenueWatchListJNDIDAO();
	}

	public VenueWatchListVO addVenueWatchList(Integer venueSN, Integer userId) {

		VenueWatchListVO venueWatchListVO = new VenueWatchListVO();

		venueWatchListVO.setVenueSN(venueSN);
		venueWatchListVO.setUserId(userId);
		dao.insert(venueWatchListVO);

		return venueWatchListVO;
	}

	public VenueWatchListVO updateVenueWatchList(Integer venueWatchListSN, Integer venueSN, Integer userId) {

		VenueWatchListVO venueWatchListVO = new VenueWatchListVO();

		venueWatchListVO.setVenueWatchListSN(venueWatchListSN);
		venueWatchListVO.setVenueSN(venueSN);
		venueWatchListVO.setUserId(userId);
		dao.update(venueWatchListVO);

		return venueWatchListVO;
	}

	public void deleteVenueWatchList(Integer venueWatchListSN) {
		dao.delete(venueWatchListSN);
	}

	public VenueWatchListVO getOneVenueWatchList(Integer venueWatchListSN) {
		return dao.findByPrimaryKey(venueWatchListSN);
	}

	public List<VenueWatchListVO> getWatchListByUser(Integer userId) {
		
		return dao.getWatchListByUser(userId);
	}
	
	public List<VenueWatchListVO> getAll() {
		return dao.getAll();
	}
}