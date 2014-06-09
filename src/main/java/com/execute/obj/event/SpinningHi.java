package com.execute.obj.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.execute.obj.Event;

public class SpinningHi extends Event{

	private String datetimeTriggered;
	
	public SpinningHi(String eventGroup, String eventType, String datetimeTriggered) {
		super(eventGroup, eventType);
		this.datetimeTriggered = datetimeTriggered;
	}

	public String getDatetimeTriggered() {
		return datetimeTriggered;
	}
}
