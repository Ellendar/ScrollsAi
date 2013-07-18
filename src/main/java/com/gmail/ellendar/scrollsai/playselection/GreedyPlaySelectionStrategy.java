package com.gmail.ellendar.scrollsai.playselection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.scroll.Scroll;

/**
 * Play the most expensive card you can, recursively, until no plays are available.
 * @author jgill
 *
 */
public class GreedyPlaySelectionStrategy implements PlaySelectionStrategy {

	@Override
	public List<Scroll> selectPlays(GameState state) {
		List<Scroll> hand = new ArrayList<Scroll>(state.getHand());
		
		Collections.sort(hand, new Comparator<Scroll>() {
			@Override
			public int compare(Scroll lhs, Scroll rhs) {
				//This fails for costs at the extremes, but works well enough here.
				return rhs.getCost() - lhs.getCost();
			}
		});
		
		int remainingMana = state.getMaxMana();
	}
}
