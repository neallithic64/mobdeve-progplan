package com.mobdeve.s17.lim.matthew.mobdeve_progplan.apimodels;

import com.mobdeve.s17.lim.matthew.mobdeve_progplan.models.ProgChecklist;

import java.util.ArrayList;

public class UpdateJS {
	private String programId;
	private ArrayList<ProgChecklist> checklist;

	public UpdateJS(String programId, ArrayList<ProgChecklist> checklist) {
		this.programId = programId;
		this.checklist = checklist;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public ArrayList<ProgChecklist> getChecklist() {
		return checklist;
	}

	public void setChecklist(ArrayList<ProgChecklist> checklist) {
		this.checklist = checklist;
	}
}
