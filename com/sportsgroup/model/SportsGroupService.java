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
		dao.insert(sportsGroupVO);
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

	public void deleteEmp(Integer sportsGroupSN) {
		dao.delete(sportsGroupSN);
	}

	public SportsGroupVO getOneSportsGroup(Integer sportsGroupSN) {
		return dao.findByPrimaryKey(sportsGroupSN);
	}

	public List<SportsGroupVO> getAll() {
		return dao.getAll();
	}

}
