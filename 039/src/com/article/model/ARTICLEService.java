package com.article.model;

import java.sql.Timestamp;
import java.util.List;
import oracle.sql.BLOB;

public class ARTICLEService {

	private ARTICLEDAO_interface dao;

	public ARTICLEService() {
		dao = new ARTICLEJDBCDAO();
	}

	public ARTICLEVO addArticle(Integer userId,Integer articleClass,Integer articleType,String articleTitle,byte[] articleContent,Integer articlePop,Integer articleLikes,Timestamp articleDate,Timestamp articleUpDate,Integer articleStatus)
		{

		ARTICLEVO articleVO = new ARTICLEVO();

		articleVO.setUserId(userId);
		articleVO.setArticleClass(articleClass);
		articleVO.setArticleType(articleType);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArticlePop(articlePop);
		articleVO.setArticleLikes(articleLikes);
		articleVO.setArticleDate(articleDate);
		articleVO.setArticleUpDate(articleUpDate);
		articleVO.setArticleStatus(articleStatus);
		
		dao.insert(articleVO);

		return articleVO;
	}

	public ARTICLEVO updateArticle(Integer articleSN,Integer userId,Integer articleClass,Integer articleType,String articleTitle,byte[] articleContent,Integer articlePop,Integer articleLikes,Timestamp articleDate,Timestamp articleUpDate,Integer articleStatus)
	{

		ARTICLEVO articleVO = new ARTICLEVO();

		articleVO.setArticleSN(articleSN);
		articleVO.setUserId(userId);
		articleVO.setArticleClass(articleClass);
		articleVO.setArticleType(articleType);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArticlePop(articlePop);
		articleVO.setArticleLikes(articleLikes);
		articleVO.setArticleDate(articleDate);
		articleVO.setArticleUpDate(articleUpDate);
		articleVO.setArticleStatus(articleStatus);
		
		dao.update(articleVO);

		return articleVO;
	}

	public void deleteArticle(Integer articleSN) {
		dao.delete(articleSN);
	}

	public ARTICLEVO getOneArticle(Integer articleSN) {
		return dao.findByPrimaryKey(articleSN);
	}

	public List<ARTICLEVO> getAll() {
		return dao.getAll();
	}
	
	public List<ARTICLEVO> getClassArticle(Integer articleSN) {
		return dao.findByClassKey(articleSN);
	}
}
