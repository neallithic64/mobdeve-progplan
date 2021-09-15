package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

public class Resource {
	private String resourceName;
	private String programId;
	private int expectedAmt;
	private int actualAmt;

	public Resource(String resourceName, String programId, int expectedAmt) {
		this.resourceName = resourceName;
		this.programId = programId;
		this.expectedAmt = expectedAmt;
	}

	public Resource(String resourceName, int expectedAmt) {
		this.programId = "";
		this.resourceName = resourceName;
		this.expectedAmt = expectedAmt;
	}

	public String getResourceName() { return resourceName; }
	public String getProgramId() { return programId; }
	public int getExpectedAmt() { return expectedAmt; }
	public int getActualAmt() { return actualAmt; }

	public void setActualAmt(int actualAmt) { this.actualAmt = actualAmt; }
}
