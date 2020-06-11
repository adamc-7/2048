package game;

import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import game.TestGrid.MyKeyListener;
import java.io.*;

public class GamePanel extends JPanel{
	JButton [][] squares = new JButton[4][4];
	JButton up;
	JButton down;
	JButton right;
	JButton left;
	JButton reset;
	JButton how2play;
	JButton placeHolder1;
	JButton placeHolder2;
	JButton addButtons;
//	JFrame helpFrame;
	JLabel tutorial;
	int moveCounter = -1;
	int highScore = 0;
	
	String line1 ="Use the Up, Down, Left, and Right buttons to move all tiles on the ";
	String line2 ="grid the farthest they can travel in that direction. If two tiles with ";
	String line3 ="the same number collide, they combine into one new tile with a value equal ";
	String line4 ="to the sum of the tiles that collided. For an example, a 2 tile and a 2 tile ";
	String line5 ="will combine into a 4 tile. If two 4 tiles collide, they become an 8 tile and ";
	String line6 ="so on. The object of the game is to create a tile worth 2048. After each move ";
	String line7 ="made by the player, a new tile is generated in a random location. If the board ";
	String line8 ="has no empty spaces and the player cannot combine tiles to create a space, the game ends.";
	Game g = new Game();
	
//	GUI gui= new GUI(g);
	
	
	
	
	public GamePanel()
	{
		ImageIcon restart = new ImageIcon("Images/restartdark.png");
		this.setLayout(new GridLayout(5,4));
		reset=new JButton(restart);
//		reset.setVerticalAlignment(SwingConstants.CENTER);
		reset.setFocusable(false);
//		reset.addActionListener(this);
		reset.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),7));
		reset.setBackground(new Color(238,228,218));
		
		ImageIcon settings = new ImageIcon("Images/settingsdark.png");
		how2play=new JButton(settings);
		how2play.setFocusable(false);
//		how2play.addActionListener(this);
		how2play.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),7));
		how2play.setBackground(new Color(238,228,218));
		how2play.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
		how2play.setForeground(Color.BLACK);
		
		tutorial= new JLabel("<html>" + line1 + "<html>" + line2 + "<html>" + line3 + "<html>" + line4 + "<html>" + line5 + "<html>"+ line6 + "<html>" + line7 + "<html>" + line8);
		
		placeHolder1=new JButton("Score:");
//		placeHolder1.addActionListener(this);
		placeHolder1.setBorder(BorderFactory.createMatteBorder(7,7,7,0,new Color(187,173,160)));
		placeHolder1.setBackground(new Color(237,194,46));
		placeHolder1.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
		placeHolder1.setForeground(Color.WHITE);
		
		placeHolder2=new JButton("0");
//		placeHolder2.addActionListener(this);
		placeHolder2.setBorder(BorderFactory.createMatteBorder(7,0,7,7,new Color(187,173,160)));
		placeHolder2.setBackground(new Color(237,194,46));
		placeHolder2.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
		placeHolder2.setForeground(Color.WHITE);
		
		addButtons=new JButton("Switch Mode");
//		addButtons.addActionListener(this);
		addButtons.setBorder(BorderFactory.createMatteBorder(7,7,7,7,new Color(187,173,160)));
		addButtons.setBackground(new Color(238,228,218));
		addButtons.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		addButtons.setForeground(Color.BLACK);
		addButtons.setHorizontalTextPosition(SwingConstants.CENTER);
		
		this.add(reset);
		this.add(placeHolder1);
		this.add(placeHolder2);
		this.add(how2play);
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				squares [i][j] = new JButton();
				this.add(squares[i][j]);
			}
		setSize(500,643);
//		helpFrame = new JFrame();
//		helpFrame.add(tutorial,FlowLayout.LEFT);
//		helpFrame.setTitle("How to Play");
//		helpFrame.setLocationRelativeTo(null);
//		helpFrame.setSize(400, 200);
		
