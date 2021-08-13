package com.reply.model;

import java.sql.Timestamp;
import java.util.List;
import oracle.sql.BLOB;

public class REPLYService {

	private REPLYDAO_interface dao;

	public REPLYService() {
		dao = new REPLYJDBCDAO();
	}

	public REPLYVO addReply(Integer articleSN,Integer userId,BLOB replyContent,Integer replyLikes,Timestamp replyDate,Timestamp replyUpDate,Integer replyStatus){

		REPLYVO replyVO = new REPLYVO();

		replyVO.setArticleSN(articleSN);
		replyVO.setUserId(userId);
		replyVO.setReplyContent(replyContent);
		replyVO.setReplyLikes(replyLikes);
		replyVO.setReplyDate(replyDate);
		replyVO.setReplyUpDate(replyUpDate);
		replyVO.setReplyStatus(replyStatus);
		
		dao.insert(replyVO);

		return replyVO;
	}

	public REPLYVO updateReply(Integer replySN,Integer articleSN,Integer userId,BLOB replyContent,Integer replyLikes,Timestamp replyDate,Timestamp replyUpDate,Integer replyStatus){

		REPLYVO replyVO = new REPLYVO();

		replyVO.setReplySN(replySN);
		replyVO.setArticleSN(articleSN);
		replyVO.setUserId(userId);
		replyVO.setReplyContent(replyContent);
		replyVO.setReplyLikes(replyLikes);
		replyVO.setReplyDate(replyDate);
		replyVO.setReplyUpDate(replyUpDate);
		replyVO.setReplyStatus(replyStatus);
		
		dao.update(replyVO);

		return replyVO;
	}

	public void deleteReply(Integer replySN) {
		dao.delete(replySN);
	}

	public REPLYVO getOneReply(Integer replySN) {
		return dao.findByPrimaryKey(replySN);
	}

	public List<REPLYVO> getAll() {
		return dao.getAll();
	}
}
