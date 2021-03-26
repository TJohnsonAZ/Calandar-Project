package ActivityPackage;

import java.util.ArrayList;
import java.util.List;

public class ActivityDatabase {

	private static List<Activity> data = new ArrayList<Activity>();
	
	public static Activity addActivity(Activity activity) {
		data.add(activity);
		return activity;
	}
	
	public static Activity deleteActivity(String id) {
		Activity activity = getActivity(id);
		data.remove(activity);
		return activity;
	}
	
	public static Activity updateActivity(String id, Activity activity) {
		Activity a = getActivity(id);
		if(a != null) {
			a.setActivityColor(activity.getActivityColor());
			a.setActivityDescription(activity.getActivityDescription());
			a.setActivityName(activity.getActivityName());
			a.setActivityComplete(activity.getActivityComplete());
		}
		return a;
	}
	
	public static Activity getActivity(String id) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getActivityID().equals(id)) {
				return data.get(i);
			}
		}
		return null;
	}
	
	public static List<Activity> getAllActivities() {
		return data;
	}
	
}
