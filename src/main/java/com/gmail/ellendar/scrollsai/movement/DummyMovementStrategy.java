package com.gmail.ellendar.scrollsai.movement;

import java.util.HashMap;
import java.util.Map;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;
import com.gmail.ellendar.scrollsai.Unit;

/**
 * Dummy movement (don't move)
 * @author jgill
 *
 */
public class DummyMovementStrategy implements MovementStrategy {

	@Override
	public Map<Unit, Point> getMoves(GameState state) {
		return new HashMap<Unit, Point>(0);
	}
	
}
