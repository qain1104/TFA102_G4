package com.article.model;

import java.sql.*;

public class ARTICLEVO implements java.io.Serializable{
	private Integer articleSN; //�峹�s��
	private Integer userId; //�o���
	private Integer articleClass; //�峹����
	private Integer articleType; //�峹����
	private String articleTitle; //�峹���D
	private byte[] articleContent; //�峹���e
	private Integer articlePop; //�峹�H��
	private Integer articleLikes; //���g��
	private Timestamp articleDate; //�W�[���
	private Timestamp articleUpDate; //�s����
	private Integer articleStatus; //�峹���A
	
	
	
	
	@Override
	public String toString() {
		return "ARTICLEVO [articleSN=" + articleSN + ", userId=" + userId + ", articleClass=" + articleClass
				+ ", articleType=" + articleType + ", articleTitle=" + articleTitle + ", articleContent="
				+ articleContent + ", articlePop=" + articlePop + ", articleLikes=" + articleLikes + ", articleDate="
				+ articleDate + ", articleUpDate=" + articleUpDate + ", articleStatus=" + articleStatus + "]";
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

	public Integer getArticleClass() {
		return articleClass;
	}

	public void setArticleClass(Integer articleClass) {
		this.articleClass = articleClass;
	}

	public byte[] getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(byte[] articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getArticleType() {
		return articleType;
	}

	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public Integer getArticlePop() {
		return articlePop;
	}

	public void setArticlePop(Integer articlePop) {
		this.articlePop = articlePop;
	}

	public Integer getArticleLikes() {
		return articleLikes;
	}

	public void setArticleLikes(Integer articleLikes) {
		this.articleLikes = articleLikes;
	}

	public Timestamp getarticleDate() {
		return articleDate;
	}

	public void setArticleDate(Timestamp articleDate) {
		this.articleDate = articleDate;
	}

	public Timestamp getArticleUpDate() {
		return articleUpDate;
	}

	public void setArticleUpDate(Timestamp articleUpDate) {
		this.articleUpDate = articleUpDate;
	}

	public Integer getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(Integer articleStatus) {
		this.articleStatus = articleStatus;
	}
}
