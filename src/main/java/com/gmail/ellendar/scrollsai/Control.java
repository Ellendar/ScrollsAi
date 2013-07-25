package com.gmail.ellendar.scrollsai;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmail.ellendar.scrollsai.sacrifice.Sacrifice;

public class Control {
	
	public static final Robot robot;
	
	static {
		try {
			robot = new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1]);
		} catch (AWTException e) {
			throw new RuntimeException();
		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Control.class);
	
	public static void doSacrifice(GameState state, Sacrifice sacrifice) {
		logger.info("Sacrificing scroll: " + sacrifice.getScroll() + "(" + sacrifice.getType() + ")");
	}
}
