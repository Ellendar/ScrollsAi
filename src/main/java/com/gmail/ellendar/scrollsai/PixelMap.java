package com.gmail.ellendar.scrollsai;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixelMap {
	
	private boolean[][] pixels;
	
	public PixelMap(boolean[][] pixels) {
		this.pixels = pixels;
	}
	
	public PixelMap(BufferedImage img, int density) {
		this(img, density, density);
	}

	public PixelMap(BufferedImage img, int xDensity, int yDensity) {
		if(img.getHeight() == 0 || img.getWidth() == 0) {
			throw new RuntimeException("Image must be at least 1x1");
		}
		pixels = new boolean[img.getWidth() / xDensity][img.getHeight() / yDensity];
		
		for(int x = 0; x < img.getWidth(); x+=xDensity) {
			for(int y = 0; y < img.getHeight(); y+=yDensity) {
				img = ImageUtil.convertToMonochrome(img);
				pixels[x][y] = img.getRGB(x, y) == ImageUtil.WHITE;
			}
		}
	}
	
	public PixelMap or(PixelMap rhs) {
		
		int width = pixels.length;
		int height = pixels[0].length;
		int rhsWidth = rhs.pixels.length;
		int rhsHeight = rhs.pixels[0].length;
		int maxWidth = Math.max(pixels.length, rhs.pixels.length);
		int maxHeight = Math.max(pixels[0].length, rhs.pixels[0].length);
		
		boolean[][] result = new boolean[maxWidth][maxHeight];
		
		for(int x = 0; x < maxWidth; ++x) {
			for(int y = 0; y < maxHeight; ++y) {
				if(x < width && y < height) {
					if(x < rhsWidth && y < rhsHeight) {
						result[x][y] = pixels[x][y] || rhs.pixels[x][y];
					}
					else {
						result[x][y] = pixels[x][y];
					}
				}
				else if(x < rhsWidth && y < rhsHeight) {
					result[x][y] = rhs.pixels[x][y];
				}
				else {
					result[x][y] = false;
				}
			}
		}
		
		return new PixelMap(result);
	}
	
	public PixelMap and(PixelMap rhs) {
		
		int width = pixels.length;
		int height = pixels[0].length;
		int rhsWidth = rhs.pixels.length;
		int rhsHeight = rhs.pixels[0].length;
		int maxWidth = Math.max(pixels.length, rhs.pixels.length);
		int maxHeight = Math.max(pixels[0].length, rhs.pixels[0].length);
		
		boolean[][] result = new boolean[maxWidth][maxHeight];
		
		for(int x = 0; x < maxWidth; ++x) {
			for(int y = 0; y < maxHeight; ++y) {
				if(x < width && y < height) {
					if(x < rhsWidth && y < rhsHeight) {
						result[x][y] = pixels[x][y] && rhs.pixels[x][y];
					}
					else {
						result[x][y] = false;
					}
				}
				else {
					result[x][y] = false;
				}
			}
		}
		
		return new PixelMap(result);
	}
	
	public double getDensity() {
		double count = 0;
		for(int x = 0; x < pixels.length; x++) {
			for(int y = 0; y < pixels[0].length; y++) {
				if(pixels[x][y]) {
					++count;
				}
			}
		}
		
		return count / (pixels.length * pixels[0].length);
	}
	
	public double getRelativeDensity(PixelMap targetDensity) {
		double count = 0;
		for(int x = 0; x < pixels.length; x++) {
			for(int y = 0; y < pixels[0].length; y++) {
				if(pixels[x][y]) {
					++count;
				}
			}
		}
		
		double density = count / (pixels.length * pixels[0].length);
		
		return density / targetDensity.getDensity();
	}
	
	public BufferedImage asImage() {
		BufferedImage image = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_ARGB);
		
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y < image.getHeight(); y++) {
				image.setRGB(x, y, pixels[x][y] ? Color.BLACK.getRGB() : Color.WHITE.getRed());
			}
		}
		
		return image;
	}
}
