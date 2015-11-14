// COMP SCI 2ME3 

// Assignment 1
// Hassaan Malik (1224997)
// Katrine Rachitsky (1306314)
// Trevor Rae (1324949)
// Navleen Signh (1302228)
// Paul Warnick (1300963)

import java.awt.Color;  // imports for user actions and JFrame components
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Control extends View{ // a class to determines what happens when the user clicks a button in one of the JPanels and also anything the user can do / edit during the run
	
	private static Random rand = new Random (); // a random used for determining who moves first
	private static int mouseClick = rand.nextInt(2), coordinates [][] = new int [7][6], bluecount = 0, redcount = 0, winpoints[][] = new int[4][2]; // ints throughout the program, mouseclick is for which player moves first, coordinates is the where the user clicks in a grid format and the counts determine how many moves each player has done
	private String player1, player2; // strings for the player names
	private String winner; // string for the name of the player that wins
	private boolean wrongFormat = false; // used to see if the users names are in the correct format
	private static JLabel p1 = new JLabel(); // label for player 1
	private static JLabel p2 = new JLabel(); // player 2
	public static int[][] getCoordinates(){
		return coordinates;
		
	}
	private void blueDisk(int x, int y, JPanel panel, boolean gamewin) throws IOException{ // method for placing a blue disk
		final ImageIcon blueimage = new ImageIcon(getClass().getResource("/Bluedisk.png")); // loads in the image
		final ImageIcon blueimagewin = new ImageIcon(getClass().getResource("/BlueWin.png")); // loads in the image
		if(gamewin == true) // once the winner has connect four, that combination of pieces is highlighted with different labels
			blueDisk[x/99][y/95].setIcon(blueimagewin);
		else
			blueDisk[x/99][y/95].setIcon(blueimage); // creates a label with the above image
		
		blueDisk[x/99][y/95].setBounds(102+(x), 2+(y), 93, 93); // sets the location based on the arguments (which come from the players click)
		panel.add(blueDisk[x/99][y/95]); // adds the disk to the panel
		
		if (Model.dev_mode == false){ // below if statements determine if the user is in dev mode or not
			card_layout.show(deck_panel, "GamePanel"); // shows on the actual game board
		}
		
		else{
			card_layout.show(deck_panel, "DeveloperPanel"); // shows on the dev mode board
		}
		
		Control.coordinates[x/99][y/95] = 1; // updates the coordinates 1 at the appropriate place to show that a disk is in that position
		Control.check_disk[x/99][y/95]=true;

		main_frame.repaint(); // repaints the mainframe to update the board
		main_frame.validate(); // insure that the frame has repainted correctly
	}
	
	public void redDisk(int x, int y, JPanel panel, boolean gamewin) throws IOException{ //  same as the above method but for the red disk
		final ImageIcon redimage = new ImageIcon(getClass().getResource("/Reddisk.png"));		
		final ImageIcon redimagewin = new ImageIcon(getClass().getResource("/RedWin.png")); // loads in the image
		if(gamewin == true) // checks if the game has been won, used for changing the disk labels to show which combination has won
			redDisk[x/99][y/95].setIcon(redimagewin); // same as above with over writing the appropriate labels 
		else
			redDisk[x/99][y/95].setIcon(redimage);
		redDisk[x/99][y/95].setBounds(102+(x), 2+(y), 93, 93);
		panel.add(redDisk[x/99][y/95]);	
		
		if (Control.dev_mode == false){
			card_layout.show(deck_panel, "GamePanel");
		}
		
		else{
			card_layout.show(deck_panel, "DeveloperPanel");
		}
		
		Control.coordinates[x/99][y/95] = -1;
		Control.check_disk[x/99][y/95]=true;

		main_frame.repaint();
		main_frame.validate();
	}
	private void singlePlayerNameSet(JPanel panel){
		p1.setFont(new Font("Calibri", Font.ITALIC, 25));
		player1 = JOptionPane.showInputDialog("Please enter Player 1's name: ");
		Model.player1name = player1;
		Model.player2name = player2;
		p2.setText("Computer");
		p1.setText(player1);
		if ((player1 == null) || (p1.getText().length() < 1) || (p1.getText().charAt(0) == ' ')) { // simple error check to see if the user inputed a proper name. doesn't check all cases
			JOptionPane.showMessageDialog(main_frame,
				    "A players name is not in the correct format!",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE); // gives an error pop up to tell the user the name was inputed incorrectly
			wrongFormat = true; // if this is true, the program will go back to the title screen
		}
		nameOnScreen(panel);

	}
	
	private void playerNameSet(JPanel panel){ // method for setting the names of the players before the game starts
		
		p1.setFont(new Font("Calibri", Font.ITALIC, 25)); // sets the font and size of the labels 
		p2.setFont(new Font("Calibri", Font.ITALIC, 25));
		
		player1 = JOptionPane.showInputDialog("Please enter Player 1's name: "); // pop up window that takes player 1's name
		player2 = JOptionPane.showInputDialog("Please enter Player 2's name: "); //  same for player 2
		Model.player1name = player1;
		Model.player2name = player2;
		p1.setText(player1); // sets the labels text to the text received above
		p2.setText(player2); // same for p2
		
		if ((player1 == null) || (player2 == null) || (p1.getText().equals(p2.getText())) || (p1.getText().length() < 1) || (p2.getText().length() < 1) || (p1.getText().charAt(0) == ' ') || (p2.getText().charAt(0) == ' ')){ // simple error check to see if the user inputed a proper name. doesn't check all cases
			JOptionPane.showMessageDialog(main_frame,
				    "A players name is not in the correct format!",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE); // gives an error pop up to tell the user the name was inputed incorrectly
			wrongFormat = true; // if this is true, the program will go back to the title screen
		}
		nameOnScreen(panel);

	}

	private void nameOnScreen(JPanel panel){
		p1.setBounds(3, 110, 93, 40); // sets the location of the name label
		p1.setHorizontalAlignment(SwingConstants.CENTER); // centers the text
		p2.setBounds(798, 110, 93, 40);
		p2.setHorizontalAlignment(SwingConstants.CENTER);
		p2.setForeground(Color.red); // changes the colour
		p1.setForeground(Color.blue);
		
		panel.add(p1); // adds to the game panel
		panel.add(p2);	
		
		card_layout.show(deck_panel, "GamePanel"); // adds the panel to the deck
		main_frame.repaint(); // repaints to show change
		main_frame.validate(); // checks if properly repainted	
	}
	
	private boolean pieceAir (){ // method to check if a piece has been place floating in dev mode
		for (int x = 0; x < 7; x++){ // runs through every position
			for (int y = 0; y < 5; y++){ // except the bottom row 
				if (Control.coordinates[x][y] == 1 || Control.coordinates[x][y] == -1){ //checks if a piece has been placed there
					if ( Control.coordinates[x][y+1] == 0){ // if there is no piece below that piece
						return true; // the method return true
					}
				}
			}
		}
		return false; // other wise no pieces are floating and method returns false
	}

		
		
		
	
	private boolean win (){ // checks each row, column and diagonal of the board to determine if a winning combination has occurred, outputs true or false
		int x_counter = 0, // used for checking x coordinates
			y_counter = 0; // same for y
		
		while(x_counter != 7){ // runs through the columns
			while(y_counter != 6){ // runs through the rows
				int total_pieces = 0, // a count for the number of same type piece it counts in a row/column/diagonal, if the number reaches 4 then a win has occurred
					piece = Control.coordinates[x_counter][y_counter]; // used for determining if a piece is in a location and if so the colour of that piece 
				
				
				if (piece == 1 || piece == -1){ // piece will be in a -1 state, if the board coordinate is red, 0 state if there's no piece, and 1 if the piece is blue 
					if (x_counter <= 3){ // checks if a horizontal win is still possible, no win to the right can occur in a row if x location is greater than 3
						for(int i = 0; i < 4; i++){ // runs 4 times to check if there is a win
							if(piece == Control.coordinates[x_counter+i][y_counter]) total_pieces++; // checks if the position to the right of the current one is the same colour (if one exists), if so add 1 to the total pieces count
							else break; // if a win has not occurred the loop breaks
						}
					}
					if (total_pieces == 4) { // if a win has occurred output true
						for (int i = 0; i < 4; i++){
							winpoints[i][0] = x_counter + i;
							winpoints[i][1] = y_counter;
						}
						return true;
					}
					total_pieces = 0; // reset the piece count to 0 
					if (y_counter <= 2) { // checks for vertical wins, only runs 3 times because a win cannot occur if y is below 2
						for(int i = 0; i < 4; i++) { // runs 4 times to check if a win has occurred
							if(piece == Control.coordinates[x_counter][y_counter+i])total_pieces++; // same as above but changing y coordinate
							else break; // break if not a win
						}
					}
					if (total_pieces == 4) { // if a win has occurred 
						for (int i = 0; i < 4; i++){
							winpoints[i][0] = x_counter;
							winpoints[i][1] = y_counter + i;
						}
						return true;
					}
					total_pieces = 0; // resets count
					if (y_counter <= 2 && x_counter >= 3){ // checking for diagonal wins starting from the bottom left corner of the board
						for(int i = 0; i < 4; i++){ // runs 4 times as always
							if(piece == Control.coordinates[x_counter-i][y_counter+i])total_pieces++; // checks each position to the top right of the current
							else break; // breaks otherwise
						}
					}
					if (total_pieces == 4) { // if a win has occurred
						for (int i = 0; i < 4; i++){
							winpoints[i][0] = x_counter - i;
							winpoints[i][1] = y_counter + i;
						}
						return true;
					}
					total_pieces = 0; // reset count
					if (y_counter <= 2 && x_counter <= 3){ // checks diagonal wins starting from the top left position
						for(int i = 0; i < 4; i++){
							if(piece == Control.coordinates[x_counter+i][y_counter+i])total_pieces++; // moves left diagonally checking each position
							else break; 
						}
					}
				}
				if (total_pieces == 4) { // checks for win
					for (int i = 0; i < 4; i++){
						winpoints[i][0] = x_counter + i;
						winpoints[i][1] = y_counter + i;
					}
					return true;
				}
				y_counter++; // moves down if none of the current rows had a win
			}
			y_counter = 0; // resets
			x_counter++; // moves right to go through every board piece every time
		}
		return false; // no win has occurred
	}
	
	private boolean noMoreMoves (){ // A method to check if there are no more winning moves are possible

		int x_counter = 0, 
			y_counter = 5,
			space_counter = 0,
			total_counter = 0;

		while(x_counter != 7){ // runs through the columns
			while(y_counter != -1){ // runs through the rows
				int piece = Control.coordinates[x_counter][y_counter]; // determines where the piece is located
					
					/* The below lines of code check different cases for determining if there are no more wins available to either player.
					 * The three cases are both diagonals and horizontal therefore checking all possible combinations every time a player
					 * places a piece
					*/
					try{
						for(int i = 1; i < 4; i++){
							int check_piece = Control.coordinates[x_counter-i][y_counter];
							if( check_piece == piece || check_piece == 0){
								space_counter ++;
							}else break;
						}
					}catch(Exception ee){}
					finally{
						try{
							for(int i = 1; i < 4; i++){
								int check_piece = Control.coordinates[x_counter+i][y_counter];
								if( check_piece == piece || check_piece == 0){
									space_counter ++;
								}else break;
							}
						}catch(Exception e){}
						finally{
							if(space_counter >= 3) {
								total_counter++;
							}
						}
					}
					
					space_counter = 0;
					try{
						for(int i = 1; i < 4; i++){
							int check_piece = Control.coordinates[x_counter-i][y_counter-i];
							if( check_piece == piece || check_piece == 0){
								space_counter ++;
							}else break;
						}
					}catch(Exception ee){}
					finally{
						try{
							for(int i = 1; i < 4; i++){
								int check_piece = Control.coordinates[x_counter+i][y_counter+i];
								if( check_piece == piece || check_piece == 0){
									space_counter ++;
								}else break;
							}
						}catch(Exception e){}
						finally{
							if(space_counter >= 3) {
								total_counter++;
							}
						}
					}

					space_counter = 0;
					try{
						for(int i = 1; i < 4; i++){
							int check_piece = Control.coordinates[x_counter-i][y_counter+i];
							if( check_piece == piece || check_piece == 0){
								space_counter ++;
							}else break;
						}
					}catch(Exception ee){}
					finally{
						try{
							for(int i = 1; i < 4; i++){
								int check_piece = Control.coordinates[x_counter+i][y_counter-i];
								if( check_piece == piece || check_piece == 0){
									space_counter ++;
								}else break;
							}
						}catch(Exception e){}
						finally{
							if(space_counter >= 3) {
								total_counter++;
							}
						}
					}
					
					space_counter = 0;
					y_counter--; // moves down if none of the current rows had a win
				}
				y_counter = 0; // resets
				x_counter++; // moves right to go through every board piece every time
			}
		if(total_counter == 0){ // returns true if no more moves are possible
			return true;
		}
		return false;
	}
	
	private void saveGame(String savename, String player1, String player2) throws Exception{ // method for saving the users current game
		int x_counter = 0, // used for checking x coordinates
				y_counter = 0; // same for y
			PrintWriter printer = new PrintWriter("Saves/"+savename+".connectfoursavefile"); // gets the file name from the user 
			printer.println(mouseClick); // adds the whos turn it is
			printer.println("Player 1:"+player1); // adds the players names
			printer.println("Player 2:"+player2); // same 
			while(y_counter != 6){ // runs through the columns
				while(x_counter != 7){ // runs through the rows
					int piece = Control.coordinates[x_counter][y_counter]; // used for determining if a piece is in a location and if so the colour of that piece 
					printer.write(piece+" "); // used to represent the boards current state
					x_counter++; // moves down if none of the current rows had a win
				}
				printer.println();
				x_counter = 0; // resets
				y_counter++; // moves right to go through every board piece every time
			}
			printer.close(); // closes the file
	}
	
	private int[][] loadGame(BufferedReader read)throws Exception{ // method used in load a game that the users have previously stored
		int [][] cood = new int [7][6]; // makes a coordinates board
		String s = read.readLine(); // reads each line of the save file
		int y_count = 0;
		while(s != null){ // runs through the whole file
			String[] getpieces = s.split(" "); // splits at space to determine coordinates
			for (int x = 0; x < getpieces.length; x++){ // this for loop populates the coordinates array with the proper values to represent the current board state
				cood[x][y_count] = Integer.parseInt(getpieces[x]);
				if(Integer.parseInt(getpieces[x]) == 1 || Integer.parseInt(getpieces[x]) == -1)
					View.check_disk[x][y_count] = true;
				else
					View.check_disk[x][y_count] = false;
			}
			y_count++;
			s = read.readLine();
		}
		return(cood); // returns the array
	}

    
	
	void buttonFunction(ActionEvent e, JPanel panel, boolean dev_mode, boolean single_player) throws Exception{ // the method determines the actions of each click on each button throughout the code
		if (e.getActionCommand().equals("Instructions")){ // if instructions is clicked on the title screen, go to infopanel
			infoScreen();
			card_layout.show(deck_panel, "InfoPanel");
		}
		else if (e.getActionCommand().equals("Exit")){ // if exit is clicked throughout the program, ask the user first, then close
			int exit = JOptionPane.showConfirmDialog(main_frame, "Are you sure you want to close?", "", 0); // checks to see if the user really wants to close the window
			if (exit == 0){ System.exit (0); }// if so the window closes
		}
		else if (e.getActionCommand().equals("Main Menu")){ // if user want to return to the main menu
			
			int exit = JOptionPane.showConfirmDialog(main_frame, "Are you sure you would like to go the the Main Menu? If you are currently playing a game please safe first before exiting.", "", 0); // checks to see if the user really wants to close the window
			if (exit == 0){
				card_layout.show(deck_panel, "TitlePanel");		
				for (int iy = 0; iy < 6; iy++){
					for (int ix = 0; ix < 7; ix++){
						Control.coordinates[ix][iy] = 0;
						View.check_disk[ix][iy] = false;
					}
				}
			}
		}	
		else if(e.getActionCommand().equals("Save Game")){ // when the user wants to save the game
			String savename = JOptionPane.showInputDialog("Enter what file you want to save it in: ");  // gets the file name
			saveGame(savename, Control.player1name, Control.player2name); // saves it
		}
		else if (e.getActionCommand().equals("Resume Game") ){ // resume game button on the info panel to return the player to the current game
			card_layout.show(deck_panel, "GamePanel"); 
		}
		else if (e.getActionCommand().equals("Load Game")){ // when the user wants to load a game
			File[] listgames = new File ("Saves").listFiles(); // gets a list of the current saved files
			try{
				String gamepicked = JOptionPane.showInputDialog(null, "Please choose a game to load.", // allows the user to pick which file they want the load
																"Load Game", JOptionPane.DEFAULT_OPTION,
																null, listgames, listgames[0].toString()).toString();
				BufferedReader read = new BufferedReader(new FileReader(gamepicked)); //  determines the file the user chose to load
				mouseClick = Integer.parseInt(read.readLine()); // reads the move / whose turn it is
				String [] player_1 = read.readLine().split(":"); // splits the names string and loads each name for each player
				player1 = player_1[1];
				String [] player_2 = read.readLine().split(":");
				player2 = player_2[1];
				player1name = player1; // saves the names
				player2name = player2;
				
				Control.coordinates = loadGame(read); // reads the actual board
				
				gameScreen(); // calls the game screen
				p1.setFont(new Font("Calibri", Font.ITALIC, 25)); // sets the font and size of the labels 
				p2.setFont(new Font("Calibri", Font.ITALIC, 25));
				p1.setText(player1); // sets the labels text to the text received above
				p2.setText(player2); // same for p2
				nameOnScreen(game_panel);
				
				for (int iy = 0; iy < 6; iy++){ // sets all the pieces in place
					for (int ix = 0; ix < 7; ix++){
						if (Control.coordinates[ix][iy] == 1){
							blueDisk((ix)*99, (iy)*95, game_panel, false);
						}
						else if (Control.coordinates[ix][iy] == -1){
							redDisk((ix)*99, (iy)*95, game_panel, false);
						}
					}
				}
				if (mouseClick % 2 == 0){ // tell the users who is playing first
					p1.setOpaque(true); // sets label background
					p1.setBackground(new Color (0, 0, 0, 100));
					JOptionPane.showMessageDialog(main_frame, // shows whose turn it is currently
							"It's " + p1.getText() + "'s turn, please place a piece.",
							"Current Player",
							JOptionPane.WARNING_MESSAGE);
				}

				else{
					p2.setOpaque(true); // sets label background
					p2.setBackground(new Color (0, 0, 0, 100));
					JOptionPane.showMessageDialog(main_frame, // same for player 2
							"It's " + p2.getText() + "'s turn, please place a piece.",
							"Current Player",
							JOptionPane.WARNING_MESSAGE);
				}

			}catch(Exception ee){} // Nothing happens if the user decides not to load a file
				
		}
		else if(e.getActionCommand().equals("Single Player")){
			gameScreen();
			panel = game_panel;
			Model.single_player = true;
			singlePlayerNameSet(panel);
			if (wrongFormat == false){ // checks if the formatting of the name is correct
				for (int ix = 0; ix < 7; ix++){ // if so the board is set up
					for (int iy = 0; iy < 6; iy++){
						check_disk[ix][iy] = false;
						Control.coordinates[ix][iy] = 0;
					}
				}
				card_layout.show(deck_panel, "GamePanel"); // shows the game panel
				possibleMoves player3 = new possibleMoves(panel);

				if (mouseClick % 2 == 0){ // determines which player is going to move first
					p1.setOpaque(true); // sets label background
					p1.setBackground(new Color (0, 0, 0, 100));
					JOptionPane.showMessageDialog(main_frame,
							"Random selection has chosen " + player1 + " to move first. After this the turns will alternate.",
							"First Move",
							JOptionPane.WARNING_MESSAGE); // tells the players who is going to move first 


				}

				else{ // if player 1 is not moving first
					p2.setOpaque(true); // sets label background
					p2.setBackground(new Color (0, 0, 0, 100));
					JOptionPane.showMessageDialog(main_frame,
							"Random selection has chosen the computer to move first. After this the turns will alternate.",
							"First Move",
							JOptionPane.WARNING_MESSAGE);
							player3.AI();

				}	
			}
			
			else{ // if the names are in the wrong format return to main menu
				card_layout.show(deck_panel, "TitlePanel");
			}	

		}
		else if (e.getActionCommand().equals("Start Game") || // if the user wants to play a standard game with dev mode
				 e.getActionCommand().equals("New Game") ||
				 e.getActionCommand().equals("Resume Game")){
				gameScreen(); // calls the game
				panel = game_panel;
				playerNameSet(panel); // asks player for there names

				if (wrongFormat == false){ // checks if the formatting of the name is correct
					for (int ix = 0; ix < 7; ix++){ // if so the board is set up
						for (int iy = 0; iy < 6; iy++){
							check_disk[ix][iy] = false;
							Control.coordinates[ix][iy] = 0;
						}
					}
					card_layout.show(deck_panel, "GamePanel"); // shows the game panel
					
					if (mouseClick % 2 == 0){ // determines which player is going to move first
						p1.setOpaque(true); // sets label background
						p1.setBackground(new Color (0, 0, 0, 100));
						JOptionPane.showMessageDialog(main_frame,
								"Random selection has chosen " + player1 + " to move first. After this the turns will alternate.",
								"First Move",
								JOptionPane.WARNING_MESSAGE); // tells the players who is going to move first 
					}

					else{ // if player 1 is not moving first
						p2.setOpaque(true); // sets label background
						p2.setBackground(new Color (0, 0, 0, 100));
						JOptionPane.showMessageDialog(main_frame,
								"Random selection has chosen " + player2 + " to move first. After this the turns will alternate.",
								"First Move",
								JOptionPane.WARNING_MESSAGE);
					}	
				}
				
				else{ // if the names are in the wrong format return to main menu
					card_layout.show(deck_panel, "TitlePanel");
				}			
		}
		
		else if (e.getActionCommand().equals("Developer Mode") ||
				 e.getActionCommand().equals("Reset")){ // check if the user want to use dev mode
				developerScreen();
				Model.dev_mode = true; // set to true so the system know the user is in dev mode
				Control.redcount = 0;
				Control.bluecount = 0;
				for (int x = 0; x < 7; x++){ // creates the board as normal
					for (int y = 0; y < 6; y++){
						Control.coordinates[x][y] = 0;
						check_disk[x][y] = false;
					}
				}
				card_layout.show(deck_panel, "DeveloperPanel");
		}
		else if (e.getActionCommand().equals("Select Blue")){ // while in dev mode checks if user want to play blue disks (will place blue while selected)
			Control.mouseClick = 0;
		}
		else if (e.getActionCommand().equals("Select Red")){ // same for red disk
			Control.mouseClick = 1;
		}
		else if (e.getActionCommand().equals("Start Game ")){ // in dev mode if user wants to start a game from the current state
			boolean piece_air = pieceAir(), // checks if piece is in air
					win_check = win();
			int piece_diff = Math.abs(Control.bluecount - Control.redcount); // checks if the count of the piece is correct
			if (piece_diff > 1 && piece_air == true && win_check == true){ // if all errors occur the program will list them
				JOptionPane.showMessageDialog(main_frame,
					    "                    ERROR"+
				        "\n1) Too many of one colour \n2) A piece is in the air \n3) One of the players has a Connect 4",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if (piece_diff > 1 && piece_air == true){ // colours are uneven and piece are floating
				JOptionPane.showMessageDialog(main_frame,
					    "                    ERROR"+
				        "\n1) Too many of one colour \n2) A piece is in the air ",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if (piece_diff > 1 && win_check == true){ //if colours are uneven and a player has a winning combination
				JOptionPane.showMessageDialog(main_frame,
					    "                    ERROR"+
				        "\n1) Too many of one colour \n2) One of the players has a Connect 4",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if (piece_air == true && win_check == true){ // if piece in air and a player has a winning combination
				JOptionPane.showMessageDialog(main_frame,
					    "                    ERROR"+
				        "\n1) A piece is in the air \n2) One of the players has a Connect 4",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if (piece_diff > 1){ // if colours are uneven
				JOptionPane.showMessageDialog(main_frame,
					    "                    ERROR"+
					    "\n1) Too many of one colour",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}

			else if(piece_air == true){ // if piece in air
				JOptionPane.showMessageDialog(main_frame,
					    "                    ERROR"+
					    "\n1) A piece is in the air",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			else if (win_check == true){ // if player has winning combination
				JOptionPane.showMessageDialog(main_frame,
					    "                    ERROR"+
					    "\n1) One of the players has a Connect 4",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if (piece_diff == 1 || piece_diff == 0 || piece_air == false || win_check == false) { // if game state is correct start the game
				gameScreen();
				panel = game_panel;
				playerNameSet(panel); // check names
				
				if (wrongFormat == false){ // check if formats okay
				card_layout.show(deck_panel, "GamePanel"); 
					for (int ix = 0; ix < 7; ix++){
						for (int iy = 0; iy < 6; iy++){
							if (Control.coordinates[ix][iy] == 1){
								blueDisk((ix)*99, (iy)*95, panel, false);
							}
							else if (Control.coordinates[ix][iy] == -1){
								redDisk((ix)*99, (iy)*95, panel, false);
							}
						}
					}
					if (mouseClick % 2 == 0){ // tell the users who is playing first
						p1.setOpaque(true); // sets label background
						p1.setBackground(new Color (0, 0, 0, 50));
						JOptionPane.showMessageDialog(main_frame,
								"Random selection has chosen " + player1 + " to move first. After this the turns will alternate.",
								"First Move",
								JOptionPane.WARNING_MESSAGE);
					}

					else{
						p2.setOpaque(true); // sets label background
						p2.setBackground(new Color (0, 0, 0, 50));
						JOptionPane.showMessageDialog(main_frame,
								"Random selection has chosen " + player2 + " to move first. After this the turns will alternate.",
								"First Move",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				
				else{ // if names are incorrect 
					card_layout.show(deck_panel, "DeveloperPanel"); 
				}				
				
				if(Control.bluecount > Control.redcount) mouseClick = 1; // checks to see who goes first depending on the developer mode state
				else if(Control.bluecount < Control.redcount) mouseClick = 0; // same 
				else { // if the pieces are even in number than whoever has been randomly selected to go first will
					if (mouseClick % 2 == 0) { mouseClick = 0; }
					else { mouseClick = 1; }
					}
				}
			}
		}



	void mouseFunction(MouseEvent e, JPanel panel, boolean dev_model, boolean single_player){ // used for determining location of mouse click
		try{
			int pointX = e.getX(), pointY = e.getY(); // gets the X/Y grid coordinates
			possibleMoves player3 = new possibleMoves(panel);
			if (pointX <= 199  && pointX >= 100)  // the below if statements to see where the mouse click was and return a grid coordinate depending on the click
				setX(0);
			else if (pointX <= 299  && pointX >= 200)
				setX(1);
			else if (pointX <= 399  && pointX >= 300)
				setX(2);
			else if (pointX <= 499  && pointX >= 400)
				setX(3);
			else if (pointX <= 599  && pointX >= 500)
				setX(4);
			else if (pointX <= 699  && pointX >= 600)
				setX(5);
			else if (pointX <= 799  && pointX >= 700)
				setX(6);
			else
				setX(-5);

			if (pointY <= 99  && pointY >= 0)
				setY(0);
			else if (pointY <= 199  && pointY >= 100)
				setY(1);
			else if (pointY <= 299  && pointY >= 200)
				setY(2);
			else if (pointY <= 399  && pointY >= 300)
				setY(3);
			else if (pointY <= 499  && pointY >= 400)
				setY(4);
			else if (pointY <= 599  && pointY >= 500)
				setY(5);
			else
				setY(-5);
			
			if (Model.single_player == true){
				if(Model.check_disk[Disk.getX()][Disk.getY()] != true){ // makes sure a disk is not in the position
					Model.check_disk[Disk.getX()][Disk.getY()] = true;

					for(int i = Disk.getY()+1; i < 6; i++){ // checking to see if a disk is in the position
						if (Model.check_disk[Disk.getX()][i] == false){
							Model.check_disk[Disk.getX()][Disk.getY()] = false;
							setY(i);
							Model.check_disk[Disk.getX()][Disk.getY()] = true;
						}
						else{ // breaks if a disk is already there
							break;
						}
					
					}
						p1.setOpaque(false); // below three lines highlight which players turn it is
						p2.setOpaque(true);
						p2.setBackground(new Color (0, 0, 0, 100));
						blueDisk((Disk.getX())*99, (Disk.getY())*95, panel, false);
						if (win() == true){ // when a player has connect 4

							for (int j = 0; j < 4; j++){
									winner = player1name;
									blueDisk(winpoints[j][0]*99, winpoints[j][1]*95, panel, true);

								}
							JOptionPane.showMessageDialog(main_frame,  winner + " has connected 4 and won the game!"); // shows the winner
							card_layout.show(deck_panel, "TitlePanel");
							for (int iy = 0; iy < 6; iy++){
								for (int ix = 0; ix < 7; ix++){
									Control.coordinates[ix][iy] = 0;
									View.check_disk[ix][iy] = false;
								}
							}
							card_layout.removeLayoutComponent(panel);
						}
						
						p2.setOpaque(false); //after the user places his piece the AI takes over and will automaticaly place one right away then wait for user to click again
						p1.setOpaque(true);
						p1.setBackground(new Color (0, 0, 0, 100));
						player3.AI();
						if (win() == true){ // when a player has connect 4
							for (int n = 0; n < 4; n++){

									redDisk(winpoints[n][0]*99, winpoints[n][1]*95, panel, true);
									winner = "YOU LOSE";
									}
							JOptionPane.showMessageDialog(main_frame,  winner ); // shows the winner
							card_layout.show(deck_panel, "TitlePanel");
							for (int iy = 0; iy < 6; iy++){
								for (int ix = 0; ix < 7; ix++){
									Control.coordinates[ix][iy] = 0;
									View.check_disk[ix][iy] = false;
								}
							}
							card_layout.removeLayoutComponent(panel);
						}
					
				}

				if(noMoreMoves() == true){ // checking each time if the game is a draw
					JOptionPane.showMessageDialog(main_frame, "No more winning combinations can be made. The game is a draw!");
					card_layout.show(deck_panel, "TitlePanel");
					card_layout.removeLayoutComponent(panel);
					
				}
			}
			else if (Model.dev_mode == false&&single_player == false){ // if the game is not in dev mode
				if(Model.check_disk[Disk.getX()][Disk.getY()] != true){ // makes sure a disk is not in the position
					Model.check_disk[Disk.getX()][Disk.getY()] = true;

					for(int i = Disk.getY()+1; i < 6; i++){ // checking to see if a disk is in the position
						if (Model.check_disk[Disk.getX()][i] == false){
							Model.check_disk[Disk.getX()][Disk.getY()] = false;
							setY(i);
							Model.check_disk[Disk.getX()][Disk.getY()] = true;
						}
						else{ // breaks if a disk is already there
							break;
						}
					}
					if(mouseClick%2==0){ // places the actual disk depending on who's turn it is
						p1.setOpaque(false); // below three lines highlight which players turn it is
						p2.setOpaque(true);
						p2.setBackground(new Color (0, 0, 0, 100));
						blueDisk((Disk.getX())*99, (Disk.getY())*95, panel, false);
						winner = player1name;
					}
					else{
						p2.setOpaque(false); // below three lines highlight which players turn it is
						p1.setOpaque(true);
						p1.setBackground(new Color (0, 0, 0, 100));
						redDisk((Disk.getX())*99, (Disk.getY())*95, panel, false);
						winner = player2name;
					}
					mouseClick++; // increments mouse click
				}
				
				if(noMoreMoves() == true){ // checking each time if the game is a draw
					JOptionPane.showMessageDialog(main_frame, "No more winning combinations can be made. The game is a draw!");
					card_layout.show(deck_panel, "TitlePanel");
					card_layout.removeLayoutComponent(panel);
					
				}
				
				if (win() == true){ // when a player has connect 4
					mouseClick--;
					for (int i = 0; i < 4; i++){
						if(mouseClick%2==0) // the below two statements are checking for who the actual winner is
							blueDisk(winpoints[i][0]*99, winpoints[i][1]*95, panel, true);
						else
							redDisk(winpoints[i][0]*99, winpoints[i][1]*95, panel, true);
					}
					JOptionPane.showMessageDialog(main_frame,  winner + " has connected 4 and won the game!"); // shows the winner
					card_layout.show(deck_panel, "TitlePanel");
					for (int iy = 0; iy < 6; iy++){
						for (int ix = 0; ix < 7; ix++){
							Control.coordinates[ix][iy] = 0;
							View.check_disk[ix][iy] = false;
						}
					}
					card_layout.removeLayoutComponent(panel);
				}
			}
			else if(dev_model == true&& single_player==false){ // if user is in dev mode, the same happens with different properties (disks don't fall)
				if(Model.check_disk[Disk.getX()][Disk.getY()] != true){
					Model.check_disk[Disk.getX()][Disk.getY()] = true;
						
					if(mouseClick == 0){
						blueDisk((Disk.getX())*99, (Disk.getY())*95, panel, false);
						Control.bluecount++;
					}
					else if (mouseClick == 1){
						redDisk((Disk.getX())*99, (Disk.getY())*95, panel, false);
						Control.redcount++;
					}
				}
			}
		}
		catch(Exception error){ // catch if there's an exception
		}
	}
}