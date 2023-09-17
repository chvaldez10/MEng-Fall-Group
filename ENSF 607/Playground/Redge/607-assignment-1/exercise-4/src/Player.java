
import java.io.*;

/** Player class represents a tic-tac-toe player. The Player class is assigned an opponent and
 * assigned the game board via the Game class.
 * This class contains the play() method, which controls the players' turns, displays the board (via Board class methods), and checks
 * if there is a winner yet (via Game class methods).
 * @author redge
 *
 */
public class Player {
	private String name;
	private Board board;
	private Player opponent;
	char mark;
	
	public Player(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}
	
	/**	Calls makeMove() as long as xWin(), oWin(), and isFull() in class Board returns false.
	 * 	If any of the above are true, game stops, winner is announced/tie is announced.
	 * 	Then displays board after each move. Afterwards, it checks for the winner and then passes
	 *  the turn to the other player
	 * @throws IOException 
	 */
	public void play() throws IOException {
		boolean gameOver = board.xWins() || board.oWins() || board.isFull();
//		System.out.println("\n**gameOver = "+gameOver);
		while (!(board.xWins() || board.oWins() || board.isFull())) {
			makeMove();
			board.display();
			if (board.xWins()) {
				System.out.println("\n THE GAME IS OVER: "+ this.name +" is the winner!\n");
				gameOver = true;
			} else if (board.oWins()) {
				System.out.println("\n THE GAME IS OVER: "+ this.name +" is the winner!\n");
				gameOver = true;
			} else if (board.isFull()){
				System.out.println("\n DRAW!\n");
				gameOver = true;
			}
			if (!gameOver) {
				opponent.play();
			}
		} 
	}
	
	/** Asks the player to make a move by entering the row and column numbers.
	 * 	Puts a X or O mark on the board by calling addMark() in class Board.
	 * @throws IOException 
	 * 
	 */
	public void makeMove() throws IOException	{
		BufferedReader stdin;
		System.out.print("\n" + this.name + ", what row should your next " + this.mark + " be placed in? ");
		
		stdin = new BufferedReader(new InputStreamReader(System.in));		
		String sRow= stdin.readLine();
		while (sRow == null) {
			System.out.print("Please try again: ");
			sRow = stdin.readLine();
		}
		int row = Integer.parseInt(sRow);
		
		System.out.print("\n" + this.name + ", what column should your next " + this.mark + " be placed in? ");
		String sCol= stdin.readLine();
		while (sCol == null) {
			System.out.print("Please try again: ");
			sCol = stdin.readLine();
		}
		int col = Integer.parseInt(sCol);
		
		this.board.addMark(row, col, this.mark);
	}
	
	/** Connects the other player to this player.
	 * @param player (Player) - current player's opponent
	 */
	public void setOpponent(Player player) {
		this.opponent = player;
	}
	
	/** Connects the player to the game board.
	 * @param theBoard (Board) - the game board.
	 */
	public void setBoard(Board theBoard) {
		this.board = theBoard;
	}
}
