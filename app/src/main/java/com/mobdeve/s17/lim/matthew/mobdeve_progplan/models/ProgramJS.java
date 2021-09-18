package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import java.util.ArrayList;

/* This is a class that's used to model a complate JOIN-ed program object. The
	purpose of this class is NOT as a storage in the database, but rather used
	to standardise the requests to and responses from the server. Thus, the
	server response also needs to be formatted in an identical manner. I also
	believe there won't be any setters here -- only getters.
*/

public class ProgramJS {
	private Program program;
	private ArrayList<Outcome> outcomes;
	private ArrayList<Resource> resources;
	private ArrayList<ProgChecklist> checklistItems;
	private Feedback feedback;

	public ProgramJS(Program program, ArrayList<Outcome> outcomes, ArrayList<Resource> resources) {
		this.program = program;
		this.outcomes = outcomes;
		this.resources = resources;
		this.checklistItems = new ArrayList<>();
	}

	public ProgramJS(Program program, ArrayList<Outcome> outcomes, ArrayList<Resource> resources, ArrayList<ProgChecklist> checklistItems) {
		this.program = program;
		this.outcomes = outcomes;
		this.resources = resources;
		this.checklistItems = checklistItems;
	}

	public ProgramJS(Program program, ArrayList<Outcome> outcomes, ArrayList<Resource> resources, ArrayList<ProgChecklist> checklistItems, Feedback feedback) {
		this.program = program;
		this.outcomes = outcomes;
		this.resources = resources;
		this.checklistItems = checklistItems;
		this.feedback = feedback;
	}

	public Feedback getFeedback() { return feedback; }
	public Program getProgram() { return program; }
	public ArrayList<Outcome> getOutcomes() { return outcomes; }
	public ArrayList<Resource> getResources() { return resources; }
	public ArrayList<ProgChecklist> getChecklist() { return checklistItems; }
}
