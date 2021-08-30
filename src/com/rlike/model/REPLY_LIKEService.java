package com.rlike.model;

import java.util.List;
import java.util.stream.Collectors;

public class REPLY_LIKEService {

	private REPLY_LIKEDAO_interface dao;

	public REPLY_LIKEService() {
		dao = new REPLY_LIKEJNDIDAO();
	}

	public REPLY_LIKEVO addRlike(Integer replySN, Integer userId) {

		REPLY_LIKEVO rlikeVO = new REPLY_LIKEVO();

		rlikeVO.setReplySN(replySN);
		rlikeVO.setUserId(userId);

		dao.insert(rlikeVO);

		return rlikeVO;
	}

	public REPLY_LIKEVO updateRlike(Integer rlikeSN, Integer replySN, Integer userId) {

		REPLY_LIKEVO rlikeVO = new REPLY_LIKEVO();

		rlikeVO.setReplyLikeSN(rlikeSN);
		rlikeVO.setReplySN(replySN);
		rlikeVO.setUserId(userId);

		dao.update(rlikeVO);

		return rlikeVO;
	}

	public void deleteRlike(Integer rlikeVO) {
		dao.delete(rlikeVO);
	}

	public REPLY_LIKEVO getOneRlike(Integer rlikeVO) {
		return dao.findByPrimaryKey(rlikeVO);
	}

	public List<REPLY_LIKEVO> getAll() {
		return dao.getAll();
	}
	
	public List<REPLY_LIKEVO> getReplylike(Integer replySN) {
		List<REPLY_LIKEVO> alike=dao.getAll().stream()
				.filter(e ->e.getReplySN().equals(replySN))
				.collect(Collectors.toList());
		return alike;
	}

	public Boolean doesrliked(Integer loguser,Integer replySN) {
		List<REPLY_LIKEVO> rlike=dao.getAll().stream()
				.filter(e ->e.getReplySN().equals(replySN))
				.collect(Collectors.toList());
		
		Boolean doesrlike =rlike.stream().noneMatch(e -> loguser.equals(e.getUserId()));
		return doesrlike;
	}
	
}
