//  Program to implement Tic Tac Toe game using GUI in Java

/*
    Steps to create TIC TAC TOE game.

    1.  Create a window
    2.  Set the window to 3x3 grid layout
    3.  Add 9 buttons to the grid and initialise them
    4.  Add action listeners to the buttons
    5.  Checks for winner and display the winner
    6.  Checks for draw 
    7.  Reset the buttons if we want to play again.

*/


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TicTacToe extends JPanel {
	
	char playerMark = 'x';
	JButton[] buttons = new JButton[9];
	
	public TicTacToe() {
		setLayout(new GridLayout(3,3));
		initializeButtons();
		
	}
	
	// method used to create 9 buttons
	// set the default text, add action listeners
	// adding them to the screen
	public void initializeButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText(" ");
            buttons[i].setBackground(Color.white);
            buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton buttonClicked = (JButton) e.getSource(); //get the particular button that was clicked
			        buttonClicked.setText(String.valueOf(playerMark));
                                buttonClicked.setFont(new Font(Font.SERIF, Font.BOLD, 60));
			        
			        
			        if(playerMark == 'x') {
			        	playerMark = 'o';
			        	buttonClicked.setBackground(Color.GREEN);
			        }
			        else {
			        	playerMark ='x';
			        	buttonClicked.setBackground(Color.ORANGE);
			        }
			        displayWinner();
			        
					
				}
			});
            
            add(buttons[i]); //adds this button to JPanel        
        }
    }
	
	
	// display the player who wins.
	
	public void displayWinner() {
		
		if(checkForWinner() == true) {
			
			/* reverse the player marks
			   because after we put x and win, the game changes it to o
			   but x is the winner*/
			if(playerMark == 'x')
                             playerMark = 'o';
	                else
                             playerMark ='x';
			
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. "+ playerMark + " wins. Would you like to play again?","Game over.",
					JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION)
                              resetTheButtons();
			else 
                              System.exit(0);
		}
		
		else if(checkDraw()) {
			JOptionPane pane = new JOptionPane();
			int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Play again?","Game over.", JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION) 
                              resetTheButtons();
			else
                              System.exit(0);
		}
	}
	
	
	
	// Method used to reset the buttons. So you can play again
	private void resetTheButtons() {
		playerMark = 'x';
		for(int i =0;i<9;i++) {
			  
			  buttons[i].setText(" ");
			  buttons[i].setBackground(Color.white);
			  
		  }	
	}

	//  Method to check for a draw
	
	public boolean checkDraw() {
		boolean full = true;
		for(int i = 0 ; i<9;i++) {
			if(buttons[i].getText().charAt(0) == ' ') {
				full = false;
			}
		}
		return full;
	}
	
	        // Method to check for a winner
		public boolean checkForWinner() {
			if(checkRows() == true || checkColumns() == true || checkDiagonals() == true)
                           return true;
			else
                           return false;
		}
		
		// checks rows for a win
		public boolean checkRows() {
			int i = 0;
			for(int j = 0;j<3;j++) {
			if( buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) 
					&& buttons[i].getText().charAt(0) != ' ') {
				return true;
			}
			i = i+3;
			
		        }
			return false;
	        }
		
		
		// checks columns for a win
		public boolean checkColumns() {
			
			int i = 0;
			for(int j = 0;j<3;j++) {
			if( buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())
					&& buttons[i].getText().charAt(0) != ' ') 
			{
				return true;
			}
			i++;
			}
			return false;	
		}
		
		// checks diagonals for a win
		public boolean checkDiagonals() {
			if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
					&& buttons[0].getText().charAt(0) !=' ')
				return true;
			else if(buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
					&& buttons[2].getText().charAt(0) !=' ') return true;
				
			else return false;
		}
	
	
	
	public static void main(String[] args) {
        JFrame window = new JFrame("Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe()); // adds the data
        window.setBounds(500,500,500,500); // window size
        window.setVisible(true); // show the window
        window.setLocationRelativeTo(null); // place the window to the center of the screen
	}

}