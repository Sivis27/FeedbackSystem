package com.fms.email.model;

public class AuthRequest {
	public AuthRequest(String emailType, String eventid) {
		super();
		this.emailType = emailType;
		this.eventid = eventid;
	}

	private String emailType;
	private String eventid;

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

}
