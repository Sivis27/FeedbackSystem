package com.fms.authenticate.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthResponse {

	public String getToken() {
		return token;
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;

}
