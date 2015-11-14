// COMP SCI 2ME3 

// Assignment 1
// Hassaan Malik (1224997)
// Katrine Rachitsky (1306314)
// Trevor Rae (1324949)
// Navleen Signh (1302228)
// Paul Warnick (1300963)

import java.awt.CardLayout; // java imports for everything JFrame related
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Model extends Disk implements ActionListener, MouseListener { // Model class that sets up the JFrame as a window to play connect four in
	
	private Dimension screen = Toolkit.getDefaultToolkit ().getScreenSize (); // used for finding the dimensions of the users screen
	private int screen_width = screen.width, screen_height = screen.height; // creates ints for the width and the height

	public JPanel panel; // used in the creation of each panel
	static boolean dev_mode; // used to determine if the game has to play in developer move or not
	static boolean single_player; 
	


	
	static String player1name, player2name;
	static JLabel blueDisk[][] = new JLabel [7][6], redDisk[][] = new JLabel [7][6];
	static CardLayout card_layout  = new CardLayout(); // creates a new type CardLayout to determine how the panels are shown
	static JPanel deck_panel = new JPanel();//,  // main panel in which all other panels are displayed
	final static JFrame main_frame = new JFrame("Connect Four"); // makes a new JFrame type named "Connect Four" (This will be the name of the window)

	static boolean [][] check_disk = new boolean [7][6]; // creates an array to determine if a disk is in a board coordinate
	
	Model(){ // intializes model so it can be used through the class without being static
	}
	
	Model(JPanel board_panel, boolean developermode, boolean singleplayer){ // used so the program can follow an MVC style 
		this.panel = board_panel; // allows it to be used
		Model.dev_mode = developermode;
		Model.single_player = singleplayer; 
	}
	
	private void createFrame (JFrame main_frame){ // method to create the actual window with the specified dimensions
		main_frame.setResizable(false); // disables the ability to maximize or change the dimensions of the window
		main_frame.setLocation((screen_width/2) - (900/2), (screen_height/2) - (600/2)); // sets the location to 1/4 the width of the screen and 1/6 the height to center the window
		main_frame.setSize (900 , 600); // sets the size of the window
	}
	
	public static void main(String [] args) throws IOException{ // calls all methods to create the JFrame
		
		for(int x = 0; x < 7; x++){
			for(int y = 0; y < 6; y++){
				blueDisk[x][y] = new JLabel();
				redDisk[x][y] = new JLabel();
			}
		}
		
		Model model_call = new Model(); // allows model to be called in main (static function) without being static
		View view_call = new View(); // same as above 

		model_call.createFrame(main_frame); // creates a JFrame of the dimensions specified in createFrame
		
		deck_panel.setLayout(card_layout); // sets the layout style of our window to Java's "Card Layout"

		view_call.titleScreen(); // call the title screen panel to display 
		
		card_layout.show (deck_panel,"TitlePanel"); // shows the first panel 
		
		main_frame.add(deck_panel); // adds the deck panel to the main frame
		
		main_frame.setVisible(true); // shows the screen to the user		
		main_frame.addWindowListener(new WindowAdapter(){ // checks to see if the window controls (top right) are clicked
			public void windowClosing (WindowEvent WE){  // checks if the "X" button is pressed
				System.exit (0);// if so the window closes
		      }});		
	}

	@Override
	public void mouseClicked(MouseEvent e) { // the below over ride functions are needed for java's mouse click
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) { // method to run when the mouse click is released
		Control call_control = new Control(); // calls the control method which uses view to create the game
		call_control.mouseFunction(e, panel, dev_mode,single_player); // ensure the mouse listeneres and all mouse functions will run when the game is starting
	}

	@Override
	public void actionPerformed(ActionEvent e) { // runs if a buttons is used 
		Control call_control = new Control(); // used in calling the windows
		try { // try just in case so no runtime errors occur
			call_control.buttonFunction(e, panel, dev_mode,single_player); // calls control and starts the game
		} 
		
		catch (Exception e1) { // catch an error
		}
	}
}