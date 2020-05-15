package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI Test");
		frame.pack();
		frame.setVisible(true);
	}
	
	public void dispGame()
	{
		
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
}
