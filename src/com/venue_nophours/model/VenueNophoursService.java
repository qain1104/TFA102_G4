package com.venue_nophours.model;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.venue.model.VenueVO;

public class VenueNophoursService {

	private VenueNophoursDAO_interface dao;

	public VenueNophoursService() {
		dao = new VenueNophoursJDBCDAO();
	}

	public VenueNophoursVO addVenueNophours(Integer venueSN, java.sql.Timestamp venueDate, String venueNophours) {

		VenueNophoursVO venueNophoursVO = new VenueNophoursVO();

		venueNophoursVO.setVenueSN(venueSN);
		venueNophoursVO.setVenueDate(venueDate);
		venueNophoursVO.setVenueNophours(venueNophours);
		dao.insert(venueNophoursVO);

		return venueNophoursVO;
	}

	public VenueNophoursVO updateVenueNophours(Integer venueSN, java.sql.Timestamp venueDate, String venueNophours) {

		VenueNophoursVO venueNophoursVO = new VenueNophoursVO();

		venueNophoursVO.setVenueSN(venueSN);
		venueNophoursVO.setVenueDate(venueDate);
		venueNophoursVO.setVenueNophours(venueNophours);
		dao.update(venueNophoursVO);

		return venueNophoursVO;
	}

	public void deleteVenueNophours(Integer venueNophoursSN) {
		dao.delete(venueNophoursSN);
	}

	public VenueNophoursVO getOneVenueNophours(Integer venueNophoursSN) {
		return dao.findByPrimaryKey(venueNophoursSN);
	}

	public List<VenueNophoursVO> getAll() {
		return dao.getAll();
	}
	
	public List<VenueNophoursVO> getMyVenueNop(Integer venueSN) {
		List<VenueNophoursVO> myVenueNop = dao.getAll().stream()
				.filter(e -> e.getVenueSN().equals(venueSN))
				.collect(Collectors.toList());
		return myVenueNop;
	}
	
	public VenueNophoursVO updateTime(VenueNophoursVO venueNophoursVO, String venueNophours) {
		venueNophoursVO.setVenueNophours(venueNophours);
		dao.update(venueNophoursVO);
		return venueNophoursVO;
	}
}
