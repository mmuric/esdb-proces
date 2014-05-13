package com.execute.obj;

public class Event {
	
	private String eventGroup;
	private String eventType;
	
	public Event(String eventGroup, String eventType) {
		this.eventGroup = eventGroup;
		this.eventType = eventType;
	}

	public String getEventGroup() {
		return eventGroup;
	}

	public String getEventType() {
		return eventType;
	}
}
