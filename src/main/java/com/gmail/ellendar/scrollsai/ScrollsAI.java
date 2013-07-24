package com.gmail.ellendar.scrollsai;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static SacrificeStrategy sacrificeStrategy = new DefaultSacrificeStrategy();
	private static PlaySelectionStrategy playStrategy = new GreedyPlaySelectionStrategy();
	private static MovementStrategy moveStrategy = new DummyMovementStrategy();
	
	private static final Logger logger = LoggerFactory.getLogger(Scroll.class);
	
	public static void main(String args[]) {
		GameState state = new GameState();
		state.captureScreen();
		
		while(!state.isGameOver()) {
			gameLoop(state);
		}
	}
	
	public static void gameLoop(GameState state) {
		//determine boardstate
		state.update();
		
		//sacrifice
		Sacrifice sacrifice = sacrificeStrategy.determineSacrifice(state);
		switch(sacrifice.getType()) {
		case DRAW:
			Control.doSacrifice(state, sacrifice);
			state.update();
			break;
		case RAMP:
			Control.doSacrifice(state, sacrifice);
			state.setMaxMana(state.getMaxMana() + 1);
			state.setRemainingMana(state.getRemainingMana() + 1);
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
				state.discard(scroll);
			}
		}
		//move units
		Map<Unit, Point> moves = moveStrategy.getMoves(state);
		for(Entry<Unit, Point> move : moves.entrySet()) {
			//validate move
				//move is possible
				//destination is unoccupied
			
			//do_move
		}
		
		//deploy units
		for(Scroll scroll : plays) {
			if(scroll.isUnit()) {
				Point target = ((UnitScroll)scroll).getTarget(state);
				scroll.takeEffect(state, target.x, target.y);
				state.discard(scroll);
			}
		}
	}
		
}
