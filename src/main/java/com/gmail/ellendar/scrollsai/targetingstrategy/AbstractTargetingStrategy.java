package com.gmail.ellendar.scrollsai.targetingstrategy;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;

public abstract class AbstractTargetingStrategy implements TargetingStrategy {

	public Point determineTarget(GameState state, TargetingStrategy fallback) {
		Point target = getTarget(state);
		
		if(target == null) {
			return fallback.determineTarget(state);
		}
		
		else return target;
	}
	
	public abstract Point getTarget(GameState state);

}
