package com.gmail.ellendar.scrollsai.movement;

import java.util.Map;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;
import com.gmail.ellendar.scrollsai.Unit;

public interface MovementStrategy {
	public Map<Unit, Point> getMoves(GameState state);
}
