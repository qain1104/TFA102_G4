package com.report.model;

import java.sql.Timestamp;

public class REPORTVO implements java.io.Serializable{
	private Integer reportSN; //檢舉編號
	private Integer articleSN; //文章編號
	private Integer replySN; //文章回覆編號
	private Integer userId; //檢舉人
	private Integer reportClass; //檢舉分類
	private String reportContent; //檢舉內容
	private Timestamp reportDate; //檢舉時間
	private Integer reportStatus; //檢舉狀態
	private Integer managerId;  //後臺管理者
	private Timestamp reportAuditDate; //檢舉審核時間
	
	@Override
	public String toString() {
		return "REPORTVO [reportSN=" + reportSN + ", articleSN=" + articleSN + ", replySN=" + replySN + ", userId="
				+ userId + ", reportClass=" + reportClass + ", reportContent=" + reportContent + ", reportDate="
				+ reportDate + ", reportStatus=" + reportStatus + ", managerId=" + managerId + ", reportAuditDate="
				+ reportAuditDate + "]";
	}
	
	public Integer getReportSN() {
		return reportSN;
	}
	public void setReportSN(Integer reportSN) {
		this.reportSN = reportSN;
	}
	public Integer getArticleSN() {
		return articleSN;
	}
	public void setArticleSN(Integer articleSN) {
		this.articleSN = articleSN;
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
	public Integer getReportClass() {
		return reportClass;
	}
	public void setReportClass(Integer reportClass) {
		this.reportClass = reportClass;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public Timestamp getReportDate() {
		return reportDate;
	}
	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Timestamp getReportAuditDate() {
		return reportAuditDate;
	}
	public void setReportAuditDate(Timestamp reportAuditDate) {
		this.reportAuditDate = reportAuditDate;
	}
}
