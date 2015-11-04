
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.applet.Applet;

import javax.swing.Timer;
/**
 * 
 * @author pasko22v
 *MinesweeperGUI.java
 *
 **/
public class MinesweeperGUI implements ActionListener
//MouseListener
{




/**
 * instance properties
 */
	//create the three buttons for the first screen
	private JButton beginner; 
	private JButton intermediate; 
	private JButton advanced; 
	
	//create an instance of the board
	private Board board; 
	
	//create an instance of a Timer
	private Timer timer; 
	
	//create an instance of the timer box
	private JTextField timerTextField;
	
	//create am instance property of the restart button 
	private JButton restartButton;
	
	//create an instance property of the screen
	private JFrame screen; 
	
	//create an instance property of the first screen
	private JPanel buttonPanel; 
	
	//create an instance property of the second screen
	private JPanel secondScreen; 
	
	//create an instance property of the score box
	private JTextField scoreBox; 
	
	

/*
 * constructor
 */
	
	public MinesweeperGUI()
	{
		//create the new frame 
		screen = new JFrame( "Minesweeper" );
		//set the size of the frame
	 	screen.setSize( 1500, 1500 );
	 	screen.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	 	//add the initial screen 
	 	screen.getContentPane().add( initialScreen() );
	 	screen.setVisible( true );
	 	//screen.setResizable(false);	
		
	}
	
/*
 * instance methods
 */

	
	//create a function that is going to make the initial screen
	public JPanel initialScreen()
	{
		//set the new screen
		buttonPanel= new JPanel(); 
		//set is a grid
		buttonPanel.setLayout(new GridLayout(3,1));
		
		//add the beginner button 
		beginner= new JButton("Beginner"); 
		buttonPanel.add(beginner); 
		//add action Listener to the button so that we know when it is clicked
		beginner.addActionListener(this); 
		
		//add the intermediate button 
		intermediate= new JButton("Intermediate"); 
		buttonPanel.add(intermediate); 
		//add action Listener to the button so that we know when it is clicked
		intermediate.addActionListener(this); 
		
		//add the advanced button 
		advanced= new JButton("Advanced"); 
		buttonPanel.add(advanced); 
		//add action Listener to the button so that we know when it is clicked
		advanced.addActionListener(this); 
			
		return buttonPanel; 
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		//create three if statements that check which one of the three buttons is clicked 
		//check if the beginner button is clicked
		if(arg0.getSource()==beginner)
		{
			//add the second screen setting the board size as 8
			secondScreen(8);
		}
		//check if the intermediate button is clicked
		if(arg0.getSource()==intermediate)
		{
			//add the second screen setting the board size as 10
			secondScreen(12);
		}
		//check if the advanced button is clicked
		if(arg0.getSource()==advanced)
		{
			//add the second screen setting the board size as 12
			secondScreen(16);
		}
		//check if the restart button is clicked
		if(arg0.getSource()==restartButton)
		{
			//call endTimer finction 
			endTimer(); 
			//remove the second screen
			screen.getContentPane().remove( secondScreen);
			//show the initial screen  
			screen.getContentPane().add( buttonPanel );
			//validate
			screen.validate();
			//repaint
			screen.repaint();
					
		}
		
	}
	
	
	
	//make a function that is going to add the board to the panel 
	public JPanel secondScreen(int boardSize)
	{
		
		//make a new instance of the board with the specific board size 
  		board=new Board(boardSize); 
  		
  		//create a JPanel that represents the name and the timer and the restart button and the score box
  	    JPanel secondScreenAdditional = new JPanel(); 
  	    secondScreenAdditional.setLayout(new GridLayout(2,3));
  	    //create a blank text box
  	    JTextField blanktextField1 = new JTextField("Minesweeper");
  	    //add the text field to the panel
  	    secondScreenAdditional.add(blanktextField1); 
  	    //create a text box that contains the name of the game 
  	    JTextField minesweeper = new JTextField("Minesweeper");
  	    //add the text field to the panel
  	    secondScreenAdditional.add(minesweeper);
  	    //create a blank text box
  	    JTextField blanktextField2 = new JTextField("Minesweeper");
  	    //add the text field to the panel
  	    secondScreenAdditional.add(blanktextField2);
  	    //add the timer
  	    timerTextField = new JTextField("0000"); 
  	    //call setUpTimer()
  	    setUpTimer(); 
  	    //add the text field to the panel
  	    secondScreenAdditional.add(timerTextField);
  	    //create the restart button
  	    restartButton = new JButton("Restart");
  	    //create an action listener to the restart button
  	    restartButton.addActionListener(this);
  	    //add the text field to the panel
  	    secondScreenAdditional.add(restartButton);
  	    //create a score box
  	     scoreBox = new JTextField("");
  	    //add the score to the box
  	    scoreBox.setText(board.stringScore());
  	    
  	    //add a mouse event listener to the board 
  	    //board.addMouseListener(this); 
  	    //add the text field to the panel
  	    secondScreenAdditional.add(scoreBox);
  		
  		//create a JPanel that represents the whole screen
  	    secondScreen = new JPanel();
  	    //set the second screen as border layout 
  	    secondScreen.setLayout(new BorderLayout());
  	    //add the board to the second screen
  	    secondScreen.add(board, BorderLayout.CENTER); 
  	    //add the name and the timer and the restart button and the score box
  	    secondScreen.add(secondScreenAdditional, BorderLayout.NORTH); 
		
  	    //remove the initial screen
	    screen.getContentPane().remove( buttonPanel);
	    //show the second screen with the board, timer etc. 
	    screen.getContentPane().add( secondScreen );
	    //validate
	    screen.validate();
	    //repaint
	    screen.repaint();
	    
 
	   return secondScreen;  
	    
	}
	
	
	//create a function that starts the timer	
	public void setUpTimer()
	{
		
		//delay in milliseconds
		int delay = 1000;
		
		//create an action listener variable
		ActionListener taskPerformed = new ActionListener()
		{
			//create action performed
			public void actionPerformed(ActionEvent e)
			{
				//call addSeconds
			addSeconds(); 	
			}
		};
		//add the new timer instance
		timer= new Timer(delay, taskPerformed);
		//start the zero 
		timer.start(); 
	}
	
	//create a function that is going to add seconds 
	public void addSeconds()
	{
		//create a string variable to store the number of seconds
		String numSeconds; 
		//take the number of seconds from the textfield
		numSeconds=timerTextField.getText(); 
		//create an integer variable to add the new second to it 
		int intNumSeconds = Integer.parseInt(numSeconds); 
		intNumSeconds= intNumSeconds + 1;
		//add the second to the textfield
		timerTextField.setText(Integer.toString(intNumSeconds)); 
		setScore(); 
		
	}

	
	//create a function that ends the timer
	public void endTimer()
	{
		//stop the timer
		timer.stop(); 
	}
	
	
	
	public static void main( String[] args )
	  {
		 	
		
		// create a new instance of the class to get everything started
				new MinesweeperGUI();
	  }

	
	
	
	public void setScore()
	{
		if(board.getCalculatedScore()==true)
		{
			//System.out.println("Currrent Score (in GUI) :"+ board.calculateScore());
			//call the following functions 
			scoreBox.setText(board.stringScore());
			board.setNotCalculatedScore(); 
		}
	}
	
	/*
	@Override
	// create a function that is going to recognize the click 
	public void mouseClicked(MouseEvent arg0)
	{
		//if the click is on the board  
		if (board.equals (arg0.getSource()))
		{
			System.out.println("(in GUI) ");
			setScore(); 
			
		}
		
	}
	//those functions are needed for the Mouse Listener
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	*/
	
}