package com.CalandarProject.v1;

public class DayData {

	private String user;
	private String dayOfYear;
	private String activityStatus[];
		
	public DayData() {
		this.user = "";
		activityStatus = new String[]{ "1", "1", "1", "1" };
	}
	
	public DayData(String user, String dayOfYear, String activity1DayStatus, String activity2DayStatus, String activity3DayStatus, String activity4DayStatus) {
		this.user = user;
		this.dayOfYear = dayOfYear;
		this.activityStatus = new String[]{ activity1DayStatus, activity2DayStatus, activity3DayStatus, activity4DayStatus };
	}
	
	public DayData(String user, String dayNum) {
		this();
		this.dayOfYear = dayNum;
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getActivity1DayStatus() {
		return activityStatus[0];
	}

	public void setActivity1DayStatus(String activity1DayStatus) {
		this.activityStatus[0] = activity1DayStatus;
	}

	public String getActivity2DayStatus() {
		return activityStatus[1];
	}

	public void setActivity2DayStatus(String activity2DayStatus) {
		this.activityStatus[1] = activity2DayStatus;
	}

	public String getActivity3DayStatus() {
		return activityStatus[2];
	}

	public void setActivity3DayStatus(String activity3DayStatus) {
		this.activityStatus[2] = activity3DayStatus;
	}

	public String getActivity4DayStatus() {
		return activityStatus[3];
	}

	public void setActivity4DayStatus(String activity4DayStatus) {
		this.activityStatus[3] = activity4DayStatus;
	}

	public String getDayOfYear() {
		return dayOfYear;
	}
	
	

	@Override
	public String toString() {
		return "DayData [user=" + user + ", dayOfYear=" + dayOfYear + ", activity1DayStatus=" + activityStatus[0]
				+ ", activity2DayStatus=" + activityStatus[1] + ", activity3DayStatus=" + activityStatus[2]
				+ ", activity4DayStatus=" + activityStatus[3] + "]";
	}

	public String[] toStringArray() {
		return new String[] {user ,dayOfYear, activityStatus[0], activityStatus[1], activityStatus[2], activityStatus[3]}; 
	}
	
}
