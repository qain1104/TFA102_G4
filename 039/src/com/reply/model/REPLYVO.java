package com.reply.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class REPLYVO implements java.io.Serializable{
	private Integer replySN; //�峹�^�нs��
	private Integer articleSN; //�峹�s��
	private Integer userId; //�|���s��,
	private Blob replyContent; //�峹���e
	private Integer replyLikes; //���g��
	private Timestamp replyDate; //�W�[���
	private Timestamp replyUpDate; //�s����
	private Integer replyStatus; //�峹���A
	
	
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
