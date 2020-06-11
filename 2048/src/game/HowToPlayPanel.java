package game;

import java.awt.*;    
import java.awt.event.*;
import javax.swing.*;

public class HowToPlayPanel extends JPanel{
	JLabel tutorial;
	JButton goBack;
	String line1 ="Use the Up, Down, Left, and Right buttons to move all tiles on the ";
	String line2 ="grid the farthest they can travel in that direction. If two tiles with ";
	String line3 ="the same number collide, they combine into one new tile with a value equal ";
	String line4 ="to the sum of the tiles that collided. For an example, a 2 tile and a 2 tile ";
	String line5 ="will combine into a 4 tile. If two 4 tiles collide, they become an 8 tile and ";
	String line6 ="so on. The object of the game is to create a tile worth 2048. After each move ";
	String line7 ="made by the player, a new tile is generated in a random location. If the board ";
	String line8 ="has no empty spaces and the player cannot combine tiles to ";
	String line9 ="create a space, the game ends.";
	
	public HowToPlayPanel()
	{
		tutorial= new JLabel("<html><div style ='text-align:center;'>"+line1+"<br/>"+line2+"<br/>"+line3+"<br/>"+line4+"<br/>"+line5+"<br/>"+line6+"<br/>"+line7+"<br/>"+line8+"<br/>"+line9+"</div></html>");
		this.add(tutorial);
		goBack = new JButton("Go Back");
		this.add(goBack);
		setSize(500,643);
	}
}
