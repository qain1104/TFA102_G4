package com.participant.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.batch.Main;


public class ParticipantService {
	private ParticipantDAO_interface dao;

	public ParticipantService() {
		dao = new ParticipantJDBCDAO();
	}

	public ParticipantVO addParticipant(Integer sportsGroupSN, Integer userId) {
		ParticipantVO participantVO = new ParticipantVO();
		participantVO.setSportsGroupSN(sportsGroupSN);
		participantVO.setUserId(userId);
		System.out.println("進來了"+participantVO);
		dao.insert(participantVO);
		System.out.println("進來了");
		return participantVO;
	}

	public ParticipantVO updateParticipant(Integer participantID, Integer sportsGroupSN, Integer userId) {
		ParticipantVO participantVO = new ParticipantVO();

		participantVO.setParticipantID(participantID);
		participantVO.setSportsGroupSN(sportsGroupSN);
		participantVO.setUserId(userId);
		dao.update(participantVO);
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
	
	
	
	
	
	public List<ParticipantVO> getParticipant(Integer sportsGroupSN) {
		List<ParticipantVO> participant=dao.getAll().stream()
				.filter(e ->e.getSportsGroupSN().equals(sportsGroupSN))
				.collect(Collectors.toList());
		return participant;
	}
	
	
	public static void main(String[] args) {
		ParticipantService participantSVC = new ParticipantService();
//		participantSVC.getParticipant(9001);
		
//		System.out.println(participantSVC.getParticipant(9001).size());
//		System.out.println(participantSVC.getParticipant(9001));
		
//       System.out.println(participantSVC.getParticipant(9001));
		
		List<ParticipantVO> list = participantSVC.getParticipant(9001);
		
		for (ParticipantVO vo : list) {	
			System.out.println(vo.getSportsGroupSN()+"-"+vo.getUserId() );
		}
	}
		
	
	

}
