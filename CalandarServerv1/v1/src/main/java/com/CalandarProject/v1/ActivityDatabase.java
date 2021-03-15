package com.CalandarProject.v1;

import java.util.ArrayList;
import java.util.List;

public class ActivityDatabase {

	private static List<Activity> data = new ArrayList<Activity>();
	
	public static void addActivity(Activity activity) {
		data.add(activity);
	}
	
	public static void deleteActivity(Activity activity) {
		data.remove(activity);
	}
	
	public static void updateActivity(Activity activity) {
		for(int i = 0; i < data.size(); i++) {
			if(activity.getActivityID() == data.get(i).getActivityID()) {
				Activity a = data.get(i);
				a.setActivityColor(activity.getActivityColor());
				a.setActivityDescription(activity.getActivityDescription());
			}
		}
	}
	
	public static List<Activity> getAllEvents() {
		return data;
	}
	
}
