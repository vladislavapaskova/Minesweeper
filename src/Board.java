import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author pasko22v
 *Board.java
 *
 **/



public class Board extends JPanel implements ActionListener, MouseListener 
{

	/**
	 * instance properties
	 */
	
	//create an object array that is going to represent all of the squares on the board
	Mine[][]squareArray;
	
	//create an instance property that is going to reference to the  mine that is clicked
	Mine clickedSquare; 
	
	//create an instance property that is going to refer to a mine that has been flagged
	Mine flaggedSquare; 
	
	//create an instance property that is going to contain the size of the board
	private int boardSize; 
	
	//create an instance property that is going to reference the score that is going to be shown on the screen 
	private int score; 
	
	//instance property that is going to reference the score calculation 
	private boolean calculatedScore; 
	
	//create an instance property that is going to represent the real score
	private int realScore; 
	
	
 
	
	/*
	 * constructor
	 */
	

	public Board()
	{
		//set board size to 6
		boardSize=6;
		//calculate the initial value of the score
		score= (int) (boardSize*boardSize*0.125);
		//calculate the initial value of the real score
		realScore= (int) (boardSize*boardSize*0.125); 
		 
		//call createBoard()
		createBoard();
		//call put Bombs
		putBombs();
 
	}
	public Board(int boardSize)
	{
		//set board size 
		this.boardSize=boardSize; 
		//calculate the initial value of the score
		score= (int) (boardSize*boardSize*0.125);
		//calculate the initial value of the real score
		realScore= (int) (boardSize*boardSize*0.125); 
				
		//call createBoard()
		createBoard();
		//call put Bombs
		putBombs();
		
		//make the calculaed score as false so that the GUI is not triggered 
		calculatedScore=false; 
		

	}
	
	/*
	 * instance methods
	 */
	
	//make a function that is going to create a two dimensional array that is going to represent the board
	public void createBoard()
	{
		//assign the dimensions of the two dimensional array
		squareArray= new Mine[boardSize][boardSize];
		//set the board with fixed dimensions
		setLayout(new GridLayout(boardSize,boardSize));
		//create two for loop that is going to put all of the squares into the two dimensional array
		for( int i=0; i < squareArray.length; i++)
		{
			for( int j=0; j < squareArray[i].length; j++)
			{
				//put one mine in each slot
				squareArray[i][j]= new Mine();
				//add the square
				this.add(squareArray[i][j]);
				//add action listener to the square
				squareArray[i][j].addActionListener(this);
				squareArray[i][j].addMouseListener(this); 
			}
		}
	}
	
	//create a function that is going to put the bombs 
	public void putBombs()
	{
		//create variables that are going to store the x and y coordinates of the bomb
		int xCoordinate; 
		int yCoordinate;
		
		//create a for loop that is going to put all of the bombs
				for( int i=0; i < (int)(boardSize*boardSize*0.125); i++)
				{
					//assign random values for the x and y coordinates of the mines 
					xCoordinate = (int) (Math.random() * (boardSize)); 
					yCoordinate = (int) (Math.random() * (boardSize));
						
					//make an if statement that is going to check if the coordinates of the bomb are repeated
					if(squareArray[xCoordinate][yCoordinate].getBomb() == true )
					{
						//decrease i so that the loop is going one more time
						i=i-1; 
					}
					else
					{
						//put one mine in each slot
						squareArray[xCoordinate][yCoordinate].setBomb(); 
						
					}
				}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//get the source of the clicking 
		clickedSquare = (Mine) arg0.getSource();
		//check clickedSquare
		if(!clickedSquare.getClicked())
		{
			//set the square as clicked 
			clickedSquare.setClicked();
			
			//call plaerWins
			playerWins(); 
		
		//create an if else statement that evaluates if the clicked square is a bomb
		if (clickedSquare.getBomb())
		{
			//end the game 
			//this.setVisible( false ); 
			//show all of the bombs on the screen 
			displayAllBombs(); 
			//display loose message 
			JOptionPane.showMessageDialog(this,"You Lose:(. Please press the restart button if you wish to play another round.");
			
			
		}
		else 
		{
			setCalculatedScore(); 
			//call the function that is going to add to the score displayed
			calculateAdditionScore(); 
			stringScore();
			
			//create a loop that is going to go through all of the squares and check which one is clicked
			for( int i=0; i < squareArray.length; i++)
			{
				for( int j=0; j < squareArray[i].length; j++)
				{
					//create an if/else statement that is going to check if the square is the clicked square
					if (clickedSquare.equals(squareArray[i][j]))
					{
						//set the square as not clicked 
						squareArray[i][j].setNotClicked(); 
						//call openSquares
						openSquares(i,j); 
						
					}
					
					
				}
			} 

		}
		}
		
	}
	
