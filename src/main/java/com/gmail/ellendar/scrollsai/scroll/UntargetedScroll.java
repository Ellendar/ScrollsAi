package com.gmail.ellendar.scrollsai.scroll;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;

public abstract class UntargetedScroll extends Scroll {

	protected UntargetedScroll(int cost) {
		super(cost);
	}
	
	@Override
	public Point getTarget(GameState state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean targetsEnemy() {
		return false;
	}
	
	@Override
	public boolean isUnit() {
		return false;
	}
}
