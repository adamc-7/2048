package game;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class TestGrid extends JFrame{
	private Container contents;
	private JButton [][] squares = new JButton[4][4];
	
	Game g = new Game();
	
	GUI gui= new GUI(g);
	
	
	
	
	public TestGrid()
	{
		super("Test Grid");
		
		contents = getContentPane();
		contents.setLayout(new GridLayout(4,4));
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				squares [i][j] = new JButton();
				contents.add(squares[i][j]);
			}
		setSize(500,500);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		setVisible(true);
		this.addKeyListener(new MyKeyListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class MyKeyListener extends KeyAdapter implements KeyListener
	{
		public void keyPressed(KeyEvent e)
	    {
			updateGame();
			Tile [][] temp = g.copyBoard(g.getBoard());
			
			
		    if (e.getKeyCode()== KeyEvent.VK_W)
		            g.up();
		    if (e.getKeyCode()== KeyEvent.VK_S)
		            g.down();
		    if (e.getKeyCode()== KeyEvent.VK_A)
		            g.left();
		    if (e.getKeyCode()== KeyEvent.VK_D)
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

}
