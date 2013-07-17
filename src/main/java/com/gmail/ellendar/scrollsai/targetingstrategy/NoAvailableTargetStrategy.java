package com.gmail.ellendar.scrollsai.targetingstrategy;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;

public class NoAvailableTargetStrategy implements TargetingStrategy {
	public Point getTarget(GameState state) {
		return null;
	}
}
