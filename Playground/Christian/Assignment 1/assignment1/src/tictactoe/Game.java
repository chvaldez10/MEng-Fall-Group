package tictactoe;

import java.io.*;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS


public class Game implements Constants {

	private Board theBoard;
	private Referee theRef;

    public Game( ) {
        theBoard  = new Board();
	}

    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }

	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		Game theGame = new Game();

		// for system input
		BufferedReader stdin;
		stdin = new BufferedReader(new InputStreamReader(System.in));

		//get first user input
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		// create x player
		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(theGame.theBoard);

		// get second input
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		// create y player
		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(theGame.theBoard);

		// create referee
		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);

		// starts the game
        theGame.appointReferee(theRef);
	}


}
