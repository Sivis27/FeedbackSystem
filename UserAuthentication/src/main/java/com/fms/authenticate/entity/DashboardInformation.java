package com.fms.authenticate.entity;

public class DashboardInformation {

	private String event_id;
	private int lives_impacted;
	private int total_no_of_volunteers;
	private int total_no_of_Participants;

	public DashboardInformation(String event_id, int lives_impacted, int total_no_of_volunteers,
			int total_no_of_Participants) {
		super();
		this.event_id = event_id;
		this.lives_impacted = lives_impacted;
		this.total_no_of_volunteers = total_no_of_volunteers;
		this.total_no_of_Participants = total_no_of_Participants;
	}

	public DashboardInformation() {
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public int getLives_impacted() {
		return lives_impacted;
	}

	public void setLives_impacted(int lives_impacted) {
		this.lives_impacted = lives_impacted;
	}

	public int getTotal_no_of_volunteers() {
		return total_no_of_volunteers;
	}

	public void setTotal_no_of_volunteers(int total_no_of_volunteers) {
		this.total_no_of_volunteers = total_no_of_volunteers;
	}

	public int getTotal_no_of_Participants() {
		return total_no_of_Participants;
	}

	public void setTotal_no_of_Participants(int total_no_of_Participants) {
		this.total_no_of_Participants = total_no_of_Participants;
	}

}
