/**
 * The Referee class manages and controls the game flow between two players on a game board.
 */
public class Referee {
    private Player xPlayer; // Represents the player with the 'X' symbol.
    private Player oPlayer; // Represents the player with the 'O' symbol.
    private Board board;    // Represents the game board.

    /**
     * Constructs a new Referee instance.
     */
    public Referee() {
    }

    /**
     * Sets the player who will use the 'X' symbol.
     *
     * @param xPlayer The 'X' player.
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    /**
     * Sets the player who will use the 'O' symbol.
     *
     * @param oPlayer The 'O' player.
     */
    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    /**
     * Sets the game board for the referee to manage.
     *
     * @param board The game board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Runs the tic-tac-toe game.
     * The game continues until a player wins, it's a tie, or an error occurs.
     */
    public void runTheGame() {
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);

        System.out.println("\nReferee started the game...");
        board.display();

        Player currentPlayer = xPlayer;

        while (true) {
            currentPlayer.play();
            board.display();

            if (board.xWins()) {
                System.out.println("THE GAME IS OVER: " + currentPlayer.getName() + " is the winner!");
                System.out.println("Game Ended ...");
                break;
            } else if (board.oWins()) {
                System.out.println("THE GAME IS OVER: " + currentPlayer.getName() + " is the winner!");
                System.out.println("Game Ended ...");
                break;
            } else if (board.isFull()) {
                System.out.println("THE GAME IS OVER: It's a tie!");
                System.out.println("Game Ended ...");
                break;
            }

            // Switch players for the next turn
            currentPlayer = currentPlayer.getOpponent();
        }
    }
}
