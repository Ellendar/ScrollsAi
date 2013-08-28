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
	
	private static boolean firstCycle = true;
	
	private static int turnNumber = 0;
	
	private static final int LOOP_CYCLE_TIME = 1000;
	private static final int FIRST_LOOP_PER_TURN_CYCLE_TIME = 6000;
	
	public static void main(String args[]) {
		GameState state = new GameState();
		
		while(true) {
			Control.startGame(state);
			
			Control.clearScrollSelection(state);
			Control.toggleHealthDisplay();
			
			state = new GameState();
			state.update();
			
			try {
				while(!state.isGameOver()) {
					gameLoop(state);
				}
				
				Control.leaveMatch(state);
			}
			catch(Exception e) {
				Control.surrender(state);
				Control.wait(5000);
				Control.leaveMatch(state);
			}
			
		}
	}
	
	public static void gameLoop(GameState state) {
		//determine boardstate
		state.update();
		
		if(!state.isOurTurn()) {
			firstCycle = true;
			Control.wait(LOOP_CYCLE_TIME);
			return;
		}
		//if this is the first cycle since it became our turn, wait extra long for the cards to load and initialize turn start values
		else {
			if(firstCycle) {
				logger.info("Starting turn: " + ++turnNumber);
				state.setRemainingMana(state.getMaxMana());
				firstCycle = false;
				Control.wait(FIRST_LOOP_PER_TURN_CYCLE_TIME);
				state.update();
				if(state.getHand().isEmpty()) {
					Control.endTurn(state);
				}
			}
		}
		
		//sacrifice
		Sacrifice sacrifice = sacrificeStrategy.determineSacrifice(state);
		switch(sacrifice.getType()) {
		case DRAW:
			Control.doSacrifice(state, sacrifice);
			break;
		case RAMP:
			state.setMaxMana(state.getMaxMana() + 1);
			state.setRemainingMana(state.getRemainingMana() + 1);
			Control.doSacrifice(state, sacrifice);
			state.discard(sacrifice.getScroll());
			break;
		case SKIP:
		default:
			//do nothing
		}
		
		Control.wait(3000);
		state.update();
		
		/*//cast spells
		for(Scroll scroll : plays) {
			if(!scroll.isUnit()) {
				Point target = new Point(0,0);
				if(scroll.isTargeted()) {
					target = scroll.getTarget(state);
				}
				scroll.takeEffect(state, target.x, target.y);
				state.discard(scroll);
				Control.wait(4000);
			}
		}*/
		//move units
		/*Map<Unit, Point> moves = moveStrategy.getMoves(state);
		for(Entry<Unit, Point> move : moves.entrySet()) {
			//validate move
				//move is possible
				//destination is unoccupied
			
			//do_move
		}*/
		
		//deploy units
		List<Scroll> plays = playStrategy.selectPlays(state);
		logger.info("Initial play strategy established (" + state.getRemainingMana() + "/" + state.getMaxMana() + ")" 
				+ (plays.isEmpty() ? "none" : plays.get(0).toString()));
		
		while(!plays.isEmpty()) {
			for(Scroll scroll : plays) {
				if(scroll.isUnit()) {
					Point target = ((UnitScroll)scroll).getTarget(state);
					scroll.takeEffect(state, target.x, target.y);
					state.discard(scroll);
					Control.wait(6000);
				}
			}
			state.update();
			plays = playStrategy.selectPlays(state);
			if(!plays.isEmpty()) {
				logger.info("Additional play strategy established (" + state.getRemainingMana() + "/" + state.getMaxMana() + ")" 
						+ plays.get(0).toString());
			}
			if(state.isGameOver()) {
				return;
			}
		}
		
		
		//end turn
		Control.endTurn(state);
		
		logger.info(state.toString());
		
		Control.wait(1000);
		
		if(state.isGameOver()) {
			return;
		}
	}
		
}
