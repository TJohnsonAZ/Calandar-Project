package com.CalandarProject.v1;

import java.util.ArrayList;
import java.util.List;

public class DayDatabase {

	private static List<DayData> data = new ArrayList<DayData>();

	public static DayData addDayData(DayData dayData) {
		data.add(dayData);
		return dayData;
	}
	
	public static DayData updateDayData(String day, DayData dayData) {
		DayData dd = getDayData(day);
		if(dd != null) {
			dd.getSetColor();
		}
		return dd;
	}
	
	public static DayData getDayData(String day) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getDayOfMonth().equals(day)) {
				return data.get(i);
			}
		}
		return null;
	}
	
	public static List<DayData> getAllDayData() {
		return data;
	}
}