package com.gmail.ellendar.scrollsai.sacrifice;

import com.gmail.ellendar.scrollsai.GameState;

public interface SacrificeStrategy {
	public Sacrifice determineSacrifice(GameState state);
}
