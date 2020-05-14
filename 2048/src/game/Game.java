package game;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	Scanner in = new Scanner(System.in);
	
	//stores values for each tile in game
	Tile[][] board = new Tile[4][4];
	
	//current number of points earned
	static int points;
	
	public void setUpBoard()
	{
		choosePositionAndPlace();
		choosePositionAndPlace();
	}
	

	public void printBoard()
	{
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
		if(openCounter == 0)
		{
			return;
		}
		int randomPos = ThreadLocalRandom.current().nextInt(0,openCounter); //sets randompos equal to an integer from 0 to openCounter-1
		openCounter = 0; //resets openCounter to use a second time
		for(int i=0;i<board.length;i++) //traverses through board a second time until it reaches the position of the new tile
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
		System.out.println("Game Over");
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
	
	private void left()
	{
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
	
	private void right()
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
	private void up()
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
	private void down()
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
	
	public boolean moveAvailable() //Checks if there is a move available by checking if the board changes when moved left, right, up, and down 
	{
		Tile[][] temp = copyBoard(board);
		
		left();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		
		right();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		
		up();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		
		down();
		if(checkDifferent(temp))
		{
			board = copyBoard(temp);
			return true;
		}
		board = copyBoard(temp);
		return false;
	}
	
//	public static void updateScore(int val)
//	{
//		points+=val;
//	}
	
	
	
	
}
