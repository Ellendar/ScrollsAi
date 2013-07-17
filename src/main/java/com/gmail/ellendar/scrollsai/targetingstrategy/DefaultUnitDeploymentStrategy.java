package com.gmail.ellendar.scrollsai.targetingstrategy;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;
import com.gmail.ellendar.scrollsai.targetingstrategy.RandomCellTargetingStrategy.Grid;
import com.gmail.ellendar.scrollsai.targetingstrategy.RandomCellTargetingStrategy.Occupied;

public class DefaultUnitDeploymentStrategy implements TargetingStrategy {

	@Override
	public Point getTarget(GameState state) {
		return new FalloverTargetingStrategy(
				new RandomCellTargetingStrategy(Occupied.FREE, Grid.OURS), 
				new NoAvailableTargetStrategy()).getTarget(state);
	}

}
