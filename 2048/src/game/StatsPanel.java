package game;

import javax.swing.JButton;
import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;

public class StatsPanel extends JPanel{
	JButton goBack;
	JLabel highScore;
	JLabel moveCount;
	GamePanel gp;
	
	public StatsPanel(GamePanel gp)
	{
		this.gp = gp;
		goBack = new JButton("Go Back");
		this.add(goBack);
		
		highScore = new JLabel("High Score:"+gp.highScore);
		this.add(highScore);
		
		moveCount = new JLabel("Total Number of Moves:"+gp.moveCounter);
		this.add(moveCount);
		
		setSize(500,643);
	}
	
	public void updateHighScore()
	{
		highScore.setText("High Score:"+gp.highScore);
		revalidate();
	}
	
	public void updateMoveCount()
	{
		moveCount.setText("Total Number of Moves:"+gp.moveCounter);
		revalidate();
	}
}
