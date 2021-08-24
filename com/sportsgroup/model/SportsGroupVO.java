package com.sportsgroup.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SportsGroupVO implements Serializable {
	private Integer sportsGroupSN;  //���νs��
	private Integer userId; // �o�_��
	private String sportsType; //�B������
	private String sportsLocation; //���a��T
	private Timestamp exerciseTime; //�B�ʮɶ�
	private Integer numberUpLimit; //�H�ƤW��
	private Integer numberLowLimit; //�H�ƤU��
	private Timestamp registTime;//���W�ɶ�
	private Timestamp registTimeEnd; //���W�I��ɶ�
	private Timestamp issueDATE; //�o�_�ɶ�
	private Integer participantNumber; //�ѥ[�H��
	private Integer success; //�O�_�y��
	private String remarks; //�Ƶ�
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
