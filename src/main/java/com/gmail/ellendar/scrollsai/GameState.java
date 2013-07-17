package com.gmail.ellendar.scrollsai;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.gmail.ellendar.scrollsai.scroll.Scroll;

public class GameState {
	
	private int[] ourRelics;
	private int[] theirRelics;
	
	private int maxMana;
	private int remainingMana;
	
	private Image screen;
	
	//grid, offset
	private Unit[][] ourGrid;
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

	public void captureScreen() {
		//TODO: Implement
	}
	
	public void update() {
		captureScreen();
		//TODO: Implement
	}
}
