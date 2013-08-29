package com.gmail.ellendar.scrollsai.ui;

public class PausePoint {

	private static final Object locker = new Object();
	private static volatile boolean paused = false;
	
	public static boolean pause() {
		boolean wasPaused;
		synchronized(locker) {
			wasPaused = paused;
			paused = true;
		}
		
		return wasPaused;
	}
	
	public static boolean resume() {
		boolean wasPaused;
		synchronized(locker) {
			wasPaused = paused;
			paused = false;
			locker.notifyAll();
		}
		
		return !wasPaused;
	}
	
	public static boolean isPaused() {
		boolean value;
		synchronized(locker) {
			value = paused;
		}
		return value;
	}

	public static void check() throws InterruptedException {
		synchronized(locker) {
			while(paused)
				locker.wait();
		}
	}
}
