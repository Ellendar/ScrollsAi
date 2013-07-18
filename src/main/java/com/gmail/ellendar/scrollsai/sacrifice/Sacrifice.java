package com.gmail.ellendar.scrollsai.sacrifice;

public class Sacrifice {
	enum Type {
		RAMP, DRAW, SKIP;
	}
	
	private Type type;
	private int scroll;
	
	public Sacrifice(Type type, int scroll) {
		this.scroll = scroll;
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public int getScroll() {
		return scroll;
	}

}

	