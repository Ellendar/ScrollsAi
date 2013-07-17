package com.gmail.ellendar.scrollsai.targetingstrategy;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.GameStateViolationException;
import com.gmail.ellendar.scrollsai.Point;

public class GiveUpTargetingStrategy implements TargetingStrategy {

	@Override
	public Point getTarget(GameState state) {
		throw new GameStateViolationException("Targeting failed");
	}

}
