package com.gmail.ellendar.scrollsai;

import java.awt.image.BufferedImage;

public class ScreenParser {
	
	private static final int UPPER_LEFT_CELL_X_OFFSET = 0;
	private static final int UPPER_LEFT_CELL_Y_OFFSET = 0;
	private static final int CELL_WIDTH = 0;
	private static final int CELL_HEIGHT = 0;
	
	private static final int POWER_X_OFFSET = 0;
	private static final int POWER_Y_OFFSET = 0;
	private static final int POWER_WIDTH = 0;
	private static final int POWER_HEIGHT = 0;
	
	private static final int DELAY_X_OFFSET = 0;
	private static final int DELAY_Y_OFFSET = 0;
	private static final int DELAY_WIDTH = 0;
	private static final int DELAY_HEIGHT = 0;
	
	private static final int HEALTH_X_OFFSET = 0;
	private static final int HEALTH_Y_OFFSET = 0;
	private static final int HEALTH_WIDTH = 0;
	private static final int HEALTH_HEIGHT = 0;
	
	private static final 
	
	public void parse(GameState state, BufferedImage screen) {
		//read whether it's our turn
		//read our hand
		//read our grid
		//read their grid
	}
	
	public void readOurGrid(GameState state, BufferedImage screen) {
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 5; y++) {
				int cellXOffset = UPPER_LEFT_CELL_X_OFFSET + x * CELL_WIDTH;
				int cellYOffset = UPPER_LEFT_CELL_Y_OFFSET + x * CELL_HEIGHT;
				
				BufferedImage power = screen.getSubimage(cellXOffset + POWER_X_OFFSET, cellYOffset + POWER_Y_OFFSET, 
						POWER_WIDTH, POWER_HEIGHT);
				
				BufferedImage delay = screen.getSubimage(cellXOffset + DELAY_X_OFFSET, cellYOffset + DELAY_Y_OFFSET, 
						DELAY_WIDTH, DELAY_HEIGHT);
				
				BufferedImage health = screen.getSubimage(cellXOffset + HEALTH_X_OFFSET, cellYOffset + HEALTH_Y_OFFSET, 
						HEALTH_WIDTH, HEALTH_HEIGHT);
				
			}
		}
	}
}
