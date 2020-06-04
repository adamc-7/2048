package game;

import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;



public class Buttons extends JFrame implements ActionListener{
	BasicArrowButton up;
	BasicArrowButton down;
	BasicArrowButton right;
	BasicArrowButton left;
	TestGrid t;

	public Buttons(TestGrid newT)
	{
		t = newT;
		Container buttonContents = getContentPane();
		buttonContents.setLayout(new GridLayout(2,3));
		JButton [][] directions = new JButton [2][3];	
		JButton empty1 = new JButton();
		JButton empty2 = new JButton();
		empty1.setBorder(BorderFactory.createMatteBorder(0,0,0,0,new Color(187,173,160)));
		empty2.setBorder(BorderFactory.createMatteBorder(0,0,0,0,new Color(187,173,160)));
		empty1.setBackground(new Color(119,110,101));
		empty2.setBackground(new Color(119,110,101));
		
		
		up=new BasicArrowButton(BasicArrowButton.NORTH);
		up.addActionListener(this);
		up.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),3));
		up.setBackground(new Color(238,228,218));
		up.setFont(new Font("Helvetica Neue", Font.BOLD, 10));
		up.setForeground(Color.BLACK);
		
		down=new BasicArrowButton(BasicArrowButton.SOUTH);
		down.addActionListener(this);
		down.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),3));
		down.setBackground(new Color(238,228,218));
		down.setFont(new Font("Helvetica Neue", Font.BOLD, 10));
		down.setForeground(Color.BLACK);
		
		right=new BasicArrowButton(BasicArrowButton.EAST);
		right.addActionListener(this);
		right.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),3));
		right.setBackground(new Color(238,228,218));
		right.setFont(new Font("Helvetica Neue", Font.BOLD, 10));
		right.setForeground(Color.BLACK);
		
		left=new BasicArrowButton(BasicArrowButton.WEST);
		left.addActionListener(this);
		left.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),3));
		left.setBackground(new Color(238,228,218));
		left.setFont(new Font("Helvetica Neue", Font.BOLD, 10));
		left.setForeground(Color.BLACK);
		
		directions[0][0] = empty1;
		directions[0][1] = up;
		directions[0][2] = empty2;
		directions[1][0] = left;
		directions[1][1] = down;
		directions[1][2] = right;
		
		for(int i=0;i<2;i++)
			for(int j=0;j<3;j++)
			{
				buttonContents.add(directions[i][j]);
			}
		
		setSize(160,120);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==up||e.getSource()==down||e.getSource()==left||e.getSource()==right)
		{
		t.updateGame();
		Tile [][] temp = t.g.copyBoard(t.g.getBoard());
		
		
	    if (e.getSource()==up)
	        t.g.up();
	    if (e.getSource()==down)
            t.g.down();
	    if (e.getSource()==left)
            t.g.left();
	    if (e.getSource()==right)
            t.g.right();
	    if(t.g.checkDifferent(temp))
	    	t.g.choosePositionAndPlace();
		t.updateGame();
		}
	}
	
}
