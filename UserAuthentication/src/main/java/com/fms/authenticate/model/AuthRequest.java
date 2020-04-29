package com.fms.authenticate.model;

public class AuthRequest {

	private String username;
	private String email;
	private String password;
	private String eventid;

	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthRequest(String username, String email, String password, String eventid) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.eventid = eventid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

}
