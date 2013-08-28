package com.gmail.ellendar.scrollsai.scroll;

import com.gmail.ellendar.scrollsai.Control;
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
public class UnitScroll extends Scroll {
	
	private int initPower;
	private int initToughness;
	private int initDelay;
	
	public UnitScroll(int cost, int initPower, int initDelay, int initToughness) {
		super(cost);
		this.initPower = initPower;
		this.initToughness = initToughness;
		this.initDelay = initDelay;
	}
	
	public Unit asUnit(int x, int y) {
		Unit unit = new Unit();
		unit.setX(x);
		unit.setY(y);
		unit.setPower(getInitPower());
		unit.setHealth(getInitToughness());
		unit.setInitialAttackDelay(initDelay);
		
		return unit;
	}

	@Override
	public boolean isUnit() {
		return true;
	}

	@Override
	public void takeEffect(GameState state, int x, int y) {
		if(state.getOurGrid()[x][y] != null) {
			throw new GameStateViolationException("Unable to play unit scroll at coordinates occupied by another scroll");
		}
		
		//Temporarily, for now, just try to cast it as untargeted anyway. 
		//This makes unit scrolls disproportionally deploy in the bottom row.
		Control.clickScroll(state, this);
		Control.wait(500);
		Control.castUntargetedScroll(state, this);
		Control.wait(200);
		Control.clearScrollSelection(state);
		Control.wait(200);
		Control.clickScroll(state, this);
		Control.wait(200);
		Control.clickOurGrid(state, x, y);
		Control.wait(200);
		Control.clearScrollSelection(state);
		state.getOurGrid()[x][y] = asUnit(x, y);
		
	}

	public int getInitPower() {
		return initPower;
	}

	public int getInitToughness() {
		return initToughness;
	}
	
	public int getDelay() {
		return initDelay;
	}

	@Override
	public Point getTarget(GameState state) {
		return new DefaultUnitDeploymentStrategy().getTarget(state);
	}

	@Override
	public boolean targetsEnemy() {
		return false;
	}

	@Override
	public boolean isTargeted() {
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + "Unit" + ": (" + cost + ") " + initPower + "/" + initDelay + "/" + initToughness + ")";
	}
}
