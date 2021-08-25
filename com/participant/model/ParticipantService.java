package com.participant.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.sportsgroup.model.SportsGroupService;
import com.sportsgroup.model.SportsGroupVO;

public class ParticipantService {
	private ParticipantDAO_interface dao;

	public ParticipantService() {
		dao = new ParticipantJDBCDAO();
	}

	public ParticipantVO addParticipant(Integer sportsGroupSN, Integer userId) {
		ParticipantVO participantVO = new ParticipantVO();
		participantVO.setSportsGroupSN(sportsGroupSN);
		participantVO.setUserId(userId);
		System.out.println("進來了" + participantVO);
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
	
//	public void deleteParticipant1(Integer sportsGroupSN , Integer userId) {
//		List<ParticipantVO> participant = dao.getAll().stream().filter(e -> e.getSportsGroupSN().equals(sportsGroupSN))
//				.collect(Collectors.toList());
//		
//		Integer participantID = participant.stream().filter(e -> e.getUserId().equals(userId));
//		
//	}

	public ParticipantVO getOneParticipant(Integer participantID) {
		return dao.findByPrimaryKey(participantID);
	}

	public List<ParticipantVO> getAll() {
		return dao.getAll();
	}

	public List<ParticipantVO> getParticipant(Integer sportsGroupSN) {
		List<ParticipantVO> participant = dao.getAll().stream().filter(e -> e.getSportsGroupSN().equals(sportsGroupSN))
				.collect(Collectors.toList());
		return participant;
	}

	
	public List<SportsGroupVO> getSportsGroup(Integer userId) {
		List<ParticipantVO> participant = dao.getAll().stream().filter(e -> e.getUserId().equals(userId))
				.collect(Collectors.toList());
		List<SportsGroupVO> aa = new ArrayList();
		SportsGroupService sportsGroupSVC = new SportsGroupService();
		for (ParticipantVO vo : participant) {
			aa.add(sportsGroupSVC.getOneSportsGroup(vo.getSportsGroupSN()));
		}
		return aa;
	}

	public Boolean getaa(Integer userId, Integer sportsGroupSN) {
		List<ParticipantVO> participant = dao.getAll().stream().filter(e -> e.getUserId().equals(userId))
				.collect(Collectors.toList());

		Boolean aa = participant.stream().noneMatch(e -> sportsGroupSN.equals(e.getSportsGroupSN()));

		
		
		
		return aa;
	}
	
	

	public static void main(String[] args) {
		ParticipantService participantSVC = new ParticipantService();

		List<ParticipantVO> list = participantSVC.getParticipant(9001);
		List<SportsGroupVO> list1 = participantSVC.getSportsGroup(1004);
//		System.out.println(list1);
		
		
		System.out.println(participantSVC.getaa(1008,9001));

	}

}
