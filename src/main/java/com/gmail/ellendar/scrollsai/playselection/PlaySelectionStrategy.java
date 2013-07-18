package com.gmail.ellendar.scrollsai.playselection;

import java.util.List;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.scroll.Scroll;

/**
 * Strategies for determining which scrolls to play this turn.
 * @author jgill
 *
 */
public interface PlaySelectionStrategy {
	List<Scroll> selectPlays(GameState state);
}