//		setResizable(false);
//		setLocationRelativeTo(null);
		setFocusable(true);
		setVisible(true);
		this.addKeyListener(new MyKeyListener(this));
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	class MyKeyListener extends KeyAdapter implements KeyListener
	{
		GamePanel gp;
		public MyKeyListener(GamePanel gp)
		{
			this.gp = gp;
		}
		public void keyPressed(KeyEvent e)
	    {
			Tile [][] temp = g.copyBoard(g.getBoard());
			
			
		    if (e.getKeyCode()== KeyEvent.VK_W || e.getKeyCode()== KeyEvent.VK_UP)
		    {
		    	
		        g.up(gp);
		    }
		    if (e.getKeyCode()== KeyEvent.VK_S || e.getKeyCode()== KeyEvent.VK_DOWN)
		    {
	    		
	            g.down(gp);
		    }
		    if (e.getKeyCode()== KeyEvent.VK_A || e.getKeyCode()== KeyEvent.VK_LEFT)
		    {
	    		
	            g.left(gp);
		    }
		    if (e.getKeyCode()== KeyEvent.VK_D || e.getKeyCode()== KeyEvent.VK_RIGHT)
		    {
	    		
	            g.right(gp);
		    }
		    if(g.checkDifferent(temp))
		    {
		    	moveCounter++;
		    	g.choosePositionAndPlace(gp);
		    }
			
			updateGame();
	        
	        
	    }
	}
	
	public boolean lostGame()
	{
		if(g.moveAvailable()!=true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void updateGame()
	{
		
		if(lostGame())
		{
			for(int i=0;i<g.getBoard().length;i++)
			{
				for(int j=0;j<g.getBoard()[0].length;j++)
				{
					squares[i][j].setForeground(Color.WHITE);
					squares[i][j].setBackground(Color.BLACK);

				}

			}
			squares[0][0].setText(""); squares[0][1].setText(""); squares[0][2].setText(""); squares[0][3].setText("");
			squares[1][0].setText("G"); squares[1][1].setText("A"); squares[1][2].setText("M"); squares[1][3].setText("E");
			squares[2][0].setText("O"); squares[2][1].setText("V"); squares[2][2].setText("E"); squares[2][3].setText("R");
			squares[3][0].setText(""); squares[3][1].setText(""); squares[3][2].setText(""); squares[3][3].setText("");
		}
		else
		{
			for(int i=0;i<g.getBoard().length;i++)
				for(int j=0;j<g.getBoard()[0].length;j++)
				{
					squares[i][j].setBorder(BorderFactory.createLineBorder(new Color(187,173,160),7));
					if(g.getBoard()[i][j] != null)
					{
						squares[i][j].setFont(new Font("Helvetica Neue", Font.BOLD, 55));
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
		if(g.getPoints() > highScore)
		{
			highScore = g.getPoints();
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
			btn.setFont(new Font("Helvetica Neue", Font.BOLD, 45));
			break;
		case 256:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,204,97));
			btn.setFont(new Font("Helvetica Neue", Font.BOLD, 45));
			break;
		case 512:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,200,80));
			btn.setFont(new Font("Helvetica Neue", Font.BOLD, 45));
			break;
		case 1024:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,197,63));
			btn.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
			break;
		case 2048:
			btn.setForeground(Color.WHITE);
			btn.setBackground(new Color(237,194,46));
			btn.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
			break;
		}
	}
	
	
	

	

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		JButton goBack = new JButton("Go Back");
//		if(e.getSource()==up||e.getSource()==down||e.getSource()==left||e.getSource()==right||e.getSource()==reset)
//		{
//		updateGame();
//		Tile [][] temp = g.copyBoard(g.getBoard());
//		
//		
//	    if (e.getSource()==up)
//	            g.up();
//	    if (e.getSource()==down)
//            g.down();
//	    if (e.getSource()==left)
//            g.left();
//	    if (e.getSource()==right)
//            g.right();
//	    if(g.checkDifferent(temp))
//	    	g.choosePositionAndPlace();
//	    
//	    if(e.getSource()==reset)
//	    	g.setUpBoard();
//		
//		updateGame();
//		}
//		else if(e.getSource()==how2play)
//		{
//			this.removeAll();
//			this.add(tutorial);
//			this.revalidate();
//			this.repaint();
//			this.add(goBack);
//			goBack.addActionListener(new ActionListener()
//					{
//						public void actionPerformed(ActionEvent event)
//						{
//							setVisible(false);
//							dispose();
//							this.removeAll();
//							TestGrid t = new TestGrid();
//							t.g.setUpBoard();
//							t.updateGame();
//							contents.revalidate();
//							contents.repaint();
//						}
//					});
//		}
//		else if(e.getSource() == addButtons)
//		{
//			Buttons b1 = new Buttons(this);
//		}
//		requestFocusInWindow();
//		
//	}

}

