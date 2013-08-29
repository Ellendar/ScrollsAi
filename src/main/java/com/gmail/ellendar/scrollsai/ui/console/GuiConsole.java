package com.gmail.ellendar.scrollsai.ui.console;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiConsole extends JPanel {

	private JTextArea outputWidget;
	private JTextField inputWidget;
	
	public GuiConsole() {
		setSize(300, 200);
		setFocusable(true);
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		outputWidget = new JTextArea();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.weightx = 1f;
		c.weighty = 1f;
		c.fill = GridBagConstraints.BOTH;		
		add(outputWidget, c);
		
		c = new GridBagConstraints();
		inputWidget = new JTextField();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1f;
		c.weighty = 0f;
		c.fill = GridBagConstraints.BOTH;
		add(inputWidget, c);
	}
	
}
