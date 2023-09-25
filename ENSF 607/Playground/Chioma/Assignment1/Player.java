import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Player class represents a player in a tic-tac-toe game.
 * Each player has a name and a mark ('X' or 'O') and can make moves on the game board.
 * This class provides methods for player actions and interactions.
 * @author Chioma Ukaegbu
 */
public class Player {
    private String name;        // The name of the player.
    private char mark;          // The mark ('X' or 'O') of the player.
    private Player opponent;    // The opponent player.
    private Board board;        // The game board.

    /**
     * Constructs a new Player instance with a name and a mark.
     * @param name The name of the player.
     * @param mark The player's mark ('X' or 'O').
     */
    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * Sets the opponent player for this player.
     * @param opponent The opponent player.
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Sets the game board for this player to interact with.
     * @param board The game board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Gets the opponent player.
     * @return The opponent player.
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     * Gets the name of the player.
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * The main play method for a player.
     * Calls the makeMove() method to make a move on the game board.
     */
    public void play() {
        makeMove();
    }

    /**
     * Makes a move on the game board by taking user input for row and column.
     * Validates the move and adds the player's mark to the board.
     */
    public void makeMove() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int row, col;

        try {
            do {
                System.out.print(name + ", what row should your next " + mark + " be placed in? ");
                row = Integer.parseInt(reader.readLine());
                System.out.print(name + ", what column should your next " + mark + " be placed in? ");
                col = Integer.parseInt(reader.readLine());

                if (isValidMove(row, col)) {
                    board.addMark(row, col, mark);
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a move is valid by verifying that the selected row and column are within bounds
     * and the corresponding cell on the board is empty (space character).
     * @param row The selected row.
     * @param col The selected column.
     * @return true if the move is valid, false otherwise.
     */
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board.getMark(row, col) == Constants.SPACE_CHAR;
    }
}
