package com.gmail.ellendar.scrollsai;

import java.awt.image.BufferedImage;

public class PixelMap {
	
	private boolean[][] pixels;
	
	private PixelMap(boolean[] pixels) {
		this.pixels = this.pixels;
	}
	
	public PixelMap(BufferedImage img, int density) {
		this(img, density, density);
	}

	public PixelMap(BufferedImage img, int xDensity, int yDensity) {
		if(img.getHeight() == 0 || img.getWidth() == 0) {
			throw new Run
		}
		pixels = new boolean[img.getWidth() / xDensity][img.getHeight() / yDensity];
		
		for(int x = 0; x < img.getWidth(); x+=xDensity) {
			for(int y = 0; y < img.getHeight(); x+=yDensity) {
				img = ImageUtil.convertToMonochrome(img);
				pixels[x][y] = img.getRGB(x, y) == ImageUtil.WHITE;
			}
		}
	}
	
	public PixelMap or(PixelMap rhs) {
		boolean[][] result = new boolean[Math.max(pixels.length, rhs.pixels.length), pix)][];
	}
}
