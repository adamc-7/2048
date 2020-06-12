package game;

import java.awt.*;     
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

//main gui class
public class GameFrame extends JFrame{
	private Container contents;
	JPanel mainPanel = new JPanel();
	CardLayout c = new CardLayout();

	
	public GameFrame()
	{
		
		mainPanel.setLayout(c);
		
		GamePanel gp = new GamePanel();
		gp.g.setUpBoard();
		gp.updateGame();
		
		SettingsPanel sp = new SettingsPanel();
		
		HowToPlayPanel htp = new HowToPlayPanel();
		
		TitlePanel tp = new TitlePanel();
		
		StatsPanel stp = new StatsPanel(gp);
		
		mainPanel.add(gp,"GameBoard");
		mainPanel.add(sp,"Settings");
		mainPanel.add(htp,"HowToPlay");
		mainPanel.add(tp,"Title");
		mainPanel.add(stp,"Stats");
		
		c.show(mainPanel,"Title");
		pack();
		
		tp.play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				c.show(mainPanel,"GameBoard");
				gp.requestFocus();
			}
		});
		
		tp.play.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 10;
				    int fontSize = 50;
				    public void run() 
				    {
				    	if(borderSize == 15)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize++;
				    		fontSize+=2;
				    	}
				    	
				    	tp.play.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);

		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 14;
				    int fontSize = 60;
				    public void run() 
				    {
				    	if(borderSize == 10)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize--;
				    		fontSize-=2;
				    	}				    	
				    	tp.play.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);
		    }
		});
		
		gp.reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				gp.g.setUpBoard();
				gp.updateGame();
			}
		});
		
		gp.reset.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        gp.reset.setBackground(new Color(237,194,46));
		        gp.reset.setIcon(new ImageIcon("Images/restartlight.png"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        gp.reset.setBackground(new Color(238,228,218));
		        gp.reset.setIcon(new ImageIcon("Images/restartdark.png"));
		    }
		});
		
		gp.how2play.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        gp.how2play.setBackground(new Color(237,194,46));
		        gp.how2play.setIcon(new ImageIcon("Images/settingslight.png"));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        gp.how2play.setBackground(new Color(238,228,218));
		        gp.how2play.setForeground(Color.BLACK);
		        gp.how2play.setIcon(new ImageIcon("Images/settingsdark.png"));
		    }
		});
		
		gp.how2play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				c.show(mainPanel,"Settings");
			}
		});
		
		sp.goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				c.show(mainPanel,"GameBoard");
				gp.requestFocus();
			}
		});
		
		sp.howToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				c.show(mainPanel,"HowToPlay");
				gp.requestFocus();
			}
		});
		
		sp.addButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Buttons b1 = new Buttons(gp);
			}
		});
		
		sp.stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				stp.updateHighScore();
				stp.updateMoveCount();
				c.show(mainPanel,"Stats");
				gp.requestFocus();
			}
		});
		
		sp.howToPlay.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 10;
				    int fontSize = 40;
				    public void run() 
				    {
				    	if(borderSize == 15)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize++;
				    		fontSize+=2;
				    	}
				    	sp.howToPlay.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);

		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 14;
				    int fontSize = 48;
				    public void run() 
				    {
				    	if(borderSize == 10)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize--;
				    		fontSize-=2;
				    	}
				    	sp.howToPlay.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);
		    }
		});
		
		sp.addButtons.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 10;
				    int fontSize = 40;
				    public void run() 
				    {
				    	if(borderSize == 15)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize++;
				    		fontSize+=2;
				    	}
				    	sp.addButtons.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);

		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 14;
				    int fontSize = 48;
				    public void run() 
				    {
				    	if(borderSize == 10)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize--;
				    		fontSize-=2;
				    	}
				    	sp.addButtons.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);
		    }
		});
		
		sp.stats.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 10;
				    int fontSize = 40;
				    public void run() 
				    {
				    	if(borderSize == 15)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize++;
				    		fontSize+=2;
				    	}
				    	
				    	sp.stats.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);

		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 14;
				    int fontSize = 48;
				    public void run() 
				    {
				    	if(borderSize == 10)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize--;
				    		fontSize-=2;
				    	}
				    	sp.stats.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);
		    }
		});
		
		sp.goBack.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 10;
				    int fontSize = 40;
				    public void run() 
				    {
				    	if(borderSize == 15)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize++;
				    		fontSize+=2;
				    	}
				    	
				    	sp.goBack.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);

		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 14;
				    int fontSize = 48;
				    public void run() 
				    {
				    	if(borderSize == 10)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize--;
				    		fontSize-=2;
				    	}				    	
				    	sp.goBack.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);
		    }
		});
		
		
		
		stp.goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				c.show(mainPanel,"Settings");
				gp.requestFocus();
			}
		});
		
		stp.goBack.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 10;
				    int fontSize = 40;
				    public void run() 
				    {
				    	if(borderSize == 15)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize++;
				    		fontSize+=2;
				    	}
				    	
				    	stp.goBack.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);

		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 14;
				    int fontSize = 48;
				    public void run() 
				    {
				    	if(borderSize == 10)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize--;
				    		fontSize-=2;
				    	}				    	
				    	stp.goBack.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);
		    }
		});
		
		htp.goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				c.show(mainPanel,"Settings");
				gp.requestFocus();
			}
		});
		
		htp.goBack.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 10;
				    int fontSize = 40;
				    public void run() 
				    {
				    	if(borderSize == 15)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize++;
				    		fontSize+=2;
				    	}
				    	
				    	htp.goBack.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);

		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Timer t = new Timer();
				class Helper extends TimerTask 
				{ 
				    int borderSize = 14;
				    int fontSize = 48;
				    public void run() 
				    {
				    	if(borderSize == 10)
				    	{
				    		t.cancel();
				    	}
				    	else
				    	{
				    		borderSize--;
				    		fontSize-=2;
				    	}				    	
				    	htp.goBack.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
				    	
				    } 
				}
				t.schedule(new Helper(),1,15);
		    }
		});
	
		
		
