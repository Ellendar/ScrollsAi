package com.gmail.ellendar.scrollsai.targetingstrategy;


import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;

public class FalloverTargetingStrategy implements TargetingStrategy {
	
	TargetingStrategy[] strategies;
	
	protected FalloverTargetingStrategy(TargetingStrategy... strategies) {
		this.strategies = strategies;
	}

	@Override
	public Point getTarget(GameState state) {
		Point target = null;
		for(TargetingStrategy strategy : strategies) {
			target = strategy.getTarget(state);
			if(target != null) {
				return target;
			}
		}
		
		return target;
	}

}
