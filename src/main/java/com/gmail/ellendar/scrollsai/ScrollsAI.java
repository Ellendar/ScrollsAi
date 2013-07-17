package com.gmail.ellendar.scrollsai;

public class ScrollsAI {
	
	public static void main(String args[]) {
		GameState state = new GameState();
		state.captureScreen();
		
		while(!state.isGameOver()) {
			//determine boardstate
			state.update();
			//sacrifice
			//cast spells
			//move units
			//deploy units
		}
	}
	
	
		
}
