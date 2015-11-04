import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * @author pasko22v
 *Mine.java
 *
 **/

public class Mine extends JButton 
{

	/**
	 * instance properties
	 */
	//create an instance property of the bomb image
	Image bombImage; 
	
	//create an instance property of the flag 
	Image flagImage; 
	
	//create an instance variable that is going to hold the number of surrounding mines
	int numOfSurroundingBombs;
		
	
	//create a boolean instance property that is going to represent the bomb
	private boolean bomb; 
	
	//create a boolean instance property that shows if the mine is clicked
	private boolean isClicked; 
	
	//create a boolean instance property that is going to store if a square if flagged
	private boolean isFlagged; 
	
	/*
	 * constructor
	 */


	public Mine()
	{
		//set the square without a bomb
		bomb=false; 
		//set the square as unclicked
		isClicked=false; 
		//set isFlagged as false
		isFlagged=false; 
		
	}
	
	/*
	 * instance methods
	 */
	//make a function that is going to paint the square
	public void paint(Graphics g)
	{
		//check if the mine is clicked
		if(isClicked==false && isFlagged==false)
		{
			//set the color of the box
			g.setColor(Color.GRAY); 
			//fill
			g.fillRect(0, 0, getWidth(), getHeight());
			//set the color of the border of the box
			g.setColor(Color.BLACK);
			//draw it 
			g.drawRect(0,0,getWidth(),getHeight());	
		
		}
		else if(isClicked==true)
		{
			//check if mine has a bomb or not
			if(bomb==false)
			{
				//set the inside of the box
				g.setColor(Color.WHITE);
				//fill
				g.fillRect(0, 0, getWidth(), getHeight());
				//set the color of the border
				g.setColor(Color.BLACK);
				//draw the rectangle
				g.drawRect(0,0,getWidth(),getHeight());	
				//add the number of surrounding bombs of the square
				g.drawString(Integer.toString(numOfSurroundingBombs), getWidth()/2, getHeight()/2);
				
			}
			else if (bomb==true)
			{
				//set the inside of the box 
				g.setColor(Color.GRAY);
				//fill
				g.fillRect(0, 0, getWidth(), getHeight());
				//set color of the background 
				g.setColor(Color.BLACK);
				//draw rectangle 
				g.drawRect(0,0,getWidth(),getHeight());	
				//g.drawOval(0, 0, getWidth(), getHeight());
				BufferedImage bombImage = null;
				try 
				{
				    bombImage = ImageIO.read(new File("bomb.jpg"));
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				//draw the image
				g.drawImage(bombImage, 0, 0, null);
			
			}

		}
		else if (isFlagged==true)
		{
			//set the inside of the box 
			g.setColor(Color.GRAY);
			//fill
			g.fillRect(0, 0, getWidth(), getHeight());
			//set color of the border 
			g.setColor(Color.BLACK);
			//draw rectangle 
			g.drawRect(0,0,getWidth(),getHeight());	
			//g.drawOval(0, 0, getWidth()/2, getHeight()/2);
			
			BufferedImage flagImage = null;
			try 
			{
			    flagImage = ImageIO.read(new File("flag.png"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			//draw the image
			g.drawImage(flagImage, 0, 0, null);
		}
		
		
	}
	
	//make a function that is going to set the bomb as true
	public void setBomb()
	{
		//set bomb to true
		bomb=true; 
	}
	
	//make a function that is going to check if there is a bomb in the mine
	public boolean getBomb()
	{
		//return bomb
		return bomb; 	
	}
	
	//make a function that is going to set the bomb as clicked
	public void setClicked()
	{
		//set square as clicked
		isClicked=true; 
	}
	
	//make a function that sets the square as not clicked 
	public void setNotClicked()
	{
		//set square as clicked
		isClicked=false; 
	}
	
	//make a getter function that is going to check if the square is clicked
	public boolean getClicked()
	{
		//return is Clicked
		return isClicked; 
	}
	//make a getter function for the number of surrounding bombs
	public int getNumOfSurroundingBombs() 
	{
		//return the number of surrounding bombs 
		return numOfSurroundingBombs;
	}

	//make a setter function for the number of surrounding bombs 
	public void setNumOfSurroundingBombs(int numOfSurroundingBombs) 
	{
		//set the number of surrounding bombs
		this.numOfSurroundingBombs = numOfSurroundingBombs;
	}
	
	//make a getter function for the flagging of the mine
	public boolean getFlagged() 
	{
		//return isFlagged
		return isFlagged;
	}

	//make a setter function for the flagging of the mine
	public void setFlagged() 
	{
		//set as flagged
		isFlagged = true;
	}
	

	
	
}
