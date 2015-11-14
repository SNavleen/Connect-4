import java.io.IOException;
import javax.swing.JPanel;

public class possibleMoves{
	private JPanel panel;

	//constructor takes in the JPanel from control and makes it a global so we can write to it throughout the class
	public possibleMoves(JPanel panel) {
		this.panel = panel;
	}
	//the AI method
	public void AI() throws IOException {
		int boardarray[][] = new int [6][7];//makes the array that needs to be resized because the original array that we worked with in 2 player is a 7x6
		int c[] = new int [7];//this is just an array that holds a variables representing how full each column is
		int switcher[][] = Control.getCoordinates();//takes in what the board currently holds


		for (int x=0;x<6;x++){
			for (int y = 0; y<7; y++){
				boardarray[Math.abs(x-5)][y] = switcher[y][Math.abs(x-5)];//rearranges the values in the array to form a 6x7
			}
		}

		int counter = 0;
		for(int i =0; i <7;i++){
			for(int j=0;j<6;j++){
				if (boardarray[j][i]!=1 &&boardarray[j][i]!=-1){//this nested loop sets the column array index value to the current highest piece in the column
					counter++;
					c[i]=5-j;
				}
			}
			if(counter ==0){
				c[i]=6;
				counter = 0;
			}
			else{
				counter=0;
			}
			}


		int redToken = -1;//sets to the value of the red tokens on the board
		int blueToken = 1;//sets to the value of the blue tokens on the board

		int[] h= new int[7];
		for (int i = 0; i < 7; i++) h[i]=0;//create an array to hold each of the best possible moves for each column


		for (int i=0; i<7; i++)
		{
			if (c[i]==6) h[i]=0; else{//if the column is not full then set col = to the counter and row equals to the reverse of its current position
				int col = i;
				int row = 5-c[i];
				//the below code is a very repititive way of checking and assigning a value based on how good each possibile move is, it does this by looking at each possible move and assesing
				//the surrounding pieces to determine where is the best place to play, it looks at both your and your opponents pieces 
				if ((col>=3)&& (boardarray[row][col-1] == redToken)&& (boardarray[row][col-2] == redToken)&& (boardarray[row][col-3] == redToken))
					h[i]=h[i]+16;
				if ((col<=3)&& (boardarray[row][col+1] == redToken)&& (boardarray[row][col+2] == redToken)&& (boardarray[row][col+3] == redToken))
					h[i]=h[i]+16;
				if ((row<=2)&& (boardarray[row+1][col] == redToken)&& (boardarray[row+2][col] == redToken)&& (boardarray[row+3][col] == redToken))
					h[i]=h[i]+16;
				if ((col>=3) && (row<=2)&& (boardarray[row+1][col-1] == redToken)&& (boardarray[row+2][col-2] == redToken)&& (boardarray[row+3][col-3] == redToken))
					h[i]=h[i]+16;
				if ((col<=3) && (row<=2) && (boardarray[row+1][col+1] == redToken)&& (boardarray[row+2][col+2] == redToken)&& (boardarray[row+3][col+3] == redToken))
					h[i]=h[i]+16;
				if ((col>=3) && (row>=3)&& (boardarray[row-1][col-1] == redToken)&& (boardarray[row-2][col-2] == redToken)&& (boardarray[row-3][col-3] == redToken))
					h[i]=h[i]+16;
				if ((col<=3) && (row>=3)&& (boardarray[row-1][col+1] == redToken)&& (boardarray[row-2][col+2] == redToken)&& (boardarray[row-3][col+3] == redToken))
					h[i]=h[i]+16;
				if ((col>=2)&& (boardarray[row][col-1] == redToken)&& (boardarray[row][col-2] == redToken))
					h[i]=h[i]+4;
				if ((col<=4)&& (boardarray[row][col+1] == redToken)&& (boardarray[row][col+2] == redToken))
					h[i]=h[i]+4;
				if ((row<=3)&& (boardarray[row+1][col] == redToken)	&& (boardarray[row+2][col] == redToken))
					h[i]=h[i]+4;
				if ((col>=2) && (row<=3)&& (boardarray[row+1][col-1] == redToken)&& (boardarray[row+2][col-2] == redToken))
					h[i]=h[i]+4;
				if ((col<=4) && (row<=3)&& (boardarray[row+1][col+1] == redToken)&& (boardarray[row+2][col+2] == redToken))
					h[i]=h[i]+4;
				if ((col>=2) && (row>=2)&& (boardarray[row-1][col-1] == redToken)&& (boardarray[row-2][col-2] == redToken))
					h[i]=h[i]+4;
				if ((col<=4) && (row>=2)&& (boardarray[row-1][col+1] == redToken)&& (boardarray[row-2][col+2] == redToken))
					h[i]=h[i]+4;
				if ((col>=1)&& (boardarray[row][col-1] == redToken))
					h[i]=h[i]+2;
				if ((col<=5)&& (boardarray[row][col+1] == redToken))
					h[i]=h[i]+2;
				if ((row<=4)&& (boardarray[row+1][col] == redToken))
					h[i]=h[i]+2;
				if ((col>=1) && (row<=4)&& (boardarray[row+1][col-1] == redToken))
					h[i]=h[i]+2;
				if ((col<=5) && (row<=4)&& (boardarray[row+1][col+1] == redToken))
					h[i]=h[i]+2;
				if ((col>=1) && (row>=1)&& (boardarray[row-1][col-1] == redToken))
					h[i]=h[i]+2;
				if ((col<=5) && (row>=1)&& (boardarray[row-1][col+1] == redToken))
					h[i]=h[i]+2;
				if ((col>=3)&& (boardarray[row][col-1] == blueToken)&& (boardarray[row][col-2] == blueToken)&& (boardarray[row][col-3] == blueToken))
					h[i]=h[i]+8;
				//right
				if ((col<=3)&& (boardarray[row][col+1] == blueToken)&& (boardarray[row][col+2] == blueToken)&& (boardarray[row][col+3] == blueToken))
					h[i]=h[i]+8;
				if ((row<=2)&& (boardarray[row+1][col] == blueToken)&& (boardarray[row+2][col] == blueToken)&& (boardarray[row+3][col] == blueToken))
					h[i]=h[i]+8;
				if ((col>=3) && (row<=2)&& (boardarray[row+1][col-1] == blueToken)&& (boardarray[row+2][col-2] == blueToken)&& (boardarray[row+3][col-3] == blueToken))
					h[i]=h[i]+8;
				if ((col<=3) && (row<=2)&& (boardarray[row+1][col+1] == blueToken)&& (boardarray[row+2][col+2] == blueToken)&& (boardarray[row+3][col+3] == blueToken))
					h[i]=h[i]+8;
				if ((col>=3) && (row>=3)&& (boardarray[row-1][col-1] == blueToken)&& (boardarray[row-2][col-2] == blueToken)&& (boardarray[row-3][col-3] == blueToken))
					h[i]=h[i]+8;
				if ((col<=3) && (row>=3)&& (boardarray[row-1][col+1] == blueToken)&& (boardarray[row-2][col+2] == blueToken)&& (boardarray[row-3][col+3] == blueToken))
					h[i]=h[i]+8;
				if ((col>=2)&& (boardarray[row][col-1] == blueToken)&& (boardarray[row][col-2] == blueToken))
					h[i]=h[i]+4;
				if ((col<=4)&& (boardarray[row][col+1] == blueToken)&& (boardarray[row][col+2] == blueToken))
					h[i]=h[i]+4;
				if ((row<=3)&& (boardarray[row+1][col] == blueToken)&& (boardarray[row+2][col] == blueToken))
					h[i]=h[i]+4;
				if ((col>=2) && (row<=3)&& (boardarray[row+1][col-1] == blueToken)&& (boardarray[row+2][col-2] == blueToken))
					h[i]=h[i]+4;
				if ((col<=4) && (row<=3)&& (boardarray[row+1][col+1] == blueToken)&& (boardarray[row+2][col+2] == blueToken))
					h[i]=h[i]+4;
				if ((col>=2) && (row>=2)&& (boardarray[row-1][col-1] == blueToken)&& (boardarray[row-2][col-2] == blueToken))
					h[i]=h[i]+4;
				if ((col<=4) && (row>=2)&& (boardarray[row-1][col+1] == blueToken)&& (boardarray[row-2][col+2] == blueToken))
					h[i]=h[i]+4;
				if ((col>=1)&& (boardarray[row][col-1] == blueToken))
					h[i]=h[i]+2;
				if ((col<=5)&& (boardarray[row][col+1] == blueToken))
					h[i]=h[i]+2;
				if ((row<=4)&& (boardarray[row+1][col] == blueToken))
					h[i]=h[i]+2;
				if ((col>=1) && (row<=4)&& (boardarray[row+1][col-1] == blueToken))
					h[i]=h[i]+2;
				if ((col<=5) && (row<=4)&& (boardarray[row+1][col+1] == blueToken))
					h[i]=h[i]+2;
				if ((col>=1) && (row>=1)&& (boardarray[row-1][col-1] == blueToken))
					h[i]=h[i]+2;
				if ((col<=5) && (row>=1)&& (boardarray[row-1][col+1] == blueToken))
					h[i]=h[i]+2;       
			}
		}

		int max = 0;
		int mm = 3;
		int sum = 0;
		for (int i=0; i<7; i++) {
			if (h[i]>max) {max=h[i]; mm=i;}//sets the sum equal to the highest value that was found in the above section and that is the best possible move
			sum= sum+h[i];
		}

		int[] f = new int [7];
		for(int i =0; i <7;i++){
			for(int j=0;j<6;j++){
				if (boardarray[j][i]!=1 &&boardarray[j][i]!=-1){//just like above this finds exactly where in the best column the piece will be placed
					f[i]=j;
				}

			}
			if(f[i]!=1&&f[i]!=2&&f[i]!=3&&f[i]!=4&&f[i]!=5){
				f[i]=0;
			}
		}
		

		if (sum==0) mm = (int) (Math.random()*7);//this really only happens if the computer goes first, basically place a piece randomly on the board
		Control test = new Control();
		test.redDisk(mm*99, f[mm]*95,this.panel, false);//passes into control to use the redDisk to place the piece that was selected
		Control.check_disk[mm][f[mm]]=true;//sets true that there is a disk there



	}


}







