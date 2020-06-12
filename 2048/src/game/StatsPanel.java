package game;

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
		this.add(Box.createRigidArea(new Dimension(0,150)));
		this.gp = gp;
		this.setBackground(new Color(238,228,218));
		highScore = new JLabel("<html><div style ='text-align:center;'>HIGH SCORE: "+gp.highScore+"<br/></div></html>");
		highScore.setBorder(BorderFactory.createMatteBorder(50,0,0,0,new Color(238,228,218)));
		highScore.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		highScore.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(highScore);
		
		moveCount = new JLabel("<html><div style ='text-align:center;'>TOTAL MOVES: "+gp.moveCounter+"<br/><br/><br/><br/><br/></div></html>");
		moveCount.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		moveCount.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(moveCount);
		
		goBack = new JButton("GO BACK");
		goBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		goBack.setBorder(BorderFactory.createMatteBorder(3,60,3,60,new Color(187,173,160)));
		goBack.setBackground(new Color(187,173,160));
		goBack.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
		goBack.setForeground(Color.WHITE);
		this.add(goBack);
		
		setSize(500,643);
	}
	
	public void updateHighScore()
	{
		highScore.setText("<html><div style ='text-align:center;'>HIGH SCORE: "+gp.highScore+"<br/></div></html>");
		revalidate();
	}
	
	public void updateMoveCount()
	{
		moveCount.setText("<html><div style ='text-align:center;'>TOTAL MOVES: "+gp.moveCounter+"<br/><br/><br/><br/><br/></div></html>");
		revalidate();
	}
}
