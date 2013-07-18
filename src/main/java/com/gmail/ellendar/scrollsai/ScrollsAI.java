package com.gmail.ellendar.scrollsai;

import com.gmail.ellendar.scrollsai.sacrifice.DefaultSacrificeStrategy;
import com.gmail.ellendar.scrollsai.sacrifice.Sacrifice;
import com.gmail.ellendar.scrollsai.sacrifice.SacrificeStrategy;

public class ScrollsAI {
	
	public static void main(String args[]) {
		GameState state = new GameState();
		state.captureScreen();
		
		SacrificeStrategy sacrificeStrategy = new DefaultSacrificeStrategy();
		
		while(!state.isGameOver()) {
			//determine boardstate
			state.update();
			
			//sacrifice
			Sacrifice sacrifice = sacrificeStrategy.determineSacrifice(state);
			//DO_SACRIFICE
			//remember that this needs to update the game state
			
			
			//cast spells
			//move units
			//deploy units
		}
	}
	
	
		
}