	//create a function that is going to repaint the squares and initiate recursion 
	public void openSquares(int i, int j)
	{
		//check if square is not clicked 
		if(!squareArray[i][j].getClicked()) 
	
		{
			//set the square as clicked 
			squareArray[i][j].setClicked();
			//calculate the number of bombs around 
			numBombsAround(i,j); 
			//repaint 
			squareArray[i][j].repaint();
					
					//if the square has no bombs around 
					if(squareArray[i][j].getNumOfSurroundingBombs()==0)
					{
						//initiate a double for loop that is going to check all of the squares around 
						for (int k=(-1); k<2; k++)
						{
							for(int l=(-1); l<2; l++)
							{
								//create two variables that are going to contain the coordinates of the arrays 
								int m; 
								m=i+k; 
								int n; 
								n=j+l;
								//check if the squares exist//border check 
								if(m>=0 && n>=0 && m<boardSize && n<boardSize)
								{
									//check if the square has not already been clicked 
									if(squareArray[m][n].getClicked()==false)
									{
										//set the square as clicked 
										squareArray[m][n].setClicked();
										//calculate the number of bombs around 
										numBombsAround(m,n); 
										//repaint 
										squareArray[m][n].repaint();
										
										
										
										
										
										//check so that we do not initiate an infinite loop 
										if(!squareArray[m][n].equals(squareArray[i][j]) &&squareArray[m][n].getNumOfSurroundingBombs()==0)
										{
											//set as not clicked 
											squareArray[m][n].setNotClicked();
											//initiate recursion 
											openSquares(m,n);
											
											
											setCalculatedScore(); 
											//call the function that is going to add to the score displayed
											calculateAdditionScoreRecursion(); 
											stringScore();
											
											
											
											
										}
										
									}
								
									
								}
							}
						}
					}
						
		} 
		
	}
	
	
	
	//create a function that is going to calculate the number of surrounding bombs	
		public void numBombsAround(int xCoordinate, int yCoordinate)
		{
			//create a for loop that is going to calculate the number of surrounding bombs
			for (int i=(-1); i<2; i++)
			{
				for(int j=(-1); j<2; j++)
				{
					//check if the squares exist
					if(xCoordinate+i>=0 && yCoordinate+j>=0 && xCoordinate+i<boardSize && yCoordinate+j<boardSize)
					{
						//check if there are any bombs around
						if(squareArray[xCoordinate+i][yCoordinate+j].getBomb())
						{
							//update the surrounding number of bombs
							squareArray[xCoordinate][yCoordinate].setNumOfSurroundingBombs(squareArray[xCoordinate][yCoordinate].getNumOfSurroundingBombs()+1); 
						}
					}
				}
			}
		}

		//create a getter function for board size
		public int getBoardSize() 
		{
			//return board size
			return boardSize;
		}
		
		//create a setter function for board size 
		public void setBoardSize(int boardSize) 
		{
			//set the board size 
			this.boardSize = boardSize;
		}
		
		//create a function that is going to display all of the bombs on the board 
		public void displayAllBombs()
		{
			//create two for loop that go through all of the squares in the array
			for( int i=0; i < squareArray.length; i++)
			{
				for( int j=0; j < squareArray[i].length; j++)
				{
					//check if the specific square has a bomb 
					if(squareArray[i][j].getBomb()==true)
					{
						//set it as clicked
						squareArray[i][j].setClicked(); 
						//repaint it 
						squareArray[i][j].repaint(); 
					}
				}
			}
		}
		
