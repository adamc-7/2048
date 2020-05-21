package game;

public class Tile {
	
	//the numerical value of the tile
	private int value;
	
	public Tile()
	{
		//constructor
		double rand = Math.random();	//chooses the initial value of the tile (90% chance of 2 and 10% chance of 4) based on the probabilities of the real 2048 game
		if(rand<0.9)
		{
			value = 2;
		}
		else
		{
			value = 4;
		}
	}
	
	public Tile(int newValue)
	{
		//constructor
		value = newValue;
	}
	
	public int getVal()
	{
		//getter
		return value;
	}
	
	public void updateVal()
	{
		//Updates the value of the tile. Called when one tile is combined with another tile.
		value *= 2;
		Game.updateScore(value); //calls the update score method to add the new value to the point total of the game
	}
	
}
