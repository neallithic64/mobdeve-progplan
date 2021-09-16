package com.mobdeve.s17.lim.matthew.mobdeve_progplan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Program implements Parcelable {
	private String programId;
	private String userCreated;
	private	String programTitle;
	private Date startDate;
	private Date endDate;
	private String street;
	private String city;
	private int progress;
	private String status;

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
		public Program createFromParcel(Parcel in){
			return  new Program(in);
		}

		public Program[] newArray(int size){
			return new Program[size];
		}
	};
	public Program(String programId, String userCreated, String programTitle, Date startDate, Date endDate, String street, String city, int progress, String status) {
		this.programId = programId;
		this.userCreated = userCreated;
		this.programTitle = programTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.street = street;
		this.city = city;
		this.progress = progress;
		this.status = status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(programId);
		dest.writeString(userCreated);
		dest.writeString(programTitle);
		dest.writeString(startDate.toString());
		dest.writeString(endDate.toString());
		dest.writeString(street);
		dest.writeString(city);
		dest.writeInt(progress);
		dest.writeString(status);
	}

	public Program(Parcel in){
		this.programId = in.readString();
		this.userCreated = in.readString();
		this.programTitle = in.readString();
		this.startDate = new Date(in.readString());
		this.endDate = new Date(in.readString());
		this.street = in.readString();
		this.city = in.readString();
		this.progress = in.readInt();
		this.status = in.readString();
	}

	public String getProgramId() { return programId; }
	public String getUserCreated() { return userCreated; }
	public String getProgramTitle() { return programTitle; }
	public Date getStartDate() { return startDate; }
	public Date getEndDate() { return endDate; }
	public String getStreet() { return street; }
	public String getCity() { return city; }
	public int getProgress() { return progress; }
	public String getStatus() { return status; }

	public void setStartDate(Date startDate) { this.startDate = startDate; }
	public void setEndDate(Date endDate) { this.endDate = endDate; }
	public void setCity(String city) { this.city = city; }
	public void setProgress(int progress) { this.progress = progress; }
	public void setStatus(String status) { this.status = status; }
}
