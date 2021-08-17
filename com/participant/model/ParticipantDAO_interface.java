package com.participant.model;

import java.util.List;

public interface ParticipantDAO_interface {
	public void insert(ParticipantVO participantVO);
    public void update(ParticipantVO participantVO);
    public void delete(Integer ParticipantID);
    public ParticipantVO findByPrimaryKey(Integer ParticipantID);
    public List<ParticipantVO> getAll();
}
