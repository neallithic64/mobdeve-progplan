package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Outcome implements Parcelable {
	private String outcomeName;
	private String programId;
	private int expectedVal;
	private int actualVal;

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
		public Outcome createFromParcel(Parcel in){
			return new Outcome(in);
		}

		public Outcome[] newArray(int size){
			return new Outcome[size];
		}
	};

	public Outcome(String outcomeName, String programId, int expectedVal) {
		this.outcomeName = outcomeName;
		this.programId = programId;
		this.expectedVal = expectedVal;
	}

	public Outcome(String outcomeName, int expectedVal) {
		this.programId = "";
		this.outcomeName = outcomeName;
		this.expectedVal = expectedVal;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(programId);
		dest.writeString(outcomeName);
		dest.writeInt(expectedVal);
		dest.writeInt(actualVal);
	}

	public Outcome(Parcel in){
		this.programId = in.readString();
		this.outcomeName = in.readString();
		this.expectedVal = in.readInt();
		this.actualVal = in.readInt();
	}

	public String getOutcomeName() { return outcomeName; }
	public String getProgramId() { return programId; }
	public int getExpectedVal() { return expectedVal; }
	public int getActualVal() { return actualVal; }

	public void setActualVal(int actualVal) { this.actualVal = actualVal; }
}
