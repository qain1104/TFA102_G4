package com.alike.model;

public class ARTICLE_LIKEVO implements java.io.Serializable{
	private Integer articleLikeSN; //�峹���g�s��
	private Integer articleSN; //�峹�s��
	private Integer userId; //�|���s��
	
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
