package com.CalandarProject.v1;

import java.util.ArrayList;
import java.util.List;

public class DayDatabase {

	private static List<DayData> data = new ArrayList<DayData>();

	public static DayData addDayData(DayData dayData) {
		data.add(dayData);
		return dayData;
	}
	
	public static DayData updateDayData(String day, DayData dayStatus) {
		DayData dd = getDayData(day);
		if(dd != null) {
			dd.setActivity1DayStatus(dayStatus.getActivity1DayStatus());
			dd.setActivity2DayStatus(dayStatus.getActivity2DayStatus());
			dd.setActivity3DayStatus(dayStatus.getActivity3DayStatus());
			dd.setActivity4DayStatus(dayStatus.getActivity4DayStatus());
		}
		return dd;
	}
	
	public static DayData getDayData(String day) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getDayOfYear().equals(day)) {
				return data.get(i);
			}
		}
		return null;
	}
	
	public static List<DayData> getAllDayData() {
		return data;
	}
}
