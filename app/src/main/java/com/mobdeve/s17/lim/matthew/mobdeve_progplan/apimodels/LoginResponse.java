package com.mobdeve.s17.lim.matthew.mobdeve_progplan.apimodels;

public class LoginResponse {
	private String email;
	private String username;
	private String city;
	private int userType;

	public LoginResponse(String email, String username, String city, int userType) {
		this.email = email;
		this.username = username;
		this.city = city;
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getCity() {
		return city;
	}

	public int getUserType() {
		return userType;
	}
}
