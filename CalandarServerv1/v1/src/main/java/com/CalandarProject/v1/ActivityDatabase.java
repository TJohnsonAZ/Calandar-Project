package com.CalandarProject.v1;

import java.util.ArrayList;
import java.util.List;

public class ActivityDatabase {

	private static List<Event> data = new ArrayList<Event>();
	
	public static void addEvent(Event addedEvent) {
		data.add(addedEvent);
	}
	
	public static void deleteEvent(Event removedEvent) {
		data.remove(removedEvent);
	}
	
	public static void updateEvent(Event updatedEvent) {
		for(int i = 0; i < data.size(); i++) {
			if(updatedEvent.getId() == data.get(i).getId()) {
				Event event = data.get(i);
				event.setColor(updatedEvent.getColor());
				event.setDescription(updatedEvent.getDescription());
			}
		}
	}
	
	public static List<Event> getAllEvents() {
		return data;
	}
	
}
