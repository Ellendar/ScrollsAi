package com.gmail.ellendar.scrollsai.scroll;

import com.gmail.ellendar.scrollsai.GameState;

public abstract class SpellScroll extends Scroll {

	protected SpellScroll(int cost) {
		super(cost);
	}

	@Override
	public boolean isUnit() {
		return false;
	}

	@Override
	public abstract void takeEffect(GameState state, int targetX, int targetY);

}
