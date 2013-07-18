package com.gmail.ellendar.scrollsai.scroll;

import com.gmail.ellendar.scrollsai.GameState;
import com.gmail.ellendar.scrollsai.GameStateViolationException;
import com.gmail.ellendar.scrollsai.Point;
import com.gmail.ellendar.scrollsai.Unit;
import com.gmail.ellendar.scrollsai.targetingstrategy.DefaultUnitDeploymentStrategy;

/**
 * Dumb unit scroll implementation. For now they're just power, toughness, and delay on a card. Maybe more subtleness later.
 * @author Ellendar
 *
 */
public class UnitScroll extends TargetedScroll {
	
	private int initPower;
	private int initToughness;
	private int delay;
	
	public UnitScroll(int cost, int initPower, int initToughness, int delay) {
		super(cost);
		this.initPower = initPower;
		this.initToughness = initToughness;
		this.delay = delay;
	}
	
	public Unit asUnit(int x, int y) {
		Unit unit = new Unit();
		unit.x = x;
		unit.y = y;
		unit.power = getInitPower();
		unit.health = getInitToughness();
		
		return unit;
	}

	@Override
	public boolean isUnit() {
		return true;
	}

	@Override
	public void takeEffect(GameState state, int targetX, int targetY) {
		if(state.getOurGrid()[targetX][targetX] != null) {
			throw new GameStateViolationException("Unable to play unit scroll at coordinates occupied by another scroll");
		}
	}

	public int getInitPower() {
		return initPower;
	}

	public int getInitToughness() {
		return initToughness;
	}
	
	public int getDelay() {
		return delay;
	}

	@Override
	public Point getTarget(GameState state) {
		return new DefaultUnitDeploymentStrategy().getTarget(state);
	}

	@Override
	boolean targetsEnemy() {
		return false;
	}
	
}
