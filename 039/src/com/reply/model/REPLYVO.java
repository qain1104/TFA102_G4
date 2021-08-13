package com.reply.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class REPLYVO implements java.io.Serializable{
	private Integer replySN; //文章回覆編號
	private Integer articleSN; //文章編號
	private Integer userId; //會員編號,
	private Blob replyContent; //文章內容
	private Integer replyLikes; //按讚數
	private Timestamp replyDate; //上架日期
	private Timestamp replyUpDate; //編輯日期
	private Integer replyStatus; //文章狀態
	
	
	@Override
	public String toString() {
		return "REPLYVO [replySN=" + replySN + ", articleSN=" + articleSN + ", userId=" + userId + ", replyContent="
				+ replyContent + ", replyLikes=" + replyLikes + ", replyDate=" + replyDate + ", replyUpDate="
				+ replyUpDate + ", replyStatus=" + replyStatus + "]";
	}
	
	public Integer getReplySN() {
		return replySN;
	}
	public void setReplySN(Integer replySN) {
		this.replySN = replySN;
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
	public Blob getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(Blob replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getReplyLikes() {
		return replyLikes;
	}
	public void setReplyLikes(Integer replyLikes) {
		this.replyLikes = replyLikes;
	}
	public Timestamp getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public Timestamp getReplyUpDate() {
		return replyUpDate;
	}
	public void setReplyUpDate(Timestamp replyUpDate) {
		this.replyUpDate = replyUpDate;
	}
	public Integer getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(Integer replyStatus) {
		this.replyStatus = replyStatus;
	}
}
