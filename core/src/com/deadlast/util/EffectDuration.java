package com.deadlast.util;

public class EffectDuration {
	
	public enum Time {
		
		INSTANT, SHORT, NORMAL, LONG, INFINITE;
		
	}
	
	private Time duration;
	
	private double timeRemaining;
	
	public EffectDuration(Time duration) {
		this.duration = duration;
		switch(duration) {
		case INSTANT:
			timeRemaining = 0;
			break;
		case SHORT:
			timeRemaining = 10;
			break;
		case NORMAL:
			timeRemaining = 15;
			break;
		case LONG:
			timeRemaining = 20;
			break;
		case INFINITE:
			timeRemaining = -1;
		}
	}
	
	public void update(float delta) {
		if (duration == Time.INSTANT || duration == Time.INFINITE) {
			return;
		} else {
			timeRemaining = Math.max(0, timeRemaining - delta);
		}
	}
	
	public boolean isZero() {
		if (duration == Time.INSTANT) {
			return true;
		}
		else if (duration == Time.INFINITE) {
			return false;
		}
		else {
			return timeRemaining == 0 ? true : false;
		}
	}
	
}
