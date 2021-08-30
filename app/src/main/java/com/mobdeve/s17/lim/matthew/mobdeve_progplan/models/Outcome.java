package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

public class Outcome {
	private String outcomeName;
	private String programId;
	private int expectedVal;
	private int actualVal;

	public Outcome(String outcomeName, String programId, int expectedVal) {
		this.outcomeName = outcomeName;
		this.programId = programId;
		this.expectedVal = expectedVal;
	}

	public String getOutcomeName() { return outcomeName; }
	public String getProgramId() { return programId; }
	public int getExpectedVal() { return expectedVal; }
	public int getActualVal() { return actualVal; }

	public void setActualVal(int actualVal) { this.actualVal = actualVal; }
}
