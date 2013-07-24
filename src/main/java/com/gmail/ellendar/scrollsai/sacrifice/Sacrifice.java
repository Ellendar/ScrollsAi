package com.gmail.ellendar.scrollsai.sacrifice;

import com.gmail.ellendar.scrollsai.scroll.Scroll;

public class Sacrifice {
	public enum Type {
		RAMP, DRAW, SKIP;
	}
	
	private Type type;
	private Scroll scroll;
	
	public Sacrifice(Type type, Scroll scroll) {
		this.scroll = scroll;
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public Scroll getScroll() {
		return scroll;
	}
	
	@Override
	public String toString() {
		return "Type:" + type + " Scroll:" + scroll;
	}

}

	