package com.rlike.model;

public class REPLY_LIKEVO implements java.io.Serializable{
	private Integer replyLikeSN; //回覆按讚編號
	private Integer replySN; //文章回覆編號
	private Integer userId; //會員編號
	
	public Integer getReplyLikeSN() {
		return replyLikeSN;
	}
	public void setReplyLikeSN(Integer replyLikeSN) {
		this.replyLikeSN = replyLikeSN;
	}
	public Integer getReplySN() {
		return replySN;
	}
	public void setReplySN(Integer replySN) {
		this.replySN = replySN;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
