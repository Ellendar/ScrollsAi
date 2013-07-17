package com.gmail.ellendar.scrollsai;

public class ScrollsAI {
	
	public static void main(String args[]) {
		GameState state = new GameState();
		state.captureScreen();
		
		while(!state.isGameOver()) {
			state.update();
		}
	}
	
	
		//determine boardstate
		//sacrifice
		//cast spells
		//move units
		//deploy units
}
