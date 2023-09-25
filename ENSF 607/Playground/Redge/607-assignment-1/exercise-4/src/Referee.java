import java.io.IOException;

/** Referee class represents the referee for a tic-tac-toe game. It knows which player is
 * 	the x-player and the o-player. It also knows the board. After establishing the players
 * 	and the board, the Referee class initiates the game. Lastly, this class also announces
 * 	when the game has ended.
 * @author redge
 *
 */
public class Referee {
	private Player xPlayer;
	private Player oPlayer;
	private Board board;
	
	public Referee() {
		
	}
	
	/** runTheGame() sets the opponents up, displays the board, and initiates the game.
	 * 	Afterwards a winner is declared.
	 * @throws IOException
	 */
	public void runTheGame() throws IOException {
		System.out.println("\nReferee started the game...\n");
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		
		board.display();
		xPlayer.play();		// game is initiated
		System.out.println("Game Ended ...");
		
//		board.display();
	}
	
	/** Sets the current game board as board.
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}
}
