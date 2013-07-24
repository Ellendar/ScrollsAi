package com.gmail.ellendar.scrollsai;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.gmail.ellendar.InvalidReferenceException;
import com.gmail.ellendar.scrollsai.scroll.Scroll;

public class GameState {
	
	private int[] ourRelics;
	private int[] theirRelics;
	
	private int maxMana;
	private int remainingMana;
	
	private BufferedImage screen;
	
	private static final int SCROLLS_MONITOR_CENTER_X = 2500;
	
	private static final int SCREEN_WIDTH = 1440;
	private static final int SCREEN_HEIGHT = 900;
	
	//grid, offset
	//since the grid is small and not radially symmetric i'm going to be lazy and use
	//grid coordinates. This complicates adjacency math, but it makes a lot of calculations
	//easier. This is a refactor candidate if the math proves unwieldly.
	private Unit[][] ourGrid;
	
	//185/209/234
	private int WINDOW_COLOR = 0xB9D1EA00;
	
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
		screen = Control.robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		
		int screenStartX = 0;
		int screenStartY = 0;
		boolean windowTopEncountered = false;
		//draw down from top center until you see the window color.
		for(int y = 0; y < 500; y++) {
			//strip alpha for comparison
			int rgb = screen.getRGB(SCROLLS_MONITOR_CENTER_X, y) & 0xFFFFFF00;
			
			if(rgb == WINDOW_COLOR) {
				windowTopEncountered = true;
			}
			else if(windowTopEncountered) {
				screenStartY = y;
				break;
			}
		}
		
		for(int x = SCROLLS_MONITOR_CENTER_X; x > 0; x--) {
			int rgb = screen.getRGB(x, screenStartY) & 0xFFFFFF00;
			
			if(rgb == WINDOW_COLOR) {
				screenStartX = x+1;
			}
		}
		
		screen = screen.getSubimage(screenStartX, screenStartY, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	public void update() {
		captureScreen();
	
		//read whether it's our turn
		//read our hand
		//read our grid
		//read their grid
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
			sb.append(ourRelics[y] + "| ");
			if(y % 2 == 0) {
				sb.append("   ");
			}

			for(int x = 0; x < 3; x++) {
				Unit unit = ourGrid[x][y];
				if(unit == null) {
					sb.append("     ");
				}
				else {
					sb.append(unit);
				}
				if(x != 2) {
					sb.append(" | ");
				}
			}
			
			if(y % 2 == 1) {
				sb.append("    ||    ");
			}
			else {
				sb.append(" || ");
			}
			

			for(int x = 2; x >= 0 ; --x) {
				Unit unit = theirGrid[x][y];
				if(unit == null) {
					sb.append("     ");
				}
				else {
					sb.append(unit);
				}
				if(x != 0) {
					sb.append(" | ");
				}
			}
			if(y % 2 == 0) {
				sb.append("   ");
			}
			sb.append(" |" + theirRelics[y]);
			if(y != 4) {
				sb.append('\n');
				sb.append("-------------------------------------------------\n");
			}
		}
		sb.append("\nMana: " + remainingMana + '/' + maxMana + "\n"); 
		sb.append("Hand: ");
		for(Scroll scroll : hand) {
			sb.append(scroll);
		}
		
		sb.append("\n");
		
		return sb.toString();
	}
}
