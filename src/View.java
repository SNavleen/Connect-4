// COMP SCI 2ME3 

// Assignment 1
// Hassaan Malik (1224997)
// Katrine Rachitsky (1306314)
// Trevor Rae (1324949)
// Navleen Signh (1302228)
// Paul Warnick (1300963)

import java.awt.Color; // jave imports for creating panels in a JFrame
import java.awt.Graphics;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class View extends Model{ // View class to create everything the user sees by using panels
	
	JPanel game_panel; // the JPanel for the game screen
	private boolean developer_mode = false; // bool for checking if the user wants to play in dev mode
	private boolean single_player2 = false;
	@SuppressWarnings("serial")
	void titleScreen () throws IOException{ // panel for the start screen (main menu with start, instructions and exit buttons)
		final ImageIcon image = new ImageIcon (getClass().getResource("/StartScreen.png")); // loads in the start screen image      
		JPanel title_panel = new JPanel() { // creates the title panel with proper sizes and the proper background image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        
		title_panel.setLayout(null); // sets the layout to null so certain buttons can have a set bounds instead of the java default
		
		JButton start_game = new JButton("Start Game"), // buttons for title screen
				single_player = new JButton("Single Player"), 

				instructions_title = new JButton("Instructions"), 
				exit_title = new JButton("Exit"),
				developer = new JButton ("Developer Mode"),
				loadgame = new JButton ("Load Game");
		
		start_game.addActionListener(new Model(game_panel, developer_mode, single_player2)); // listers for all the buttons, checking if the user clicks a certain button
		single_player.addActionListener(new Model(game_panel, developer_mode, single_player2));
		instructions_title.addActionListener(new Control());
		developer.addActionListener(new Model(game_panel, developer_mode,single_player2));
		exit_title.addActionListener(new Model());
		loadgame.addActionListener(new Model(game_panel, developer_mode,single_player2));
		
		 start_game.setBounds(43, 500, 125, 40); // sets where the button goes as well as the height and width (on the start screen all are located in a line on the bottom of the screen)
		  single_player.setBounds(183, 500, 130, 40);
		  loadgame.setBounds(323, 500, 125, 40);
		  instructions_title.setBounds(458, 500, 125, 40);
		  developer.setBounds(593, 500, 125, 40);
		  exit_title.setBounds(728, 500, 125, 40);
		
		title_panel.add(start_game); // puts the button on the screen
		title_panel.add(single_player); // puts the button on the screen
		title_panel.add(instructions_title);	
		title_panel.add(developer);
		title_panel.add(exit_title);
		title_panel.add(loadgame);
		
		deck_panel.add(title_panel, "TitlePanel"); // adds the panel to the deck of panels
	}
	
	@SuppressWarnings("serial")
	void infoScreen () throws IOException{ // info panel that has a list of instructions on how to play connect four
		JButton game_resume = new JButton("Resume Game"); // a button used in the instruction panel
		final ImageIcon image = new ImageIcon(getClass().getResource("/Instructions.png")); // load in the image that has the instructions on it
		JPanel info_panel = new JPanel() { // creates the panel itself with proper size and background image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        
		info_panel.setLayout(null); // sets to null so setBounds can be used properly
		
		JButton back_menu = new JButton("Main Menu"); // creates a button for returning to the main menu
		
		back_menu.addActionListener(new Model()); // add a lister for it ^
		game_resume.addActionListener(new Model(info_panel, developer_mode,single_player2)); // lister for resume game 
		
		back_menu.setBounds(900-175 , 25, 150, 40); // sets the positions
		game_resume.setBounds(25 , 25, 150, 40);

		info_panel.add(back_menu); // adds the buttons to the panel
		info_panel.add(game_resume);

		deck_panel.add(info_panel, "InfoPanel"); // adds to deck of panels
	}
	
	@SuppressWarnings("serial")
	void gameScreen () throws IOException{ // panel for the actual game screen
		final ImageIcon image = new ImageIcon(getClass().getResource("/gameScreen.png")); // image for the yellow 7 x 6 board itself + beautiful (jake) har(d)wood finish
		final ImageIcon blueimage = new ImageIcon(getClass().getResource("/Bluedisk.png")); // loads in the picture for the blue disk
		final ImageIcon redimage = new ImageIcon(getClass().getResource("/Reddisk.png")); // same for the red disk
		
		game_panel = new JPanel() { // creates the panel with proper size and image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        
		game_panel.setLayout(null); // same as above for button bounds
		
		JButton mainmenu = new JButton ("Main Menu"), // buttons for game board
				exit = new JButton("Exit"),
				instructions_game = new JButton ("Instructions"),
				save = new JButton("Save Game");

		JLabel blueDisk = new JLabel(blueimage), // the disks are simply labels with pictures on top of them (here a label is created for each type of disk)
			   redDisk = new JLabel (redimage),
			   playerVSplayermode = new JLabel ("PvP Mode"); // a label in the bottom left corner to show that the user is in player vs player mode
		
		mainmenu.addActionListener(new Model()); // adds listeners for the buttons
		instructions_game.addActionListener(new Model());
		exit.addActionListener(new Model());
		save.addActionListener(new Model());
		
		mainmenu.setBounds(798, 380, 93, 40); // sets the locations of the buttons
		mainmenu.setMargin(new Insets(0,0,0,0)); // limits the amount of "padding" on the buttons so the text of the button does not get cut off
		instructions_game.setBounds(798, 440, 93, 40);
		instructions_game.setMargin(new Insets(0,0,0,0));
		exit.setBounds(798, 500, 93, 40);
		exit.setMargin(new Insets(0,0,0,0));
		save.setBounds(798, 320, 93, 40);
		save.setMargin(new Insets(0,0,0,0));
		
		blueDisk.setBounds(0, 0, 95, 99); // sets the location of the blue and red disk in the top right/left of the screen
		redDisk.setBounds(798, 0, 95, 99);
		playerVSplayermode.setBounds(1, 520, 96, 40);
		playerVSplayermode.setHorizontalAlignment(SwingConstants.CENTER); // centers the text of the PvP label (defaulted to LEFT)
		playerVSplayermode.setOpaque(true); // for esthetics 
		playerVSplayermode.setForeground(new Color (200, 0, 0)); // colours
		playerVSplayermode.setBackground(new Color (0, 0, 0, 50));
		
		game_panel.add(mainmenu); // adds all buttons and labels to the panel
		game_panel.add(instructions_game);
		game_panel.add(exit);
		game_panel.add(save);
		game_panel.add(blueDisk);
		game_panel.add(redDisk);
		game_panel.add(playerVSplayermode);
		
		game_panel.addMouseListener(new Model(game_panel, developer_mode,single_player2)); // uses model and implements the mouse listeners
		
		deck_panel.add(game_panel, "GamePanel"); // adds the game panel to the deck panel
	}
	
	@SuppressWarnings("serial")
	void developerScreen () throws IOException{ // panel for dev mode that sets up the board in a slight different way than a normal game
		final ImageIcon image = new ImageIcon(getClass().getResource("/gameScreen.png")); // image for the yellow 7 x 6 board itself
		final ImageIcon blueimage = new ImageIcon(getClass().getResource("/Bluedisk.png")); // loads in the picture for the blue disk
		final ImageIcon redimage = new ImageIcon(getClass().getResource("/Reddisk.png")); // same for the red disk
		
		JPanel dev_panel = new JPanel() { // panel and image are set
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        
		dev_panel.setLayout(null); // same as above 3 methods
		
		JButton redbutton = new JButton ("Select Red"), // a few buttons are added for dev mode, the choice to reset the current pieces and the choice to leave dev mode to start playing an actual game
				bluebutton = new JButton ("Select Blue"),
				startbutton = new JButton ("Start Game "),
				reset = new JButton ("Reset"),
				mainmenu = new JButton ("Main Menu");

		JLabel blueDisk = new JLabel(blueimage), // images for the disks
			   redDisk = new JLabel (redimage),
			   devmode = new JLabel ("Developer Mode"); // a label in the bottom left corner to show that the user is in dev mode
		
		redbutton.addActionListener(new Model()); // listeners for all buttons
		bluebutton.addActionListener(new Model());
		startbutton.addActionListener(new Model());
		reset.addActionListener(new Model());
		mainmenu.addActionListener(new Model());
		
		redbutton.setBounds(798, 110, 93, 40); // bounds locations + padding reduction
		redbutton.setMargin(new Insets(0,0,0,0));
		bluebutton.setBounds(3, 110, 93, 40);
		bluebutton.setMargin(new Insets(0,0,0,0));
		startbutton.setBounds(798, 380, 93, 40);
		startbutton.setMargin(new Insets(0,0,0,0));
		reset.setBounds(798, 440, 93, 40);
		mainmenu.setBounds(798, 500, 93, 40);
		mainmenu.setMargin(new Insets(0,0,0,0));
		blueDisk.setBounds(0, 0, 95, 99);
		redDisk.setBounds(798, 0, 95, 99);
		devmode.setBounds(1, 520, 96, 40);
		devmode.setHorizontalAlignment(SwingConstants.CENTER); // Centers the text of the dev mode label (defaulted to LEFT)
		devmode.setOpaque(true); // for esthetics 
		devmode.setForeground(new Color (200, 0, 0)); // colours
		devmode.setBackground(new Color (0, 0, 0, 50));
		
		dev_panel.add(redbutton); // adds all the buttons
		dev_panel.add(bluebutton);
		dev_panel.add(startbutton);
		dev_panel.add(reset);
		dev_panel.add(mainmenu);
		dev_panel.add(blueDisk);
		dev_panel.add(redDisk);
		dev_panel.add(devmode);
		
		dev_panel.addMouseListener(new Model(dev_panel, developer_mode,single_player2)); // uses models listener methods
		
		deck_panel.add(dev_panel, "DeveloperPanel"); // adds to deck panel
	}
}