package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

public class ProgChecklist {
	private String progItem;
	private String programId;
	private boolean checked;

	public ProgChecklist(String progItem, String programId, boolean checked) {
		this.progItem = progItem;
		this.programId = programId;
		this.checked = checked;
	}

	public String getProgItem() { return progItem; }
	public String getProgramId() { return programId; }
	public boolean isChecked() { return checked; }

	public void toggleChecked() { this.checked = !this.checked; }
}
