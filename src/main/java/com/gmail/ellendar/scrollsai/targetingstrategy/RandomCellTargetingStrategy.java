package com.gmail.ellendar.scrollsai.targetingstrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.gmail.ellendar.ImpossibleException;
import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;
import com.gmail.ellendar.scrollsai.Unit;

public class RandomCellTargetingStrategy implements TargetingStrategy {
	
	private static final Random random = new Random(System.currentTimeMillis());
	
	enum Occupied {
		OCCUPIED, FREE, ANY;
	}
	
	enum Grid {
		OURS, THEIRS;
	}
	
	public RandomCellTargetingStrategy(Occupied occupied, Grid grid) {
		this.occupied = occupied;
		this.gridChoice = grid;
	}
	
	private Occupied occupied;
	private Grid gridChoice;

	@Override
	public Point getTarget(GameState state) {
		List<Point> candidates = new ArrayList<Point>();
		Unit[][] grid;
		switch(gridChoice) {
		case OURS:
			grid = state.getOurGrid();
			break;
		case THEIRS:
			grid = state.getTheirGrid();
			break;
		default:
			throw new ImpossibleException();
		}
		
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 5; y++) {
				if((grid[x][y] == null && occupied != Occupied.OCCUPIED)
						|| (grid[x][y] != null && occupied != Occupied.FREE)) {
					candidates.add(new Point(x, y));
				}
			}
		}
		
		return candidates.get(random.nextInt(candidates.size()));
	}

}
