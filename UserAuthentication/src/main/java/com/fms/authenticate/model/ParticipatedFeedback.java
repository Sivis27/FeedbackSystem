package com.fms.authenticate.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Participated
 */
@Table("participatedfeedback") 
public class ParticipatedFeedback {
	@Id
	private Long id;
	private String eventId;
	private int verysatisfied;
	private int satisfied;
	private int ok;
	private int dissatisfied;
	private int verydissatisfied;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVerysatisfied() {
		return verysatisfied;
	}

	public void setVerysatisfied(int verysatisfied) {
		this.verysatisfied = verysatisfied;
	}

	public int getSatisfied() {
		return satisfied;
	}

	public void setSatisfied(int satisfied) {
		this.satisfied = satisfied;
	}

	public int getOk() {
		return ok;
	}

	public void setOk(int ok) {
		this.ok = ok;
	}

	public int getDissatisfied() {
		return dissatisfied;
	}

	public void setDissatisfied(int dissatisfied) {
		this.dissatisfied = dissatisfied;
	}

	public int getVerydissatisfied() {
		return verydissatisfied;
	}

	public void setVerydissatisfied(int verydissatisfied) {
		this.verydissatisfied = verydissatisfied;
	}

	@Override
	public String toString() {
		return "ParticipatedFeedback [id=" + id + ", event_id=" + eventId + ", verysatisfied=" + verysatisfied
				+ ", satisfied=" + satisfied + ", ok=" + ok + ", dissatisfied=" + dissatisfied + ", verydissatisfied="
				+ verydissatisfied + "]";
	}


	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
