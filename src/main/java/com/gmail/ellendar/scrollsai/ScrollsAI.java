package com.gmail.ellendar.scrollsai;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gmail.ellendar.scrollsai.movement.DummyMovementStrategy;
import com.gmail.ellendar.scrollsai.movement.MovementStrategy;
import com.gmail.ellendar.scrollsai.playselection.GreedyPlaySelectionStrategy;
import com.gmail.ellendar.scrollsai.playselection.PlaySelectionStrategy;
import com.gmail.ellendar.scrollsai.sacrifice.DefaultSacrificeStrategy;
import com.gmail.ellendar.scrollsai.sacrifice.Sacrifice;
import com.gmail.ellendar.scrollsai.sacrifice.SacrificeStrategy;
import com.gmail.ellendar.scrollsai.scroll.Scroll;
import com.gmail.ellendar.scrollsai.scroll.UnitScroll;

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
			switch(sacrifice.getType()) {
			case DRAW:
				//do_draw
				state.update();
				break;
			case RAMP:
				//do_ramp
				state.discard(sacrifice.getScroll());
				break;
			case SKIP:
			default:
				//do nothing
			}
			
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
			Map<Unit, Point> moves = moveStrategy.getMoves(state);
			for(Entry<Unit, Point> move : moves.entrySet()) {
				//validate move
					//move is possible
					//destination is unoccupied
			}
			
			//deploy units
			for(Scroll scroll : plays) {
				if(scroll.isUnit()) {
					Point target = ((UnitScroll)scroll).getTarget(state);
					scroll.takeEffect(state, target.x, target.y);
				}
			}
		}
	}
	
	
		
}
