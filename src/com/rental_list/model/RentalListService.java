package com.rental_list.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.batch.Main;

public class RentalListService {
	private RentalListDAO_interface dao;

	public RentalListService() {
		dao = new RentalListJNDIDAO();
	}

	public RentalListVO addRentalList(Integer venueSN, Integer userId, Integer returnStatus,
			java.sql.Timestamp rentalDate, String venueReview, byte[] beforeUse, byte[] afterUse, String rentalTime) {

		RentalListVO rentalListVO = new RentalListVO();
		rentalListVO.setVenueSN(venueSN);
		rentalListVO.setUserId(userId);
		rentalListVO.setReturnStatus(returnStatus);
		rentalListVO.setRentalDate(rentalDate);
		rentalListVO.setVenueReview(venueReview);
		rentalListVO.setBeforeUse(beforeUse);
		rentalListVO.setAfterUse(afterUse);
		rentalListVO.setRentalTime(rentalTime);
		dao.insert(rentalListVO);
		return rentalListVO;
	}
// public static void main(String[] args) {
//	 RentalListService rsvc=new RentalListService();
//	 Integer venueSN=19001;
//	 Integer userId=1001;
//	 Integer returnStatus=1;
//	 Timestamp rentalDate=(java.sql.Timestamp.valueOf("2021-10-03 00:00:00"));
//	 String venueReview="¿Ùπ“∞Æ≤b";
//	 byte[] beforeUse=null;
//	 byte[] afterUse=null;
//	 String rentalTime="100";
//	 rsvc.addRentalList(venueSN, userId, returnStatus, rentalDate, venueReview, beforeUse, afterUse, rentalTime);
//	 
//}

	public RentalListVO updateRentalList(Integer rentalListSN, Integer venueSN, Integer userId, Integer returnStatus,
			java.sql.Timestamp rentalDate, String venueReview, byte[] beforeUse, byte[] afterUse, String rentalTime) {

		RentalListVO rentalListVO = new RentalListVO();

		rentalListVO.setRentalListSN(rentalListSN);
		rentalListVO.setVenueSN(venueSN);
		rentalListVO.setUserId(userId);
		rentalListVO.setReturnStatus(returnStatus);
		rentalListVO.setRentalDate(rentalDate);
		rentalListVO.setVenueReview(venueReview);
		rentalListVO.setBeforeUse(beforeUse);
		rentalListVO.setAfterUse(afterUse);
		rentalListVO.setRentalTime(rentalTime);
		dao.update(rentalListVO);

		return rentalListVO;
	}

	public void deleteRentalList(Integer rentalListSN) {
		dao.delete(rentalListSN);
	}

	public RentalListVO getOneRentalList(Integer rentalListSN) {
		return dao.findByPrimaryKey(rentalListSN);
	}

	public List<RentalListVO> getAll() {
		return dao.getAll();
	}

	public RentalListVO updateBeforeUse(RentalListVO rentalListVO, byte[] beforeUse) {
//		RentalListVO rentalListVO = dao.findByPrimaryKey(rentalListSN);
		rentalListVO.setBeforeUse(beforeUse);
		dao.update(rentalListVO);
		return rentalListVO;
	}
	
	public RentalListVO updateAfterUse(RentalListVO rentalListVO, byte[] AfterUse) {
//		RentalListVO rentalListVO = dao.findByPrimaryKey(rentalListSN);
		rentalListVO.setBeforeUse(AfterUse);
		dao.update(rentalListVO);
		return rentalListVO;
	}
}