package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * UnregisteredFeedback
 */
@Table("unregisteredandnotparticipatedfeedback")
public class UnRegisteredAndNotParticipatedFeedback {
	@Id
	private Long id;
	private String event_id;
	private int disclose;
	private int personalcommitment;

	private int officialwork;

	private int notexpected;

	private int aboutevent;

	private int incorrectlyregistered;

	private int feedbacktype;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public int getDisclose() {
		return disclose;
	}

	public void setDisclose(int disclose) {
		this.disclose = disclose;
	}

	public int getPersonalcommitment() {
		return personalcommitment;
	}

	public void setPersonalcommitment(int personalcommitment) {
		this.personalcommitment = personalcommitment;
	}

	public int getOfficialwork() {
		return officialwork;
	}

	public void setOfficialwork(int officialwork) {
		this.officialwork = officialwork;
	}

	public int getNotexpected() {
		return notexpected;
	}

	public void setNotexpected(int notexpected) {
		this.notexpected = notexpected;
	}

	public int getAboutevent() {
		return aboutevent;
	}

	public void setAboutevent(int aboutevent) {
		this.aboutevent = aboutevent;
	}

	public int getIncorrectlyregistered() {
		return incorrectlyregistered;
	}

	public void setIncorrectlyregistered(int incorrectlyregistered) {
		this.incorrectlyregistered = incorrectlyregistered;
	}

	public int getFeedbacktype() {
		return feedbacktype;
	}

	public void setFeedbacktype(int feedbacktype) {
		this.feedbacktype = feedbacktype;
	}

	
}
