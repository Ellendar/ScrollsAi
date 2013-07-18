package com.gmail.ellendar.scrollsai.scroll;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;

public abstract class TargetedScroll extends Scroll {

	protected TargetedScroll(int cost) {
		super(cost);
	}
	
	abstract Point getTarget(GameState state);
	abstract boolean targetsEnemy();
}
