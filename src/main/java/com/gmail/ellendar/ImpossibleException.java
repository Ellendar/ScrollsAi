package com.gmail.ellendar;

@SuppressWarnings("serial")
public class ImpossibleException extends RuntimeException {
	public ImpossibleException() {
		
	}
	
	public ImpossibleException(String msg) {
		super(msg);
	}
}
