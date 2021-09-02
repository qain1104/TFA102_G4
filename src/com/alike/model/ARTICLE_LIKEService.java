package com.alike.model;

import java.util.List;
import java.util.stream.Collectors;

public class ARTICLE_LIKEService {

	private ARTICLE_LIKEDAO_interface dao;

	public ARTICLE_LIKEService() {
		dao = new ARTICLE_LIKEJNDIDAO();
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

	public void deleteAlike(Integer alikeSN) {
		dao.delete(alikeSN);
	}

	public ARTICLE_LIKEVO getOneAlike(Integer alikeVO) {
		return dao.findByPrimaryKey(alikeVO);
	}

	public List<ARTICLE_LIKEVO> getAll() {
		return dao.getAll();
	}
	
	public List<ARTICLE_LIKEVO> getArticlelike(Integer articleSN) {
		List<ARTICLE_LIKEVO> alike=dao.getAll().stream()
				.filter(e ->e.getArticleSN().equals(articleSN))
				.collect(Collectors.toList());
		return alike;
	}
	
	public Boolean doesaliked(Integer loguser,Integer articleSN) {
		List<ARTICLE_LIKEVO> alike=dao.getAll().stream()
				.filter(e ->e.getArticleSN().equals(articleSN))
				.collect(Collectors.toList());
		
		Boolean doesalike =alike.stream().noneMatch(e -> loguser.equals(e.getUserId()));
		return doesalike;
	}
	
}
