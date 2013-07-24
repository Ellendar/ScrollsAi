package com.gmail.ellendar.scrollsai;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageUtil {
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int WHITE = Color.WHITE.getRGB();
	
	private static final double RED_WEIGHTING = .30;
	private static final double GREEN_WEIGHTING = .59;
	private static final double BLUE_WEIGHTING = .11;

	private static final int DEFAULT_LUMINOSITY_THRESHOLD = 110;
	
	public static BufferedImage convertToMonochrome(BufferedImage image) {
		return convertToMonochrome(image, DEFAULT_LUMINOSITY_THRESHOLD);
	}
	
	public static BufferedImage convertToMonochrome(BufferedImage image, int threshold) {
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y < image.getHeight(); y++) {
				int RGB = image.getRGB(x, y);
				
				//Weighted greyscale luminosity
				int weightedLuminosity =
						(int) (RED_WEIGHTING * ((RGB >> 16) & 0xFF) + 
								GREEN_WEIGHTING * ((RGB >> 8) & 0xFF) + 
								BLUE_WEIGHTING * (RGB & 0xFF));
				
				image.setRGB(x, y, weightedLuminosity < threshold ? BLACK : WHITE);
			}
		}
		
		return image;
	}
	
	
}
