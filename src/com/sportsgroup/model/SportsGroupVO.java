package com.sportsgroup.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SportsGroupVO implements Serializable {
	private Integer sportsGroupSN;  //揪團編號
	private Integer userId; // 發起者
	private String sportsType; //運動類型
	private String sportsLocation; //場地資訊
	private Timestamp exerciseTime; //運動時間
	private Integer numberUpLimit; //人數上限
	private Integer numberLowLimit; //人數下限
	private Timestamp registTime;//報名時間
	private Timestamp registTimeEnd; //報名截止時間
	private Timestamp issueDATE; //發起時間
	private Integer participantNumber; //參加人數
	private Integer success; //是否流團
	private String remarks; //備註
	public Integer getSportsGroupSN() {
		return sportsGroupSN;
	}
	public void setSportsGroupSN(Integer sportsGroupSN) {
		this.sportsGroupSN = sportsGroupSN;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSportsType() {
		return sportsType;
	}
	public void setSportsType(String sportsType) {
		this.sportsType = sportsType;
	}
	public String getSportsLocation() {
		return sportsLocation;
	}
	public void setSportsLocation(String sportsLocation) {
		this.sportsLocation = sportsLocation;
	}
	public Timestamp getExerciseTime() {
		return exerciseTime;
	}
	public void setExerciseTime(Timestamp exerciseTime) {
		this.exerciseTime = exerciseTime;
	}
	public Integer getNumberUpLimit() {
		return numberUpLimit;
	}
	public void setNumberUpLimit(Integer numberUpLimit) {
		this.numberUpLimit = numberUpLimit;
	}
	public Integer getNumberLowLimit() {
		return numberLowLimit;
	}
	public void setNumberLowLimit(Integer numberLowLimit) {
		this.numberLowLimit = numberLowLimit;
	}
	public Timestamp getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Timestamp registTime) {
		this.registTime = registTime;
	}
	public Timestamp getRegistTimeEnd() {
		return registTimeEnd;
	}
	public void setRegistTimeEnd(Timestamp registTimeEnd) {
		this.registTimeEnd = registTimeEnd;
	}
	public Timestamp getIssueDATE() {
		return issueDATE;
	}
	public void setIssueDATE(Timestamp issueDATE) {
		this.issueDATE = issueDATE;
	}
	public Integer getParticipantNumber() {
		return participantNumber;
	}
	public void setParticipantNumber(Integer participantNumber) {
		this.participantNumber = participantNumber;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "SportsGroupVO [sportsGroupSN=" + sportsGroupSN + ", userId=" + userId + ", sportsType=" + sportsType
				+ ", sportsLocation=" + sportsLocation + ", exerciseTime=" + exerciseTime + ", numberUpLimit="
				+ numberUpLimit + ", numberLowLimit=" + numberLowLimit + ", registTime=" + registTime
				+ ", registTimeEnd=" + registTimeEnd + ", issueDATE=" + issueDATE + ", participantNumber="
				+ participantNumber + ", success=" + success + ", remarks=" + remarks + "]";
	}
	
	
	

}
