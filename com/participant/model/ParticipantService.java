package com.participant.model;

import java.sql.Timestamp;
import java.util.List;

public class ParticipantService {
	private ParticipantDAO_interface dao;

	public ParticipantService() {
		dao = new ParticipantJDBCDAO();
	}

	public ParticipantVO addParticipant(Integer sportsGroupSN, Integer userId) {
		ParticipantVO participantVO = new ParticipantVO();

		participantVO.setSportsGroupSN(sportsGroupSN);
		participantVO.setUserId(userId);

		return participantVO;
	}

	public ParticipantVO updateParticipant(Integer participantID, Integer sportsGroupSN, Integer userId) {
		ParticipantVO participantVO = new ParticipantVO();

		participantVO.setParticipantID(participantID);
		participantVO.setSportsGroupSN(sportsGroupSN);
		participantVO.setUserId(userId);

		return participantVO;
	}

	public void deleteParticipant(Integer participantID) {
		dao.delete(participantID);
	}

	public ParticipantVO getOneParticipant(Integer participantID) {
		return dao.findByPrimaryKey(participantID);
	}

	public List<ParticipantVO> getAll() {
		return dao.getAll();
	}

}
