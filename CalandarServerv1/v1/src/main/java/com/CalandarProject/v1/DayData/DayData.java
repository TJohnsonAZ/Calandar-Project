package com.CalandarProject.v1.DayData;

public class DayData {

	private String user;
	private String dayOfYear;
	private String activity1DayStatus;
	private String activity2DayStatus;
	private String activity3DayStatus;
	private String activity4DayStatus;
		
	public DayData() {
		this.user = "";
		this.dayOfYear = "1";
		this.activity1DayStatus = "1";
		this.activity2DayStatus = "1";
		this.activity3DayStatus = "1";
		this.activity4DayStatus = "1";
	}
	
	public DayData(String user, String dayOfYear, String activity1DayStatus, String activity2DayStatus, String activity3DayStatus, String activity4DayStatus) {
		this.user = user;
		this.dayOfYear = dayOfYear;
		this.activity1DayStatus = activity1DayStatus;
		this.activity2DayStatus = activity2DayStatus;
		this.activity3DayStatus = activity3DayStatus;
		this.activity4DayStatus = activity4DayStatus;
	}
	
	public DayData(String user, String dayNum) {
		this();
		this.dayOfYear = dayNum;
		this.user = user;
		this.activity1DayStatus = "1";
		this.activity2DayStatus = "1";
		this.activity3DayStatus = "1";
		this.activity4DayStatus = "1";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getActivity1DayStatus() {
		return activity1DayStatus;
	}

	public void setActivity1DayStatus(String activity1DayStatus) {
		this.activity1DayStatus = activity1DayStatus;
	}

	public String getActivity2DayStatus() {
		return activity2DayStatus;
	}

	public void setActivity2DayStatus(String activity2DayStatus) {
		this.activity2DayStatus = activity2DayStatus;
	}

	public String getActivity3DayStatus() {
		return activity3DayStatus;
	}

	public void setActivity3DayStatus(String activity3DayStatus) {
		this.activity3DayStatus = activity3DayStatus;
	}

	public String getActivity4DayStatus() {
		return activity4DayStatus;
	}

	public void setActivity4DayStatus(String activity4DayStatus) {
		this.activity4DayStatus = activity4DayStatus;
	}

	public String getDayOfYear() {
		return dayOfYear;
	}
	
	

	@Override
	public String toString() {
		return "DayData [user=" + user + ", dayOfYear=" + dayOfYear + ", activity1DayStatus=" + activity1DayStatus
				+ ", activity2DayStatus=" + activity2DayStatus + ", activity3DayStatus=" + activity3DayStatus
				+ ", activity4DayStatus=" + activity4DayStatus + "]";
	}

	public String[] toStringArray() {
		return new String[] {user ,dayOfYear, activity1DayStatus, activity2DayStatus, activity3DayStatus, activity4DayStatus}; 
	}
	
}