		public void mouseClicked (MouseEvent e)
		{
			flaggedSquare= (Mine) e.getSource(); 
			//create an if statement that is going to check if a right click was used
			if(e.getButton()==((MouseEvent.BUTTON3)))
			{
				//check if the flagged square is a bomb 
				if (flaggedSquare.getBomb())
				{
					//set is a flagged 
					flaggedSquare.setFlagged(); 
					//repaint 
					flaggedSquare.repaint();
					
					setCalculatedScore(); 
					
					//call calculate score and the subsequent function that turns it into a string 
					calculateScore();
					stringScore();
					
					//call the function that keeps track of the score that matters if the player wins 
					calculateRealScore(); 
					
					//check if the player has won 
					playerWins(); 
				}
				else
				{
					//set as flagged 
					flaggedSquare.setFlagged();
					//repaint 
					flaggedSquare.repaint();
					//do not decrease the score 
					
					setCalculatedScore(); 
					
					//call calculate score and the subsequent function that turns it into a string 
					calculateScore();
					stringScore();
				}
			}
			
		}
		
		public int calculateRealScore()
		{
			//update the value of the real score
			realScore=realScore-1; 
			//return the real score
			return realScore; 
		}
		
		//create a getter for the calculated score 
		public boolean getCalculatedScore() 
		{
			//return the boolean for calculated score 
			return calculatedScore;
		}
		//create a setter for calculated score 
		public void setCalculatedScore() 
		{
			//set the calculated score as true 
			this.calculatedScore = true;
		}
		//create a function that is going to set the calulated score as false 
		public void setNotCalculatedScore()
		{
			//set the calculated score as false 
			this.calculatedScore = false;
		}
		
		//create a function that calculates the score 
		public int calculateScore()
		{ 
			
			//decrease the score by one 
			score=score-1;
			
			System.out.println("Currrent Score:"+ score);
			
			
			//return the score//remaining number of mines 
			return score; 
			
		}
		
		//create a function that is going to add to the score so that the screen displays the correct number of flagged squares
		public int calculateAdditionScore()
		{
			if(clickedSquare.getFlagged() && clickedSquare.getFlagged())
			{
				//update the score value
				score=score+1; 
			}
			
			//return the score
			return score; 
			
		}
		
		public int calculateAdditionScoreRecursion()
		{
			if(clickedSquare.getFlagged())
			{
				//update the score value
				score=score+1; 
			}
			
			//return the score
			return score; 
			
		}
		
		//create a function that stores the string value of the score 
		public String stringScore()
		{
			//create a string variable for the score 
			String stringScore; 
			
			//convert the integer value of the score into a string
			stringScore = Integer.toString(score); 
			
			//return the score in a string value//remaining number of mines 
			return stringScore; 
		}
		
		//create a function for when the players wins
		public void playerWins()
		{
			//make an if statement to check if the player has won 
			if(realScore==0)
			{
				//display the win message 
				JOptionPane.showMessageDialog(this,"You Win! :) Please press the restart button if you wish to play another game:)");
			}	
				 
			else 
				{
				//create a variable tha is going to store the number of clicked squares without a bomb 
				int num=0; 
				//go through the array o squares and check if the user has clicked on all of the squares that are not bombs
					for( int i=0; i < squareArray.length; i++)
					{
						for( int j=0; j < squareArray[i].length; j++)
						{
							//check if a square that does not have a omb is clicked 
							if (squareArray[i][j].getClicked()==true && squareArray[i][j].getBomb()==false)
							{
								//update the value of num 
								num=num+1; 
										//check if all of the squares that do not have bombs are clicked
										if(num==(int)(boardSize*boardSize*0.875))
										{
											//display a win message
											JOptionPane.showMessageDialog(this,"You Win! :) Please press the restart button if you wish to play another game:)");
										}
							}
							
						}
					}
					
				}
		}
		
		
		public void mouseEntered (MouseEvent e)
		{
			
		}
		
		public void mouseExited (MouseEvent e)
		{
			
		}
		
		public void mousePressed (MouseEvent e)
		{
			
		}
		
		public void mouseReleased (MouseEvent e)
		{
			
		}
	
	
	
}
