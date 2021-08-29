package com.article.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.alike.model.ARTICLE_LIKEVO;

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
	
	public ARTICLEVO updateapop(ARTICLEVO articleVO) {
		
		articleVO.setArticlePop(articleVO.getArticlePop()+1);
		dao.update(articleVO);
		
		return articleVO;
	}
	
	public ARTICLEVO addlike(ARTICLEVO articleVO) {
		
		articleVO.setArticleLikes(articleVO.getArticleLikes()+1);
		dao.update(articleVO);
		
		return articleVO;
	}
	
	public ARTICLEVO reducelike(ARTICLEVO articleVO) {
		
		articleVO.setArticleLikes(articleVO.getArticleLikes()-1);
		dao.update(articleVO);
		
		return articleVO;
	}
	
	public ARTICLEVO updateastatus(ARTICLEVO articleVO,Integer articleStatus) {
		
		articleVO.setArticleStatus(articleStatus);
		dao.update(articleVO);
		
		return articleVO;
	}
	

	public ARTICLEVO editArticle(ARTICLEVO articleVO,Integer articleClass,Integer articleType,String articleTitle,byte[] articleContent)
	{

		articleVO.setArticleClass(articleClass);
		articleVO.setArticleType(articleType);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArticleUpDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		
		dao.update(articleVO);

		return articleVO;
	}
	
	
	public ARTICLEVO updateArticle(Integer articleSN,Integer articleClass,Integer articleType,String articleTitle,byte[] articleContent,Integer articlePop,Integer articleLikes,Timestamp articleUpDate,Integer articleStatus)
	{

		ARTICLEVO articleVO = new ARTICLEVO();

		articleVO.setArticleSN(articleSN);
		articleVO.setArticleClass(articleClass);
		articleVO.setArticleType(articleType);
		articleVO.setArticleTitle(articleTitle);
		articleVO.setArticleContent(articleContent);
		articleVO.setArticlePop(articlePop);
		articleVO.setArticleLikes(articleLikes);
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
	
	public List<ARTICLEVO> getClassArticle(Integer articleClass) {
		List<ARTICLEVO> aclass=dao.getAll().stream()
				.filter(e ->e.getArticleClass().equals(articleClass))
				.collect(Collectors.toList());
		return aclass;
	}
	
	public List<ARTICLEVO> getMyArticle(Integer userId){
		
		List<ARTICLEVO> myarticle=dao.getAll().stream()
				.filter(e ->e.getUserId().equals(userId))
				.collect(Collectors.toList());
		return myarticle;
	}
	
	public List<ARTICLEVO> getSearchArticle(String search){
		
		List<ARTICLEVO> mysearch=dao.getAll().stream()
				.filter(e ->e.getArticleTitle().contains(search))
				.collect(Collectors.toList());
		return mysearch;
	}
	
}
