package com.gmail.ellendar.scrollsai.targetingstrategy;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;
import com.gmail.ellendar.scrollsai.Unit;

/**
 * Greedy search for the enemy target with the lowest health
 * @author Ellendar
 *
 */
public class TargetLowestHealthEnemy implements TargetingStrategy {

	@Override
	public Point getTarget(GameState state) {
		int lowestHealth = Integer.MAX_VALUE;
		Point target = null;
		Unit[][] grid = state.getTheirGrid();
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 5; y++) {
				if(grid[x][y] != null) {
					if(grid[x][y].getHealth() < lowestHealth) {
						lowestHealth = grid[x][y].getHealth();
						target = new Point(x,y);
					}
				}
			}
		}
		
		return target;
	}
	
}
