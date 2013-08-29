package com.gmail.ellendar.scrollsai.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.gmail.ellendar.scrollsai.ScrollsAI;
import com.gmail.ellendar.scrollsai.ui.console.GuiConsole;

public class ScrollsAiGui extends JFrame {
	
	private static final String TITLE = "Scrolls AI";
	private static final String SUSPENDED_STATUS = "SUSPENDED";
	
	private ScrollsAiGui() {
		setTitle(TITLE);
		
		GuiConsole console = new GuiConsole();
		getContentPane().add(console);
		setSize(300, 200);
		setVisible(true);
	
		WindowAdapter adapter = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
			public void windowGainedFocus(WindowEvent e) {
				setTitle(String.format("%s [%s]", TITLE, SUSPENDED_STATUS));
				PausePoint.pause();
			}
			public void windowLostFocus(WindowEvent e) {
				setTitle(TITLE);
				PausePoint.resume();
			}
		};
		
		addWindowListener(adapter);
		addWindowFocusListener(adapter);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ScrollsAiGui frame = new ScrollsAiGui();
			}
		});
		
		Thread gameThread = new Thread(new Runnable() {
			public void run() {
				System.out.println("Starting Scrolls AI on separate thread...");
				ScrollsAI.main(null);
			}
		});
		gameThread.start();
	}

}
