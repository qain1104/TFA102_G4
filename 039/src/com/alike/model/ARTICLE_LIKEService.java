package com.alike.model;

import java.sql.Timestamp;
import java.util.List;
import oracle.sql.BLOB;

public class ARTICLE_LIKEService {

	private ARTICLE_LIKEDAO_interface dao;

	public ARTICLE_LIKEService() {
		dao = new ARTICLE_LIKEJDBCDAO();
	}

	public ARTICLE_LIKEVO addAlike(Integer articleSN,Integer userId)
		{

		ARTICLE_LIKEVO alikeVO = new ARTICLE_LIKEVO();

		alikeVO.setArticleSN(articleSN);
		alikeVO.setUserId(userId);
		
		
		dao.insert(alikeVO);

		return alikeVO;
	}

	public ARTICLE_LIKEVO updateAlike(Integer alikeSN,Integer articleSN,Integer userId)
	{

		ARTICLE_LIKEVO alikeVO = new ARTICLE_LIKEVO();
		
		alikeVO.setArticleLikeSN(alikeSN);
		alikeVO.setArticleSN(articleSN);
		alikeVO.setUserId(userId);
		
		dao.update(alikeVO);

		return alikeVO;
	}

	public void deleteAlike(Integer alikeVO) {
		dao.delete(alikeVO);
	}

	public ARTICLE_LIKEVO getOneAlike(Integer alikeVO) {
		return dao.findByPrimaryKey(alikeVO);
	}

	public List<ARTICLE_LIKEVO> getAll() {
		return dao.getAll();
	}
}
