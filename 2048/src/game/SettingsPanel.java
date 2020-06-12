package game;

import java.awt.*;     
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsPanel extends JPanel{
	JLabel settings;
	JButton howToPlay;
	JButton goBack;
	JButton addButtons;
	JButton stats;
	
	public SettingsPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(238,228,218));
		this.add(Box.createVerticalStrut(50));
		settings = new JLabel("SETTINGS");
		settings.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		settings.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(settings);
		this.add(Box.createVerticalStrut(30));
		
		howToPlay = new JButton("HOW TO PLAY");
		howToPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		howToPlay.setBorder(BorderFactory.createMatteBorder(3,10,3,10,new Color(187,173,160)));
		howToPlay.setBackground(new Color(187,173,160));
		howToPlay.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		howToPlay.setForeground(Color.WHITE);
		
		this.add(howToPlay);
		this.add(Box.createVerticalStrut(40));
		
		addButtons = new JButton("ADD BUTTONS");
		addButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
		addButtons.setBorder(BorderFactory.createMatteBorder(3,7,3,7,new Color(187,173,160)));
		addButtons.setBackground(new Color(187,173,160));
		addButtons.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		addButtons.setForeground(Color.WHITE);
		this.add(addButtons);
		this.add(Box.createVerticalStrut(40));
		
		stats = new JButton("STATISTICS");
		stats.setAlignmentX(Component.CENTER_ALIGNMENT);
		stats.setBorder(BorderFactory.createMatteBorder(3,35,3,35,new Color(187,173,160)));
		stats.setBackground(new Color(187,173,160));
		stats.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		stats.setForeground(Color.WHITE);
		this.add(stats);
		this.add(Box.createVerticalStrut(40));
		
		goBack = new JButton("GO BACK");
		goBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		goBack.setBorder(BorderFactory.createMatteBorder(3,60,3,60,new Color(187,173,160)));
		goBack.setBackground(new Color(187,173,160));
		goBack.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		goBack.setForeground(Color.WHITE);
		this.add(goBack);
		this.add(Box.createVerticalStrut(40));
		
		setSize(500,643);
	}
}
