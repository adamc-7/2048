package game;

import java.util.Scanner;  
import java.util.concurrent.ThreadLocalRandom;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
public class Game {
	Scanner in = new Scanner(System.in);
	
	//stores values for each tile in game
	Tile[][] board = new Tile[4][4];
	
	//current number of points earned
	static int points;
	
	public void setUpBoard()
	{
		//Places two tiles on board to start the game
		for(int r=0;r<4; r++)
		{
			for(int c=0; c<4;  c++)
			{
				board[r][c]=null;
			}
		}
		choosePositionAndPlace();
		choosePositionAndPlace();
		resetPoints();
	}
	
	public static void resetPoints()
	{
		points=0;
	}
	
	public void printBoard()
	{
		//Prints the board in a 4 x 4 style
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j] == null)
				{
					System.out.print("0     ");
				}
				else
				{
					System.out.print(board[i][j].getVal()+ " ");
					int length = (int)(Math.floor(Math.log10(board[i][j].getVal()))) + 1; //finds character length of number to be printed
					for(int x=0;x<5-length;x++) //properly formats spacing between numbers
					{
						System.out.print(" ");
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void choosePositionAndPlace()
	{
		int openCounter = 0;
		
		for(int i=0;i<board.length;i++) //counts number of open positions on board
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j] == null)
				{
					openCounter++;
				}
			}
		}
		if(openCounter == 0) //returns if no positions for a new tile are available
		{
			return;
		}
		int randomPos = ThreadLocalRandom.current().nextInt(0,openCounter); //sets randompos equal to an integer from 0 to openCounter-1 that represents one of the open positions found in the first traversal
		openCounter = 0; //resets openCounter to use a second time
		for(int i=0;i<board.length;i++) //traverses through board a second time until it reaches the position of the new tile. Once it reaches this point, it creates a new Tile and puts it in this position and then returns.
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j] == null)
				{
					if(openCounter == randomPos)
					{
						board[i][j] = new Tile();
						return;
					}
					openCounter++;
				}
			}
		}
	}
	
	public void choosePositionAndPlace(GamePanel g)
	{
		int openCounter = 0;
		
		for(int i=0;i<board.length;i++) //counts number of open positions on board
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j] == null)
				{
					openCounter++;
				}
			}
		}
		if(openCounter == 0) //returns if no positions for a new tile are available
		{
			return;
		}
		int randomPos = ThreadLocalRandom.current().nextInt(0,openCounter); //sets randompos equal to an integer from 0 to openCounter-1 that represents one of the open positions found in the first traversal
		openCounter = 0; //resets openCounter to use a second time
		for(int i=0;i<board.length;i++) //traverses through board a second time until it reaches the position of the new tile. Once it reaches this point, it creates a new Tile and puts it in this position and then returns.
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j] == null)
				{
					if(openCounter == randomPos)
					{
						board[i][j] = new Tile();
						JButton button = g.squares[i][j];
						button.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),61));
						button.setFont(new Font("Helvetica Neue", Font.BOLD, 1));
						Timer t = new Timer();
						class Helper extends TimerTask 
						{ 
						    int borderSize = 62;
						    int fontSize = 1;
						    public void run() 
						    {
						    	if(borderSize < 8)
						    	{
						    		t.cancel();
						    	}
						    	else
						    	{
						    		borderSize--;
						    		fontSize++;
						    	}
						    	
						    	button.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),borderSize));
						    	button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
						    	g.revalidate();
						    } 
						}
						t.schedule(new Helper(),1,5);
						return;
					}
					openCounter++;
				}
			}
		}
	}
	public void playGame()
	{
		System.out.println("Each round, enter 'a','s','w', or 'd'. (a=left,d=right,w=up,s=down)");
		setUpBoard();
		printBoard();
		while(moveAvailable())
		{
			System.out.println("Score: "+points);
			System.out.println("Enter Command:");
			char command = in.next().charAt(0);
			Tile [][] temp = copyBoard(board);
			playARound(command);
			if(checkDifferent(temp))
			{
				choosePositionAndPlace();
			}
			printBoard();
			
			
		}
		System.out.println("Game Over! You scored " + getPoints() + "points.");
		System.out.println("Would you like to play again? Type 'y' for yes, or 'n' for no.");
		char play= in.next().charAt(0);
		
		if(play=='y')
		{
			playGame();
		}
		else 
		{
			System.out.println("Thanks for playing!");
		}
	}
	
	public void playARound(char c)	//takes command in the form of a char and calls helper methods to execute the requested move
	{
		if(c == 'a')
		{
			left();
		}
		
		
		if(c == 'd')
		{
			right();
		}
		
		
		if(c == 'w')
		{
			up();
		}
		
		
		if(c == 's')
		{
			down();
		}
	}
	
	public void left(GamePanel g)
	{
		//This method and the other directional methods execute the command entered by first moving all of the tiles in the given direction.
		//Then, they combine all of the tiles that are next to each other and have the same value. Then, they move all of the tiles again to
		//fill the empty spaces created when combining the tiles with the other non-combined tiles.
		moveLeft(g); 
		for(int i=0; i<board.length; i++)
		{
			for(int j=1; j<board[0].length; j++)
			{
				if(board[i][j] != null && board[i][j-1] != null)
				{
					if(board[i][j-1].getVal() == board[i][j].getVal())
					{
						board[i][j-1].updateVal();
						board[i][j] = null;
						JButton button = g.squares[i][j-1];
						Timer t = new Timer();
						class Helper extends TimerTask 
						{ 
						    int borderSize = 7;
						    int fontSize = 55;
						    boolean goBack = false;
						    public void run() 
						    {
						    	if(borderSize < 3)
						    	{
						    		goBack = true;
						    		
						    	}
						    	if(goBack == true)
						    	{
						    		if(borderSize == 7)
						    		{
						    			goBack = false;
						    			t.cancel();
						    		}
						    		else
						    		{
						    			borderSize++;
						    			fontSize -=5;
						    		}
						    	}
						    	else
						    	{
						    		borderSize--;
						    		fontSize +=5;
						    	}
						    	
						    	button.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),borderSize));
						    	button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
						    	g.revalidate();
						    } 
						}
						t.schedule(new Helper(),1,25);
						g.updateGame();
					}
				}					
			}
		}
		
		moveLeft(g);
	}
	
	private void moveLeft(GamePanel g)
	{
		//This method and the other move____ methods are private helper methods for the main directional methods which move all of the tiles in the given direction.
		for(int i=0; i<board.length; i++)
		{
			for(int j=1; j<board[0].length; j++)
			{
				if(board[i][j] != null)
				{
					int cur = j-1;
					while(cur >= 0 && board[i][cur] == null)
					{
						board[i][cur] = board[i][cur+1];
						board[i][cur+1] = null;
						cur--;
						g.updateGame();
					}
				}					
			}
		}
	}
	
	public void right(GamePanel g)
	{
		
		moveRight(g);
		
		for(int i=0; i<board.length; i++)
		{
			for(int j=board[0].length-2; j>=0; j--)
			{
				if(board[i][j] != null && board[i][j+1] != null)
				{
					if(board[i][j+1].getVal() == board[i][j].getVal())
					{
						board[i][j+1].updateVal();
						board[i][j] = null;
						JButton button = g.squares[i][j+1];
						Timer t = new Timer();
						class Helper extends TimerTask 
						{ 
						    int borderSize = 7;
						    int fontSize = 55;
						    boolean goBack = false;
						    public void run() 
						    {
						    	if(borderSize < 3)
						    	{
						    		goBack = true;
						    		
						    	}
						    	if(goBack == true)
						    	{
						    		if(borderSize == 7)
						    		{
						    			goBack = false;
						    			t.cancel();
						    		}
						    		else
						    		{
						    			borderSize++;
						    			fontSize -=5;
						    		}
						    	}
						    	else
						    	{
						    		borderSize--;
						    		fontSize +=5;
						    	}
						    	
						    	button.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),borderSize));
						    	button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
						    	g.revalidate();
						    } 
						}
						t.schedule(new Helper(),1,25);
						g.updateGame();
					}
				}					
			}
		}
		
		moveRight(g);
		
		
	}
	
	private void moveRight(GamePanel g)
	{
		for(int i=0; i<board.length; i++)
		{
			for(int j=board[0].length-2; j>=0; j--)
			{
				if(board[i][j] != null)
				{
					int cur = j+1;
					while(cur < board[i].length && board[i][cur] == null)
					{
						board[i][cur] = board[i][cur-1];
						board[i][cur-1] = null;
						cur++;
						g.updateGame();
					}
				}					
			}
		}
	}
	public void up(GamePanel g)
	{
		moveUp(g);
		
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=1; i<board.length; i++)
			{
				if(board[i][j] != null && board[i-1][j] != null)
				{
					if(board[i-1][j].getVal() == board[i][j].getVal())
					{
						board[i-1][j].updateVal();
						board[i][j] = null;
						JButton button = g.squares[i-1][j];
						Timer t = new Timer();
						class Helper extends TimerTask 
						{ 
						    int borderSize = 7;
						    int fontSize = 55;
						    boolean goBack = false;
						    public void run() 
						    {
						    	if(borderSize < 3)
						    	{
						    		goBack = true;
						    		
						    	}
						    	if(goBack == true)
						    	{
						    		if(borderSize == 7)
						    		{
						    			goBack = false;
						    			t.cancel();
						    		}
						    		else
						    		{
						    			borderSize++;
						    			fontSize -=5;
						    		}
						    	}
						    	else
						    	{
						    		borderSize--;
						    		fontSize +=5;
						    	}
						    	
						    	button.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),borderSize));
						    	button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
						    	g.revalidate();
						    } 
						}
						t.schedule(new Helper(),1,25);
						g.updateGame();
					}
				}					
			}
		}
		
		moveUp(g);
	}
	
	private void moveUp(GamePanel g)
	{
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=1; i<board.length; i++)
			{
				if(board[i][j] != null)
				{
					int cur = i-1;
					while(cur >= 0 && board[cur][j] == null)
					{
						board[cur][j] = board[cur+1][j];
						board[cur+1][j] = null;
						cur--;
						g.updateGame();
					}
				}					
			}
		}
	}
	public void down(GamePanel g)
	{
		moveDown(g);
		
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=board.length-2; i>=0; i--)
			{
				if(board[i][j] != null && board[i+1][j] != null)
				{
					if(board[i+1][j].getVal() == board[i][j].getVal())
					{
						board[i+1][j].updateVal();
						board[i][j] = null;
						JButton button = g.squares[i+1][j];
						Timer t = new Timer();
						class Helper extends TimerTask 
						{ 
						    int borderSize = 7;
						    int fontSize = 55;
						    boolean goBack = false;
						    public void run() 
						    {
						    	if(borderSize < 3)
						    	{
						    		goBack = true;
						    		
						    	}
						    	if(goBack == true)
						    	{
						    		if(borderSize == 7)
						    		{
						    			goBack = false;
						    			t.cancel();
						    		}
						    		else
						    		{
						    			borderSize++;
						    			fontSize -=5;
						    		}
						    	}
						    	else
						    	{
						    		borderSize--;
						    		fontSize +=5;
						    	}
						    	
						    	button.setBorder(BorderFactory.createLineBorder(new Color(187,173,160),borderSize));
						    	button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
						    	g.revalidate();
						    } 
						}
						t.schedule(new Helper(),1,25);
						g.updateGame();
					}
				}					
			}
		}
		
		moveDown(g);	
	}
	
	private void moveDown(GamePanel g)
	{
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=board.length-2; i>=0; i--)
			{
				if(board[i][j] != null)
				{
					int cur = i+1;
					while(cur < board[i].length && board[cur][j] == null)
					{
						board[cur][j] = board[cur-1][j];
						board[cur-1][j] = null;
						cur++;
						g.updateGame();
					}
				}					
			}
		}
	}
	
	
	
	
	public void left()
	{
		//This method and the other directional methods execute the command entered by first moving all of the tiles in the given direction.
		//Then, they combine all of the tiles that are next to each other and have the same value. Then, they move all of the tiles again to
		//fill the empty spaces created when combining the tiles with the other non-combined tiles.
		moveLeft(); 
		
		for(int i=0; i<board.length; i++)
		{
			for(int j=1; j<board[0].length; j++)
			{
				if(board[i][j] != null && board[i][j-1] != null)
				{
					if(board[i][j-1].getVal() == board[i][j].getVal())
					{
						board[i][j-1].updateVal();
						board[i][j] = null;
						
					}
				}					
			}
		}
		
		moveLeft();
	}
	
	private void moveLeft()
	{
		//This method and the other move____ methods are private helper methods for the main directional methods which move all of the tiles in the given direction.
		for(int i=0; i<board.length; i++)
		{
			for(int j=1; j<board[0].length; j++)
			{
				if(board[i][j] != null)
				{
					int cur = j-1;
					while(cur >= 0 && board[i][cur] == null)
					{
						board[i][cur] = board[i][cur+1];
						board[i][cur+1] = null;
						cur--;
						
					}
				}					
			}
		}
	}
	
	public void right()
	{
		moveRight();
		
		for(int i=0; i<board.length; i++)
		{
			for(int j=board[0].length-2; j>=0; j--)
			{
				if(board[i][j] != null && board[i][j+1] != null)
				{
					if(board[i][j+1].getVal() == board[i][j].getVal())
					{
						board[i][j+1].updateVal();
						board[i][j] = null;
						
					}
				}					
			}
		}
		
		moveRight();
		
	}
	
	private void moveRight()
	{
		for(int i=0; i<board.length; i++)
		{
			for(int j=board[0].length-2; j>=0; j--)
			{
				if(board[i][j] != null)
				{
					int cur = j+1;
					while(cur < board[i].length && board[i][cur] == null)
					{
						board[i][cur] = board[i][cur-1];
						board[i][cur-1] = null;
						cur++;
						
					}
				}					
			}
		}
	}
	
	public void up()
	{
		moveUp();
		
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=1; i<board.length; i++)
			{
				if(board[i][j] != null && board[i-1][j] != null)
				{
					if(board[i-1][j].getVal() == board[i][j].getVal())
					{
						board[i-1][j].updateVal();
						board[i][j] = null;
						
					}
				}					
			}
		}
		
		moveUp();
	}
	
	private void moveUp()
	{
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=1; i<board.length; i++)
			{
				if(board[i][j] != null)
				{
					int cur = i-1;
					while(cur >= 0 && board[cur][j] == null)
					{
						board[cur][j] = board[cur+1][j];
						board[cur+1][j] = null;
						cur--;
						
					}
				}					
			}
		}
	}
	
	public void down()
	{
		moveDown();
		
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=board.length-2; i>=0; i--)
			{
				if(board[i][j] != null && board[i+1][j] != null)
				{
					if(board[i+1][j].getVal() == board[i][j].getVal())
					{
						board[i+1][j].updateVal();
						board[i][j] = null;
						
					}
				}					
			}
		}
		
		moveDown();	
	}
	
	private void moveDown()
	{
		for(int j=0; j<board[0].length; j++)
		{
			for(int i=board.length-2; i>=0; i--)
			{
				if(board[i][j] != null)
				{
					int cur = i+1;
					while(cur < board[i].length && board[cur][j] == null)
					{
						board[cur][j] = board[cur-1][j];
						board[cur-1][j] = null;
						cur++;
						
					}
				}					
			}
		}
	}
	public boolean checkDifferent(Tile[][] t)
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j] == null || t[i][j] == null)
				{
					if(!(board[i][j] == null && t[i][j] == null))
					{
						return true;
					}
				}
				else if(board[i][j].getVal() != t[i][j].getVal())
				{
					return true;
				}
			}
			
		}
		return false;
	}
	
	public Tile[][] copyBoard(Tile[][] t)
	{
		//Creates a copy of the board
		Tile[][] copy = new Tile[4][4];
		for(int i=0; i<t.length; i++)
		{
			for(int j=0; j<t[0].length; j++)
			{
				if(t[i][j] != null)
				{
					copy[i][j] = new Tile(t[i][j].getVal());
				}
			}
		}
		return copy;
	}
	
	public boolean moveAvailable()
	{
		//Checks if there is a move available by checking if the board changes when moved left, right, up, and down.
		//If board is the same after these moves (all tiles filled), then checks if any tiles can be combined with each other.
		//If the board is the same after all moves and no tiles can be combined, returns false. Otherwise, returns true.
		Tile[][] temp = copyBoard(board);
		
		moveLeft();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		
		moveRight();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		
		moveUp();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		
		moveDown();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		board = copyBoard(temp);
		
		for(int i=0;i<board.length;i++) //Checks if any tile combinations are available.
		{
			for(int j=0;j<board[0].length;j++)
			{
				if(i == board.length-1 || j == board[0].length-1)
				{
					if(i == board.length-1 && j != board[0].length-1)
					{
						if(board[i][j].getVal() == board[i][j+1].getVal())
						{
							return true;
						}
					}
					if(i != board.length-1 && j == board[0].length-1)
					{
						if(board[i][j].getVal() == board[i+1][j].getVal())
						{
							return true;
						}
					}
				}
				else
				{
					if(board[i][j].getVal() == board[i][j+1].getVal() || board[i][j].getVal() == board[i+1][j].getVal())
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public Tile[][] getBoard()
	{
		//getter
		return board;
	}
	
	public static void updateScore(int val)
	{
		//Adds val to points
		points += val;
	}
	
	public void testFillBoard() 
	{
		//Creates a board where each tile has a different value.
		//Used to test the moveAvailable method to make sure it would correctly end the game in this case.
		//Currently not being called.
		int counter = 0;
		for(int i=0;i<board.length;i++) 
		{
			for(int j=0;j<board[0].length;j++)
			{
				board[i][j] = new Tile(counter);
				counter++;
			}
		}
	}
	
	public int getPoints()
	{
		int p = points;
		return p;
	}
	
	
	
	
}
