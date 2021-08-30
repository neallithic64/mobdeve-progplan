package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

public class User {
	private String email;
	private String username;
	private String password;
	private String city;

	public User(String email, String username, String password, String city) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.city = city;
	}

	public String getEmail() { return email; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getCity() { return city; }
}
