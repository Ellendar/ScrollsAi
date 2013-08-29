package com.gmail.ellendar.scrollsai;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int WHITE = Color.WHITE.getRGB();
	
	private static final double RED_WEIGHTING = .30;
	private static final double GREEN_WEIGHTING = .59;
	private static final double BLUE_WEIGHTING = .11;

	private static final int DEFAULT_LUMINOSITY_THRESHOLD = 50;
	
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
	
	public static void saveImage(BufferedImage image, File file) {
	    try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveImage(BufferedImage image, String file) {
	    try {
			ImageIO.write(image, "png", new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage readImageResource(String url) {
		try {
			InputStream in = ImageUtil.class.getClassLoader().getResourceAsStream(url);
			if(in == null)
				in = new FileInputStream(url);
			return ImageIO.read(in);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String asRGB(int rgb) {
		return ((rgb >> 16) & 0xFF) + " / " + ((rgb >> 8) & 0xFF) + " / " + (rgb & 0xFF);
	}
}
