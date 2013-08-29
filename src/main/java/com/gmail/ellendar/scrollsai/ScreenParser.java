package com.gmail.ellendar.scrollsai;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmail.ellendar.scrollsai.scroll.Scroll;
import com.gmail.ellendar.scrollsai.scroll.UnitScroll;


//TODO: This entire algorthm is crap. The screen is rendered slightly in perspective so the distances between numeric values are 
//variable.
public class ScreenParser {
	
	private static final Logger logger = LoggerFactory.getLogger(ScreenParser.class);
	
	private static final Point GAME_OVER_CRITICAL_POINT = new Point(35, 21);
	private static final int GAME_OVER_COLOR = 0x1B200B;
	
	public static final Point[][] cellOccupiedCriticalPixels = new Point[][] {
		
		{new Point(400,247), new Point(329,316),new Point(374,390),new Point(295,471),new Point(342,559)},
		{new Point(513, 247), new Point(447, 316), new Point(497, 390), new Point(423, 471), new Point(476, 559)},
		{new Point(627, 247), new Point(564, 316), new Point(620, 390), new Point(551, 471), new Point(610, 559)}
	};
	
	@SuppressWarnings("serial")
	public static final Map<Integer, List<Integer>> handCostIndiciesByHandSize = new HashMap<Integer, List<Integer>>() {{
		put(1, Arrays.asList(725));
		put(2, Arrays.asList(703, 808));
		put(3, Arrays.asList(557, 724, 892));
		put(4, Arrays.asList(473, 640, 807, 975));
		put(5, Arrays.asList(391, 558, 723, 891, 1057));
		put(6, Arrays.asList(391, 524, 658, 791, 924, 1057));
		put(7, Arrays.asList(391, 501, 613, 724, 835, 945, 1057));
		put(8, Arrays.asList(391, 486, 581, 677, 772, 867, 962, 1057));	
	}};
	
	static final Point ourTurnCriticalPixel = new Point(187, 795);
	private static final int ourTurnCriticalPixelColor = 0xFEFDFC;
	
	private static final int handRow = 703;
	
	private static final int rgbDistanceThreshold = 60;
	
	private static final PixelMap[] numberMaps;
	private static final int inactiveHandCostColor = 0x8F6D1D;
	private static final int activeHandCostColor = 0xDDC736;
	private static final int occupiedCellSwordCriticalPixelColor = 0xF6F2E7; //246 242 231
	
	static {
		//initialize the training set
		numberMaps = new PixelMap[7];
		
		BufferedImage trainingImage = ImageUtil.readImageResource("costs_training.png");
		
		for(int i = 0; i < 7; ++i) {
			boolean[][] pixels = new boolean[11][16];
			
			for(int y = 0; y < 16; ++y) {
				for(int x = 0; x < 11; ++x) {
					int rgb = trainingImage.getRGB(i*12 + x, y);
					
					pixels[x][y] = isSufficientlyYellow(rgb);
				}
			}
			numberMaps[i] = new PixelMap(pixels);
		}
	}
	
	public static boolean isSufficientlyYellow(int rgb) {
		int delta = Math.min(getColorDistance(rgb, inactiveHandCostColor), getColorDistance(rgb, activeHandCostColor));
		
		return delta < rgbDistanceThreshold;
	}
	
	public static int getColorDistance(int lhs, int rhs) {
		return Math.abs(((lhs >> 16) & 0xFF) - ((rhs >> 16) & 0xFF)) 
			+ Math.abs(((lhs >> 8) & 0xFF) - ((rhs >> 8) & 0xFF)) 
			+ Math.abs((lhs & 0xFF) - (rhs & 0xFF));
	}
	
	public static boolean isCellOccupied(int rgb) {	
		return getColorDistance(rgb, occupiedCellSwordCriticalPixelColor) < 20;
	}
	
