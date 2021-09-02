package com.venue.model;

import java.util.List;
import java.util.stream.Collectors;

import oracle.net.aso.e;

public class VenueService {

	private VenueDAO_interface dao;

	public VenueService() {
		dao = new VenueJDBCDAO();
	}

	public VenueVO addVenue(Integer corpUserId, Integer venueStatus, String venueName, Integer venueClass,
			String venueInfo, Integer venuePrice, String venueAddress, byte[] venuePic, Integer venueAccommodate,
			String venuePhone) {

		VenueVO venueVO = new VenueVO();

		venueVO.setCorpUserId(corpUserId);
		venueVO.setVenueStatus(venueStatus);
		venueVO.setVenueName(venueName);
		venueVO.setVenueClass(venueClass);
		venueVO.setVenueInfo(venueInfo);
		venueVO.setVenuePrice(venuePrice);
		venueVO.setVenueAddress(venueAddress);
		venueVO.setVenuePic(venuePic);
		venueVO.setVenueAccommodate(venueAccommodate);
		venueVO.setVenuePhone(venuePhone);
		dao.insert(venueVO);

		return venueVO;
	}

	public VenueVO updateVenue(Integer venueSN, Integer corpUserId, Integer venueStatus, String venueName,
			Integer venueClass, String venueInfo, Integer venuePrice, String venueAddress, byte[] venuePic,
			Integer venueAccommodate, String venuePhone) {

		VenueVO venueVO = new VenueVO();
		venueVO.setVenueSN(venueSN);
		venueVO.setCorpUserId(corpUserId);
		venueVO.setVenueStatus(venueStatus);
		venueVO.setVenueName(venueName);
		venueVO.setVenueClass(venueClass);
		venueVO.setVenueInfo(venueInfo);
		venueVO.setVenuePrice(venuePrice);
		venueVO.setVenueAddress(venueAddress);
		venueVO.setVenuePic(venuePic);
		venueVO.setVenueAccommodate(venueAccommodate);
		venueVO.setVenuePhone(venuePhone);
		dao.update(venueVO);

		return venueVO;
	}

	public void deleteVenue(Integer venueSN) {
		dao.delete(venueSN);
	}

	public VenueVO getOneVenue(Integer venueSN) {
		return dao.findByPrimaryKey(venueSN);
	}

	public List<VenueVO> getAll() {
		return dao.getAll();
	}

	public List<VenueVO> getMyVenue(Integer corpUserId) {
		List<VenueVO> myVenue = dao.getAll().stream()
				.filter(e -> e.getCorpUserId().equals(corpUserId))
				.collect(Collectors.toList());
		return myVenue;
	}
	
	//利用狀態搜尋venue列表
    public List<VenueVO> getVenueStatus(Integer venueStatus) {
         List<VenueVO> list=dao.getAll().stream()
                 .filter(e ->e.getVenueStatus().equals(venueStatus))
                 .collect(Collectors.toList());
         return list;
    }
    
    //只改動venue狀態
    public VenueVO updateVenueStatus(VenueVO venueVO,Integer venueStatus) {

        venueVO.setVenueStatus(venueStatus);
        dao.update(venueVO);

        return venueVO;
    }
    
    public VenueVO updateVenuePic(VenueVO venueVO,byte[] venuePic) {
    	
    	venueVO.setVenuePic(venuePic);
    	dao.update(venueVO);
    	
    	return venueVO;
    }
}
