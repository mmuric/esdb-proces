package com.execute.obj.event;

import com.execute.obj.Event;

public class SpeedLimit extends Event{
	
	float speedLimit;
	float speedOver;
	
	public SpeedLimit( String eventGroup, String eventType, float speedLimit, float speedOver ) {
		super(eventGroup, eventType);
		this.speedLimit = speedLimit;
		this.speedOver = speedOver;
	}

	public float getSpeedLimit() {
		return speedLimit;
	}

	public float getSpeedOver() {
		return speedOver;
	}	
}
