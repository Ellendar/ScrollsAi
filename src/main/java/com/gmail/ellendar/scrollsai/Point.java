package com.gmail.ellendar.scrollsai;

public class Point {
	
	public int x,y;
	
	public Point(int x, int y) {
		this.x = x; 
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Point)) {
			return false;
		}
		Point rhs = (Point)obj;
		
		return x == rhs.x && y == rhs.y;
	}
}