	private int countCardsInHand(BufferedImage image) {
		int y = handRow + 2;
		int cardCount = 0;
		boolean currentlyYellow = false;
		int stripeLength = 0;
		int gapLength = 0;
		for(int x = 391; x < 1100; ++x) {
			if(isSufficientlyYellow(image.getRGB(x, y))) {
				gapLength = 0;
				++stripeLength;
				if(!currentlyYellow) {
					currentlyYellow = true;
				}
			}
			else {
				if(currentlyYellow) {
					if(stripeLength > 1) {
						if(gapLength > 4) {
							cardCount++;
							logger.debug("Start: " + (x - stripeLength) + " Length: " + stripeLength);
							gapLength = 0;
							stripeLength = 0;
							currentlyYellow = false;
						}
						else {
							gapLength++;
						}
					}
				}
			}
		}
		
		logger.info("Card count: " + cardCount);
		return cardCount;
	}
	
	private void readHand(GameState state) {
		int cardsInHand = countCardsInHand(state.getScreen());
		
		if(cardsInHand > handCostIndiciesByHandSize.size()) {
			throw new GameStateViolationException("Too many cards in hand, the parser is probably reading pixels it shouldn't");
		}
		List<Integer> cardCostOffsets = handCostIndiciesByHandSize.get(cardsInHand);
		
		List<Scroll> newHand = new ArrayList<Scroll>(cardsInHand);
		for(int i = 0; i < cardsInHand; i++) {
			int xOffset = cardCostOffsets.get(i);
			
			BufferedImage subImage = state.getScreen().getSubimage(xOffset, handRow, 11, 16);
			ImageUtil.saveImage(subImage, "C:\\debug\\handCost" + i + ".png"); //debug
			
			PixelMap costPixelMap = new PixelMap(subImage, 1);
			
			ImageUtil.saveImage(costPixelMap.asImage(), "C:\\debug\\handCostPixelMap" + i + ".png"); //debug
			
			double maxSimilarity = 0;
			int closestMatch = 0;
			int currentNumber = 1;
			for(PixelMap numberMap : numberMaps) {
				if(numberMap == null) {
					continue;
				}
				ImageUtil.saveImage(costPixelMap.and(numberMap).asImage(), "C:\\debug\\andMap_" + currentNumber + ".png");
				double similarity = costPixelMap.and(numberMap).getRelativeDensity(numberMap);
				
				if(similarity > maxSimilarity) {
					maxSimilarity = similarity;
					closestMatch = currentNumber;
				}
				
				currentNumber++;
			}
			
			newHand.add(new UnitScroll(closestMatch, closestMatch, 2, closestMatch));
		}
		
		state.setHand(newHand);
	}
	
	public void parse(GameState state) {
	
		if(state.getScreen() == null) {
			state.captureScreen();
		}
		
		//read whether it's our turn
		int ourTurnColor = state.getScreen().getRGB(ourTurnCriticalPixel.x, ourTurnCriticalPixel.y) & 0xFFFFFF;
		state.setOurTurn(ourTurnColor == ourTurnCriticalPixelColor);
		
		//read whether the game is over
		int gameOverColor = state.getScreen().getRGB(GAME_OVER_CRITICAL_POINT.x, GAME_OVER_CRITICAL_POINT.y) & 0xFFFFFF;
		state.setGameOver(getColorDistance(gameOverColor, GAME_OVER_COLOR) < 50);
		
		readHand(state);
		readOurGrid(state);
	}
	
	public void readOurGrid(GameState state) {
		for(int x = 0; x < 3; ++x) {
			for(int y = 0; y < 5; ++y) {
				Point criticalPoint = cellOccupiedCriticalPixels[x][y];
				if(isCellOccupied(state.getScreen().getRGB(criticalPoint.x, criticalPoint.y))) {
					state.getOurGrid()[x][y] = new Unit(1, 2, 1, x, y);
				}
				else {
					state.getOurGrid()[x][y] = null;
				}
			}
		}
	}
}
