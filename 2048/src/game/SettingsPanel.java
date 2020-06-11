package game;

import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel{
	JButton howToPlay;
	JButton goBack;
	JButton addButtons;
	JButton stats;
	
	public SettingsPanel()
	{
		goBack = new JButton("Go Back");
		this.add(goBack);
		
		howToPlay = new JButton("How to Play");
		this.add(howToPlay);
		
		addButtons = new JButton("Add Buttons");
		this.add(addButtons);
		
		stats = new JButton("Stats");
		this.add(stats);
		
		setSize(500,643);
	}
}
