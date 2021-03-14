package com.CalandarProject.v1;

import java.util.UUID;

public class Event {

	private String id;
	private String color;
	private String description;
	
	public Event(String color, String description) {
		this(UUID.randomUUID().toString(), color, description);
	}

	public Event(String id, String color, String description) {
		this.id = id;
		this.color = color;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public String getDescription() {
		return description;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", color=" + color + ", description=" + description + "]";
	}
	
	public String[] toStringArray() {
		return new String[] {String.valueOf(id), color, description};
	}
	
	
	
}
