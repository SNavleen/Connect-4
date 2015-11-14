// COMP SCI 2ME3 

// Assignment 1
// Hassaan Malik (1224997)
// Katrine Rachitsky (1306314)
// Trevor Rae (1324949)
// Navleen Signh (1302228)
// Paul Warnick (1300963)

public class Disk { // ADT for each disk in the connect four board
	
	private static int x; // x coordinate
	private static int y; // y coordinate

	public Disk (){ } // default constructor if no arguments are entered
	
	@SuppressWarnings("static-access") // the suppressions below stop warnings
	public Disk(int x, int y){ // constructor using given arguments
		this.x = x;
		this.y = y;
	}
	
	@SuppressWarnings("static-access")
	public void setX (int x){ // sets the value of x 
		this.x = x;
	}
	
	@SuppressWarnings("static-access")
	public void setY (int y){ // sets the value of y
		this.y = y;
	}
	
	public static int getX (){ // returns the x coordinate
		return (x);
	}
	
	public static int getY (){ // returns the y coordinate
		return (y);
	}	
}