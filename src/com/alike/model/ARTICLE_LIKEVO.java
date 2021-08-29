package com.alike.model;

public class ARTICLE_LIKEVO implements java.io.Serializable{
	private Integer articleLikeSN; //文章按讚編號
	private Integer articleSN; //文章編號
	private Integer userId; //會員編號
	
	public Integer getArticleLikeSN() {
		return articleLikeSN;
	}
	public void setArticleLikeSN(Integer articleLikeSN) {
		this.articleLikeSN = articleLikeSN;
	}
	public Integer getArticleSN() {
		return articleSN;
	}
	public void setArticleSN(Integer articleSN) {
		this.articleSN = articleSN;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
