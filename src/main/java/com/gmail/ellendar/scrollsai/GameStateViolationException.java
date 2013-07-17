package com.gmail.ellendar.scrollsai;

@SuppressWarnings("serial")
public class GameStateViolationException extends RuntimeException {
	
	public GameStateViolationException() {
		super();
	}
	
	public GameStateViolationException(String msg) {
		super(msg);
	}
	
	public GameStateViolationException(Throwable cause) {
		super(cause);
	}
	
	public GameStateViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
