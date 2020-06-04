package game;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class TestGrid extends JFrame implements ActionListener{
	private Container contents;
	private JButton [][] squares = new JButton[4][4];
	JButton up;
	JButton down;
	JButton right;
	JButton left;
	JButton reset;
	JButton howTo;
	JButton placeHolder1;
	JButton placeHolder2;
	JFrame howToPlay;
	
	Game g = new Game();
	
	GUI gui= new GUI(g);
	
	
	
	
	public TestGrid()
	{
		super("Test Grid");
		
		contents = getContentPane();
		contents.setLayout(new GridLayout(6,4));
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				squares [i][j] = new JButton();
				contents.add(squares[i][j]);
			}
		setSize(500,750);
		up=new JButton("Up");
		up.addActionListener(this);
		down=new JButton("Down");
		down.addActionListener(this);
		right=new JButton("Right");
		right.addActionListener(this);
		left=new JButton("Left");
		left.addActionListener(this);
		reset=new JButton("Restart Game");
		reset.addActionListener(this);
		howTo=new JButton("How To Play");
		howTo.addActionListener(this);
		placeHolder1=new JButton("Score:");
		placeHolder1.addActionListener(this);
		placeHolder2=new JButton("0");
		placeHolder2.addActionListener(this);
		contents.add(up);
		contents.add(down);
		contents.add(left);
		contents.add(right);
		contents.add(reset);
		contents.add(placeHolder1);
		contents.add(placeHolder2);
		contents.add(howTo);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		setVisible(true);
		this.addKeyListener(new MyKeyListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JFrame howToPlay = new JFrame();
		howToPlay.add(new JLabel("Use the Arrow Keys, the WASD keys, or the buttons on the screen to move the tiles. Try to reach the 2048 tile!"));
		howToPlay.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	class MyKeyListener extends KeyAdapter implements KeyListener
	{
		public void keyPressed(KeyEvent e)
	    {
			updateGame();
			Tile [][] temp = g.copyBoard(g.getBoard());
			
			
		    if (e.getKeyCode()== KeyEvent.VK_W || e.getKeyCode()== KeyEvent.VK_UP)
		            g.up();
		    if (e.getKeyCode()== KeyEvent.VK_S || e.getKeyCode()== KeyEvent.VK_DOWN)
		            g.down();
		    if (e.getKeyCode()== KeyEvent.VK_A || e.getKeyCode()== KeyEvent.VK_LEFT)
		            g.left();
		    if (e.getKeyCode()== KeyEvent.VK_D || e.getKeyCode()== KeyEvent.VK_RIGHT)
		            g.right();
		    if(g.checkDifferent(temp))
		    	g.choosePositionAndPlace();
			
			updateGame();
	        
	        
	    }
	}
	
	public void updateGame()
	{
		
		for(int i=0;i<g.getBoard().length;i++)
			for(int j=0;j<g.getBoard()[0].length;j++)
			{
				squares[i][j].setBorder(BorderFactory.createLineBorder(new Color(187,173,160),5));
				if(g.getBoard()[i][j] != null)
				{
					squares[i][j].setFont(new Font("Helvetica Neue", Font.PLAIN, 40));
					squares[i][j].setText(""+g.getBoard()[i][j].getVal());
					chooseColor(squares[i][j],i,j);
				}
				else
				{
					squares[i][j].setText("");
					squares[i][j].setBackground(new Color(119,110,101));
				}
					
			}
		placeHolder2.setText(new Integer(g.getPoints()).toString());
	}
	
	public void chooseColor(JButton btn, int i, int j)
	{
		switch(g.getBoard()[i][j].getVal())
		{
		case 2:
			btn.setForeground(Color.BLACK);
			btn.setBackground(new Color(238,228,218));
			break;
		
		case 4:
			btn.setForeground(Color.BLACK);
			btn.setBackground(new Color(237,224,200));
			break;
		case 8:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(242,177,121));
			break;
		case 16:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(245,149,99));
			break;
		case 32:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(246,124,95));
			break;
		case 64:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(246,94,59));
			break;
		case 128:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,207,114));
			break;
		case 256:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,204,97));
			break;
		case 512:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,200,80));
			break;
		case 1024:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,197,63));
			break;
		case 2048:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,194,46));
			break;
		}
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestGrid t = new TestGrid();
		t.g.setUpBoard();
		t.updateGame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==up||e.getSource()==down||e.getSource()==left||e.getSource()==right||e.getSource()==reset)
		{
		updateGame();
		Tile [][] temp = g.copyBoard(g.getBoard());
		
		
	    if (e.getSource()==up)
	            g.up();
	    if (e.getSource()==down)
            g.down();
	    if (e.getSource()==left)
            g.left();
	    if (e.getSource()==right)
            g.right();
	    if(g.checkDifferent(temp))
	    	g.choosePositionAndPlace();
	    
	    if(e.getSource()==reset)
	    	g.setUpBoard();
		
		updateGame();
		}
		else if(e.getSource()==howTo)
		{
			howToPlay.setVisible(true);
		}
		requestFocusInWindow();
	}

}
