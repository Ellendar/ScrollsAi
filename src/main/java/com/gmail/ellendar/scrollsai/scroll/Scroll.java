package com.gmail.ellendar.scrollsai.scroll;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.Point;

public abstract class Scroll {
	protected int cost;
	protected String name;
	
	protected Scroll(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract boolean isUnit();


	/**
	 * If this is a unit card, deploy it to the target coordinates.
	 * If it's a spell card, cast it targeting the cell
	 * @param state Current game state
	 * @param targetX X coordinate of the target/deployment cell
	 * @param targetY Y coordinate of the target/deployment cell
	 */
	public abstract void takeEffect(GameState state, int targetX, int targetY);
	
}
