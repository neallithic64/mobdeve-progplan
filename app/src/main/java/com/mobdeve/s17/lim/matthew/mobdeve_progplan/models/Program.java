package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import java.util.Date;

public class Program {
	private String programId;
	private String userCreated;
	private	String programTitle;
	private Date startDate;
	private Date endDate;
	private String street;
	private String city;
	private int progress;
	private String status;

	public Program(String programId, String userCreated, String programTitle, Date startDate, Date endDate, String street, String city, int progress, String status) {
		this.programId = programId;
		this.userCreated = userCreated;
		this.programTitle = programTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.street = street;
		this.city = city;
		this.progress = progress;
		this.status = status;
	}

	public String getProgramId() { return programId; }
	public String getUserCreated() { return userCreated; }
	public String getProgramTitle() { return programTitle; }
	public Date getStartDate() { return startDate; }
	public Date getEndDate() { return endDate; }
	public String getStreet() { return street; }
	public String getCity() { return city; }
	public int getProgress() { return progress; }
	public String getStatus() { return status; }

	public void setStartDate(Date startDate) { this.startDate = startDate; }
	public void setEndDate(Date endDate) { this.endDate = endDate; }
	public void setCity(String city) { this.city = city; }
	public void setProgress(int progress) { this.progress = progress; }
	public void setStatus(String status) { this.status = status; }
}
