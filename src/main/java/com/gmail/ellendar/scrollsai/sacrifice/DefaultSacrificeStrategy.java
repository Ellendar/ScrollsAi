package com.gmail.ellendar.scrollsai.sacrifice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.sacrifice.Sacrifice.Type;
import com.gmail.ellendar.scrollsai.scroll.Scroll;

/**
 * If below critical ramp value (3? 4?) ramp else draw (always act)
 * If above critical ramp value and a drop is in hand 1 greater than current resources, ramp. 
 * (This may orphan 5 drops, but let the sacrifice rules break stalemates)
 * If a card exists more than 2 off from the current resource count (maybe +2/-1) 
 * add it to the sacrifice candidates. If no sacrifice candidate from that pool, narrow incrementally (down to all cards). 
 * Sacrifice randomly from the pool (prevent card stalemates)
 * 
 * @author jgill
 *
 */
public class DefaultSacrificeStrategy implements SacrificeStrategy {
	
	private static final int INITIAL_RAMP_THRESHOLD = 3;
	
	private static final int POSITIVE_SACRIFICE_PRIORITY_THRESHOLD = 2;
	private static final int NEGATIVE_SACRIFICE_PRIORITY_THRESHOLD = -1;
	
	private static final Random random = new Random();

	public Sacrifice determineSacrifice(GameState state) {
		List<Scroll> hand = state.getHand();
		
		//are we drawing or ramping?
		Type type;
		if(hand.size() == 0) {
			return new Sacrifice(Type.SKIP, null);
		}
		else if(hand.size() > 5) {
			type = Type.RAMP;
		}
		else if(hand.size() == 1) {
			type = Type.DRAW;
		}
		else if(state.getMaxMana() < INITIAL_RAMP_THRESHOLD) {
			type = Type.RAMP;
		}
		else if(handContainsCost(hand, state.getMaxMana() + 1)) {
			type = Type.RAMP;
		}
		else {
			type = Type.DRAW;
		}
		
		//which card are we sacrificing
		List<Scroll> sacrificeCandidates = new ArrayList<Scroll>();
		
		for(Scroll scroll : hand) {
			if(scroll.getCost() < state.getMaxMana() + NEGATIVE_SACRIFICE_PRIORITY_THRESHOLD) {
				sacrificeCandidates.add(scroll);
			}
			else if(scroll.getCost() > state.getMaxMana() + POSITIVE_SACRIFICE_PRIORITY_THRESHOLD) {
				sacrificeCandidates.add(scroll);
			}
		}
		
		if(sacrificeCandidates.isEmpty()) {
			sacrificeCandidates = hand;
		}
		
		//a random card from the candidate set
		return new Sacrifice(type, sacrificeCandidates.get(random.nextInt(sacrificeCandidates.size())));
	}

	private boolean handContainsCost(List<Scroll> hand, int cost) {
		for(Scroll scroll : hand) {
			if(scroll.getCost() == cost) {
				return true;
			}
		}
		return false;
	}
}
