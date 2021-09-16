package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Resource implements Parcelable{
	private String resourceName;
	private String programId;
	private int expectedAmt;
	private int actualAmt;

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
		public Resource createFromParcel(Parcel in){
			return new Resource(in);
		}

		public Resource[] newArray(int size){
			return new Resource[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(programId);
		dest.writeString(resourceName);
		dest.writeInt(expectedAmt);
		dest.writeInt(actualAmt);
	}

	public Resource(Parcel in){
		this.programId = in.readString();
		this.resourceName = in.readString();
		this.expectedAmt = in.readInt();
		this.actualAmt = in.readInt();
	}

	public String getResourceName() { return resourceName; }
	public String getProgramId() { return programId; }
	public int getExpectedAmt() { return expectedAmt; }
	public int getActualAmt() { return actualAmt; }

	public void setActualAmt(int actualAmt) { this.actualAmt = actualAmt; }
}
