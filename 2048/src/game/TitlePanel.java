package game;

import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TitlePanel extends JPanel{
	JLabel title;
	JButton play;
	Timer t;
	public TitlePanel()
	{
		
		title = new JLabel("2048");
		title.setFont(new Font("Helvetica Neue", Font.BOLD, 100));
		
		play = new JButton("Play");
		play.setFont(new Font("Helvetica Neue", Font.BOLD, 50));
		
		
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
		this.add(Box.createRigidArea(new Dimension(0,200)));
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setSize(500,643);
	}
}
