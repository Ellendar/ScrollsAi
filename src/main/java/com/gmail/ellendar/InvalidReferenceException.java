package com.gmail.ellendar;

public class InvalidReferenceException extends RuntimeException {
	public InvalidReferenceException() {
		super();
	}
	
	public InvalidReferenceException(String msg) {
		super(msg);
	}
	
	public InvalidReferenceException(Throwable cause) {
		super(cause);
	}
	
	public InvalidReferenceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
