package game;

import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TitlePanel extends JPanel{
	JLabel title;
	JLabel by;
	JButton play;
	Timer t;
	public TitlePanel()
	{
		this.setBackground(new Color(238,228,218));
		title = new JLabel("2048");
		title.setFont(new Font("Helvetica Neue", Font.BOLD, 100));
		
		by = new JLabel("<html><div style ='text-align:center;'>By Adam Cahall, Aidan Clark, </br>Chris Lenhard, and Josh Magil</div></html>",JLabel.CENTER);
		by.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		
		play = new JButton("Play");
		play.setFont(new Font("Helvetica Neue", Font.BOLD, 50));
		play.setBorder(BorderFactory.createMatteBorder(10,50,10,50,new Color(187,173,160)));
		play.setBackground(new Color(187,173,160));
		play.setForeground(Color.WHITE);
		
		
		
		t = new Timer();
		class Helper extends TimerTask 
		{ 
		    int i = 100;
		    boolean goBack = false;
		    public void run() 
		    {
		    	if(i > 105)
		    	{
		    		goBack = true;
		    		
		    	}
		    	if(goBack == true)
		    	{
		    		if(i < 95)
		    		{
		    			goBack = false;
		    		}
		    		else
		    		{
		    			i--;
		    		}
		    	}
		    	else
		    	{
		    		i++;
		    	}
		    	title.setFont(new Font("Helvetica Neue", Font.BOLD, i));
		    	title.revalidate();
		    } 
		}
		t.schedule(new Helper(),1,50);
		this.add(title);
		this.add(Box.createVerticalGlue());
		this.add(play);
		this.add(Box.createRigidArea(new Dimension(0,50)));
		this.add(by);
		this.add(Box.createRigidArea(new Dimension(0,100)));
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		by.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setSize(500,643);
	}
}
