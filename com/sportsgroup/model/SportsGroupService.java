package com.sportsgroup.model;

import java.sql.Timestamp;
import java.util.List;


public class SportsGroupService {

	private SportsGroupDAO_interface dao;

	public SportsGroupService() {
		dao = new SportsGroupJDBCDAO();
	}

	public SportsGroupVO addSportGroup(Integer userId, String sportsType, String sportsLocation, Timestamp exerciseTime,
			Integer numberUpLimit, Integer numberLowLimit, Timestamp registTime, Timestamp registTimeEnd,
			Timestamp issueDATE, Integer participantNumber, Integer success, String remarks) {

		SportsGroupVO sportsGroupVO = new SportsGroupVO();
		System.out.println("BBB");
		sportsGroupVO.setUserId(userId);
		System.out.println(userId);
		sportsGroupVO.setSportsType(sportsType);
		System.out.println(sportsType);
//		java.sql.Timestamp.valueOf("issueDATE")
		sportsGroupVO.setSportsLocation(sportsLocation);
		System.out.println(sportsLocation);
		sportsGroupVO.setExerciseTime(exerciseTime);
		System.out.println(exerciseTime);
		sportsGroupVO.setNumberUpLimit(numberUpLimit);
		System.out.println(numberUpLimit);
		sportsGroupVO.setNumberLowLimit(numberLowLimit);
		System.out.println(numberLowLimit);
		sportsGroupVO.setRegistTime(registTime);
		System.out.println(registTime);
		sportsGroupVO.setRegistTimeEnd(registTimeEnd);
		System.out.println(registTimeEnd);
		sportsGroupVO.setIssueDATE(issueDATE);
		System.out.println(issueDATE);
		sportsGroupVO.setParticipantNumber(participantNumber);
		System.out.println(participantNumber);
		sportsGroupVO.setSuccess(success);
		System.out.println(success);
		sportsGroupVO.setRemarks(remarks);
		System.out.println(remarks);
		System.out.println(((Object)registTimeEnd).getClass().getSimpleName());
		dao.insert(sportsGroupVO);
		System.out.println("n");
		
		
		
//		sportsGroupVO.setUserId(1006);
//		sportsGroupVO.setSportsType(膞瞴代刚);
////		java.sql.Timestamp.valueOf("issueDATE")
//		sportsGroupVO.setSportsLocation(代刚);
//		sportsGroupVO.setExerciseTime(java.sql.Timestamp.valueOf(2021-01-01 00:00:00));
//		sportsGroupVO.setNumberLowLimit(4);
//		sportsGroupVO.setNumberLowLimit(3);java.sql.Timestamp.valueOf("2021-08-22 09:44:51.484")
//		sportsGroupVO.setRegistTime(java.sql.Timestamp.valueOf("2021-01-01 00:00:00"));
//		sportsGroupVO.setRegistTime(2021-08-20 00:00:00.0);
//		sportsGroupVO.setIssueDATE(java.sql.Timestamp.valueOf("2021-08-22 09:44:51.484"));
//		sportsGroupVO.setParticipantNumber(0);
//		sportsGroupVO.setSuccess(0);
//		sportsGroupVO.setRemarks(代刚代刚);
//		System.out.println(((Object)registTimeEnd).getClass().getSimpleName());
//		dao.insert(sportsGroupVO);
//		System.out.println("n");
		return sportsGroupVO;
	}

	public SportsGroupVO updateSportsGroup(Integer sportsGroupSN, Integer userId, String sportsType,
			String sportsLocation, Timestamp exerciseTime, Integer numberUpLimit, Integer numberLowLimit,
			Timestamp registTime, Timestamp registTimeEnd, Timestamp issueDATE, Integer participantNumber,
			Integer success, String remarks) {

		SportsGroupVO sportsGroupVO = new SportsGroupVO();

		sportsGroupVO.setSportsGroupSN(sportsGroupSN);
		sportsGroupVO.setUserId(userId);
		sportsGroupVO.setSportsType(sportsType);
		sportsGroupVO.setSportsLocation(sportsLocation);
		sportsGroupVO.setExerciseTime(exerciseTime);
		sportsGroupVO.setNumberLowLimit(numberUpLimit);
		sportsGroupVO.setNumberLowLimit(numberLowLimit);
		sportsGroupVO.setRegistTime(registTime);
		sportsGroupVO.setRegistTime(registTimeEnd);
		sportsGroupVO.setIssueDATE(issueDATE);
		sportsGroupVO.setParticipantNumber(participantNumber);
		sportsGroupVO.setSuccess(success);
		sportsGroupVO.setRemarks(remarks);
		dao.update(sportsGroupVO);

		return sportsGroupVO;

	}

	public void deleteSportsGroup(Integer sportsGroupSN) {
		dao.delete(sportsGroupSN);
	}

	public SportsGroupVO getOneSportsGroup(Integer sportsGroupSN) {
		return dao.findByPrimaryKey(sportsGroupSN);
	}

	public List<SportsGroupVO> getAll() {
		return dao.getAll();
	}

}
