package com.reply.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.alike.model.ARTICLE_LIKEVO;

public class REPLYService {

	private REPLYDAO_interface dao;

	public REPLYService() {
		dao = new REPLYJNDIDAO();
	}

	public REPLYVO addReply(Integer articleSN, Integer userId, byte[] replyContent) {

		REPLYVO replyVO = new REPLYVO();
		java.sql.Timestamp currentTime = new java.sql.Timestamp(new java.util.Date().getTime());
		replyVO.setArticleSN(articleSN);
		replyVO.setUserId(userId);
		replyVO.setReplyContent(replyContent);
		replyVO.setReplyLikes(0);
		replyVO.setReplyDate(currentTime);
		replyVO.setReplyUpDate(currentTime);
		replyVO.setReplyStatus(0);

		dao.insert(replyVO);

		return replyVO;
	}

	public REPLYVO updateReply(Integer replySN, Integer articleSN, Integer userId, byte[] replyContent,
			Integer replyLikes, Timestamp replyDate, Timestamp replyUpDate, Integer replyStatus) {

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

	public List<REPLYVO> getReplybyArticle(Integer articleSN) {
		List<REPLYVO> replylist = dao.getAll().stream().filter(e -> e.getArticleSN().equals(articleSN))
				.collect(Collectors.toList());
		return replylist;
	}

	public REPLYVO editreply(REPLYVO replyVO, byte[] replyContent) {

		replyVO.setReplyContent(replyContent);
		replyVO.setReplyUpDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		dao.update(replyVO);

		return replyVO;
	}

	public REPLYVO updateastatus(REPLYVO replyVO, Integer replyStatus) {

		replyVO.setReplyStatus(replyStatus);
		dao.update(replyVO);

		return replyVO;
	}

	public REPLYVO addlike(REPLYVO replyVO) {

		replyVO.setReplyLikes(replyVO.getReplyLikes() + 1);
		dao.update(replyVO);

		return replyVO;
	}

	public REPLYVO reducelike(REPLYVO replyVO) {

		replyVO.setReplyLikes(replyVO.getReplyLikes() - 1);
		dao.update(replyVO);

		return replyVO;
	}

}
