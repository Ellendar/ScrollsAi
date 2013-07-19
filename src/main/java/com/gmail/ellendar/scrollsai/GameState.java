package com.gmail.ellendar.scrollsai;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.gmail.ellendar.InvalidReferenceException;
import com.gmail.ellendar.scrollsai.scroll.Scroll;

public class GameState {
	
	private int[] ourRelics;
	private int[] theirRelics;
	
	private int maxMana;
	private int remainingMana;
	
	private Image screen;
	
	//grid, offset
	//since the grid is small and not radially symmetric i'm going to be lazy and use
	//grid coordinates. This complicates adjacency math, but it makes a lot of calculations
	//easier. This is a refactor candidate if the math proves unwieldly.
	private Unit[][] ourGrid;
	
	//their grid is indexed with inverted-x (0,2 is the upper left corner)
	//this allows us to use AI logic on both sides and preserve lane X-position meaning
	private Unit[][] theirGrid;
	private List<Scroll> hand;
	
	public GameState() {
		ourRelics = new int[5];
		theirRelics = new int[5];
		ourGrid = new Unit[3][5];
		theirGrid = new Unit[3][5];
		hand = new ArrayList<Scroll>();
	}
	
	public int[] getOurRelics() {
		return ourRelics;
	}
	public int[] getTheirRelics() {
		return theirRelics;
	}
	
	public Unit[][] getOurGrid() {
		return ourGrid;
	}
	public Unit[][] getTheirGrid() {
		return theirGrid;
	}
	
	public List<Scroll> getHand() {
		return hand;
	}
	
	public boolean isGameOver() {
		//TODO: Real implementation
		return false;
	}

	public Image getScreen() {
		return screen;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public int getRemainingMana() {
		return remainingMana;
	}

	public void setRemainingMana(int remainingMana) {
		this.remainingMana = remainingMana;
	}
	
	public void discard(Scroll scroll) {
		if(!hand.contains(scroll)) {
			throw new InvalidReferenceException("Unable to discard: scroll does not exist");
		}
		hand.remove(scroll);
	}

	public void captureScreen() {
		//TODO: Implement
	}
	
	public void update() {
		captureScreen();
		//TODO: Implement
	}
	
	
	/**
	 *    3/2/3 | 3/2/3 | 3/2/3 || 3/2/3 | 3/2/3 | 3/2/3
	 * -------------------------------------------------
	 * 3/2/3 | 3/2/3 | 3/2/3    ||    3/2/3 | 3/2/3 | 3/2/3
	 * -------------------------------------------------
	 *    3/2/3 | 3/2/3 | 3/2/3  || 3/2/3 | 3/2/3 | 3/2/3
	 * -------------------------------------------------
	 * 3/2/3 | 3/2/3 | 3/2/3    ||    3/2/3 | 3/2/3 | 3/2/3
	 * -------------------------------------------------
	 *    3/2/3 | 3/2/3 | 3/2/3  || 3/2/3 | 3/2/3 | 3/2/3

	 * 
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int y = 0; y < 5; y++) {
			for(int x = 0; x < 3; x++) {
				Unit unit = ourGrid[x][y];
				if()
			}
		}
	}
}