//		gp.how2play.addActionListener(new GameActionListener(gp));
//		gp.placeHolder1.addActionListener(new GameActionListener(gp));
//		gp.placeHolder2.addActionListener(new GameActionListener(gp));
//		gp.addButtons.addActionListener(new GameActionListener(gp));
		this.add(mainPanel);
		this.setVisible(true);
		this.setSize(500,643);
		this.setLocationRelativeTo(null);
//		GameActionListener mainListener = new GameActionListener(gp);
	}
	private class GameActionListener implements ActionListener
	{
		JPanel j;
		public GameActionListener(JPanel j)
		{
			this.j = j;
		}
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(j.getClass().getName().equals("GamePanel"))
			{
				GamePanel gp =(GamePanel)(j);
				JButton goBack = new JButton("Go Back");
				if(e.getSource()==gp.up||e.getSource()==gp.down||e.getSource()==gp.left||e.getSource()==gp.right||e.getSource()==gp.reset)
				{
					gp.updateGame();
					Tile [][] temp = gp.g.copyBoard(gp.g.getBoard());
					
					
				    if (e.getSource()==gp.up)
				    	gp.g.up();
				    if (e.getSource()==gp.down)
				    	gp.g.down();
				    if (e.getSource()==gp.left)
				    	gp.g.left(gp);
				    if (e.getSource()==gp.right)
				    	gp.g.right();
				    if(gp.g.checkDifferent(temp))
				    	gp.g.choosePositionAndPlace();
				    
				    if(e.getSource()==gp.reset)
				    	gp.g.setUpBoard();
					
				    gp.updateGame();
				}
//				else if(e.getSource()==gp.how2play)
//				{
//					this.removeAll();
//					this.add(gp.tutorial);
//					this.revalidate();
//					this.repaint();
//					this.add(goBack);
//					goBack.addActionListener(new ActionListener()
//							{
//								public void actionPerformed(ActionEvent event)
//								{
//								}
//							});
//				}
	//			else if(e.getSource() == gp.addButtons)
	//			{
	//				Buttons b1 = new Buttons(gp);
	//			}
				requestFocusInWindow();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameFrame g = new GameFrame();
	}

}
