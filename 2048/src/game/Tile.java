package game;

public class Tile {
	//the numerical value of the tile
	private int value;
	
	public Tile()
	{
		//constructor
		double rand = Math.random();
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
		value = newValue;
	}
	
	public int getVal()
	{
		return value;
	}
	public void updateVal()
	{
		value *= 2;
	}
	
}
