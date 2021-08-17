package com.csresponse.model;

import java.io.Serializable;
import java.sql.Date;


public class CsresponseVO implements Serializable{
	private Integer  responseSN;
	private Integer managerId;
	private Integer userId;
	private String csDescription;
	private String csResponse;
	private Date submittedDate;
	private Date closingDate;
	private Integer responseStatus;
	
	
	public Integer getResponseSN() {
		return responseSN;
	}
	public void setResponseSN(Integer responseSN) {
		this.responseSN = responseSN;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCsDescription() {
		return csDescription;
	}
	public void setCsDescription(String csDescription) {
		this.csDescription = csDescription;
	}
	public String getCsResponse() {
		return csResponse;
	}
	public void setCsResponse(String csResponse) {
		this.csResponse = csResponse;
	}
	public Date getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	public Integer getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(Integer responseStatus) {
		this.responseStatus = responseStatus;
	}

}
