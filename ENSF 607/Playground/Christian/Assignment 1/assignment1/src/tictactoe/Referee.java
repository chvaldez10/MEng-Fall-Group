package tictactoe;

/**
 * The Referee class manages the Tic Tac Toe game between two players.
 * It sets up the players, displays the initial board, and starts the game.
 */
public class Referee {

    /** Player using the 'X' mark. */
    private Player xPlayer;

    /** Player using the 'O' mark. */
    private Player oPlayer;

    /** The Tic Tac Toe game board. */
    private Board board;

    /**
     * Sets the game board.
     *
     * @param board The Tic Tac Toe game board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Sets the player using the 'O' mark.
     *
     * @param oPlayer The 'O' player.
     */
    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    /**
     * Sets the player using the 'X' mark.
     *
     * @param xPlayer The 'X' player.
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    /**
     * Starts the Tic Tac Toe game.
     * It sets the opponents for both players, displays the initial empty board,
     * and lets the 'X' player make the first move.
     */
    public void runTheGame() {
        // set the opponent for both players
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);

        board.display(); // show the initial empty board
        xPlayer.play();  // start the game with the 'X' player
    }
}
