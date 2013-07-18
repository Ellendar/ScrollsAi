package com.gmail.ellendar.scrollsai;

import java.util.List;

import com.gmail.ellendar.scrollsai.playselection.GreedyPlaySelectionStrategy;
import com.gmail.ellendar.scrollsai.playselection.PlaySelectionStrategy;
import com.gmail.ellendar.scrollsai.sacrifice.DefaultSacrificeStrategy;
import com.gmail.ellendar.scrollsai.sacrifice.Sacrifice;
import com.gmail.ellendar.scrollsai.sacrifice.SacrificeStrategy;
import com.gmail.ellendar.scrollsai.scroll.Scroll;

public class ScrollsAI {
	
	public static void main(String args[]) {
		GameState state = new GameState();
		state.captureScreen();
		
		SacrificeStrategy sacrificeStrategy = new DefaultSacrificeStrategy();
		PlaySelectionStrategy playStrategy = new GreedyPlaySelectionStrategy();
		MovementStrategy moveStrategy = new DummyMovementStrategy();
		
		while(!state.isGameOver()) {
			//determine boardstate
			state.update();
			
			//sacrifice
			Sacrifice sacrifice = sacrificeStrategy.determineSacrifice(state);
			//DO_SACRIFICE
			//remember that this needs to update the game state
			
			List<Scroll> plays = playStrategy.selectPlays(state);
			//cast spells
			for(Scroll scroll : plays) {
				if(!scroll.isUnit()) {
					Point target = new Point(0,0);
					if(scroll.isTargeted()) {
						target = scroll.getTarget(state);
					}
					scroll.takeEffect(state, target.x, target.y);
				}
			}
			//move units
			
			//deploy units
			for(Scroll scroll : plays) {
				
			}
		}
	}
	
	
		
}
