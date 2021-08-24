package com.participant.model;

import java.io.Serializable;

public class ParticipantVO implements Serializable {
	private Integer  participantID;
	private Integer sportsGroupSN;
	private Integer userId;
	public Integer getParticipantID() {
		return participantID;
	}
	public void setParticipantID(Integer participantID) {
		this.participantID = participantID;
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
		return "ParticipantVO [participantID=" + participantID + ", sportsGroupSN=" + sportsGroupSN + ", userId="
				+ userId + "]";
	}
	
	

}
