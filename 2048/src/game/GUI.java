package game;

import java.awt.BorderLayout;  
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener, KeyListener{

	Game g;
	JFrame frame;
	JPanel panel;
	JButton up;
	JButton down;
	JButton right;
	JButton left;
	JLabel testLabel;
	
	public GUI(Game game)
	{	
		g=game;
		frame = new JFrame();
		panel=new JPanel();
		up=new JButton("Up");
		up.addActionListener(this);
		down=new JButton("Down");
		down.addActionListener(this);
		right=new JButton("Right");
		right.addActionListener(this);
		left=new JButton("Left");
		left.addActionListener(this);
		testLabel= new JLabel("Press Button");
		panel.setBorder(BorderFactory.createEmptyBorder(60,60,50,60));
		panel.setLayout(new GridLayout(0,1));					
		panel.add(up);
		panel.add(down);
		panel.add(left);
		panel.add(right);
		panel.add(testLabel);
		frame.add(panel, BorderLayout.CENTER);
		panel.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	public void dispGame()
	{
		
		frame.setTitle("GUI Test");
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==up)
			testLabel.setText("up");
		else if(e.getSource()==left)
			testLabel.setText("left");
		else if(e.getSource()==down)
			testLabel.setText("down");
		else if(e.getSource()==right)
			testLabel.setText("right");
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		switch(code)
		{
		case KeyEvent.VK_W:testLabel.setText("up");
			break;
		case KeyEvent.VK_A:testLabel.setText("left");
			break;
		case KeyEvent.VK_D:testLabel.setText("right");					
			break;
		case KeyEvent.VK_S:testLabel.setText("down");
			break;
		}
		
		frame.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
