package com.gmail.ellendar.scrollsai.sacrifice;

import java.util.List;

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

	@Override
	public Sacrifice determineSacrifice(GameState state) {
		List<Scroll> hand = state.getHand();
		
		//are we drawing or ramping?
		Type type;
		if(hand.size() == 0) {
			type = Type.SKIP;
		}
	}

}
