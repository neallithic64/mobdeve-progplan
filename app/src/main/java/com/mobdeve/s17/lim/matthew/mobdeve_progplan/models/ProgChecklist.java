package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ProgChecklist implements Parcelable {
	private String progItem;
	private String programId;
	private boolean checked;

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
		public ProgChecklist createFromParcel(Parcel in){
			return new ProgChecklist(in);
		}

		public Program[] newArray(int size){
			return new Program[size];
		}
	};

	public ProgChecklist(String progItem, String programId, boolean checked) {
		this.progItem = progItem;
		this.programId = programId;
		this.checked = checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(programId);
		dest.writeString(progItem);
		dest.writeBoolean(checked);
	}

	public ProgChecklist(Parcel in){
		this.programId = in.readString();
		this.progItem = in.readString();
		this.checked = in.readBoolean();
	}

	public String getProgItem() { return progItem; }
	public String getProgramId() { return programId; }
	public boolean isChecked() { return checked; }

	public void toggleChecked() { this.checked = !this.checked; }
}
