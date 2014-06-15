package com.execute.obj.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.execute.obj.Event;

public class SpinningHi extends Event{

	private String datetimeTriggered;
	private String speed;
	private String duration;
	
	public SpinningHi(String eventGroup, String eventType, String datetimeTriggered, String speed, String duration) {
		super(eventGroup, eventType);
		this.datetimeTriggered = datetimeTriggered;
		this.speed = speed;
		this.duration = duration;
	}

	public String getDatetimeTriggered() {
		return datetimeTriggered;
	}

	public String getSpeed() {
		return speed;
	}

	public String getDuration() {
		return duration;
	}
}
