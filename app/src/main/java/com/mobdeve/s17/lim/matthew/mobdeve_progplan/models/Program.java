package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import java.util.ArrayList;
import java.util.Date;

public class Program {
	private	String progName;
	private String location;
	private Date startDate;
	private Date endDate;
	private ArrayList<String> resources;
	private ArrayList<String> outcomes;
	private int checklistDone;

	public Program(String progName, String location, Date startDate, Date endDate) {
		this.progName = progName;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.resources = new ArrayList<>();
		this.outcomes = new ArrayList<>();
		checklistDone = 0;
	}

	public String getProgName() {
		return progName;
	}
	public String getLocation() {
		return location;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public ArrayList<String> getResources() {
		return resources;
	}
	public ArrayList<String> getOutcomes() {
		return outcomes;
	}
	public int getChecklistDone() {
		return checklistDone;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setResources(ArrayList<String> resources) {
		this.resources = resources;
		// TODO: update this
	}
	public void setOutcomes(ArrayList<String> outcomes) {
		this.outcomes = outcomes;
		// TODO: update this
	}
	public void setChecklistDone(int checklistDone) {
		this.checklistDone = checklistDone;
	}
}
