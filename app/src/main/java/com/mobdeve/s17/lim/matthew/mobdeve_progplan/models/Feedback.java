package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

public class Feedback {
	private String feedbackId;
	private String programId;
	private String comments;

	public Feedback(String feedbackId, String programId, String comments) {
		this.feedbackId = feedbackId;
		this.programId = programId;
		this.comments = comments;
	}

	public String getFeedbackId() { return feedbackId; }
	public String getProgramId() { return programId; }
	public String getComments() { return comments; }
}
