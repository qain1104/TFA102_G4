package com.rlike.model;

import java.util.List;

public class REPLY_LIKEService {

	private REPLY_LIKEDAO_interface dao;

	public REPLY_LIKEService() {
		dao = new REPLY_LIKEJDBCDAO();
	}

	public REPLY_LIKEVO addRlike(Integer replySN,Integer userId)
		{

		REPLY_LIKEVO rlikeVO = new REPLY_LIKEVO();

		rlikeVO.setReplySN(replySN);
		rlikeVO.setUserId(userId);
		
		
		dao.insert(rlikeVO);

		return rlikeVO;
	}

	public REPLY_LIKEVO updateRlike(Integer rlikeSN,Integer replySN,Integer userId)
	{

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
}
