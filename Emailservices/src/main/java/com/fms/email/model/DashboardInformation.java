package com.fms.email.model;

public class DashboardInformation {

	private String eventId;
	private int livesImpacted;
	private int totalNoOfVolunteers;
	private int noOfParticipants;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public int getLivesImpacted() {
		return livesImpacted;
	}
	public void setLivesImpacted(int livesImpacted) {
		this.livesImpacted = livesImpacted;
	}
	public int getTotalNoOfVolunteers() {
		return totalNoOfVolunteers;
	}
	public void setTotalNoOfVolunteers(int totalNoOfVolunteers) {
		this.totalNoOfVolunteers = totalNoOfVolunteers;
	}
	public int getNoOfParticipants() {
		return noOfParticipants;
	}
	public void setNoOfParticipants(int noOfParticipants) {
		this.noOfParticipants = noOfParticipants;
	}


}
