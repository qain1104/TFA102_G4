package com.participant.model;

import java.io.Serializable;

public class ParticipantVO implements Serializable {
	private Integer  ParticipantID;
	private Integer sportsGroupSN;
	private Integer userId;
	
	public Integer getParticipantID() {
		return ParticipantID;
	}
	public void setParticipantID(Integer participantID) {
		ParticipantID = participantID;
	}
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
	@Override
	public String toString() {
		return "ParticipantVO [ParticipantID=" + ParticipantID + ", sportsGroupSN=" + sportsGroupSN + ", userId="
				+ userId + "]";
	}
	

}
