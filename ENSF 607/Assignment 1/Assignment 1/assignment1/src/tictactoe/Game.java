package tictactoe;

import java.io.*;

/**
 * The Game class represents the main logic and structure for the Tic Tac Toe game.
 * It initializes the board, players and starts the game via the referee.
 */
public class Game implements Constants {

	/** Represents the board of the Tic Tac Toe game. */
	private Board theBoard;

	/** Represents the referee to manage the game's progression. */
	private Referee theRef;

	/**
	 * Constructor for the Game class.
	 * Initializes the board.
	 */
	public Game( ) {
		theBoard  = new Board();
	}

	/**
	 * Appoints a referee to the game and starts the game.
	 *
	 * @param r The referee to be appointed to the game.
	 * @throws IOException If there's any input-output exception while running the game.
	 */
	public void appointReferee(Referee r) throws IOException {
		theRef = r;
		theRef.runTheGame();
	}

	/**
	 * The main method to start and play the Tic Tac Toe game.
	 * It initializes the board, gets player names, creates player and referee objects,
	 * and starts the game.
	 *
	 * @param args Command line arguments, not used in this application.
	 * @throws IOException If there's any input-output exception while reading player names or starting the game.
	 */
	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		Game theGame = new Game();

		// for system input
		BufferedReader stdin;
		stdin = new BufferedReader(new InputStreamReader(System.in));

		// get first user input
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
