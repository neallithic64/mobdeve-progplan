package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

public class Admin {
	private String email;
	private String username;
	private String password;

	public Admin(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() { return email; }
	public String getUsername() { return username; }
}
