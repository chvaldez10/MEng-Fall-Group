package tictactoe;

import java.util.Scanner;

/**
 * Represents a player in the Tic Tac Toe game.
 * Each player has a name and a mark ('X' or 'O').
 */
public class Player {
    /** The name of the player. */
    private String name;

    /** The game board. */
    private Board board;

    /** The opponent player. */
    private Player opponent;

    /** The mark ('X' or 'O') of the player. */
    private char mark;

    /** Scanner for getting input from the player. */
    Scanner sc = new Scanner(System.in);


    /**
     * Constructs a new player with a specified name and mark.
     *
     * @param name The name of the player.
     * @param mark The mark ('X' or 'O') of the player.
     */
    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * Sets the game board for the player.
     *
     * @param board The Tic Tac Toe game board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Sets the opponent player.
     *
     * @param opponent The opponent player.
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }


    /**
     * Starts the turn for this player. If the board isn't full and there's no winner yet,
     * the player is prompted to make a move. After the move, it checks for a winner or draw.
     */
    public void play() {
        // board has space and no winner
        if (!board.isFull() && !board.oWins() && !board.xWins()) makeMove();

        if (board.xWins()) {
            System.out.println("X Wins!");
        } else if (board.oWins()){
            System.out.println("O Wins!");
        } else if (board.isFull()){
            System.out.println("Draw!");
        } else {
            board.display();
            opponent.play();
        }
    }

    /**
     * Allows the player to make a move on the board.
     */
    public void makeMove() {
        int tmpMarkRow = getUserInput("row");
        int tmpMarkColumn = getUserInput("column");
        board.addMark(tmpMarkRow, tmpMarkColumn, mark);
    }

    /**
     * Prompts the player for input (row or column) and ensures it's valid.
     *
     * @param label The label ("row" or "column") for the input prompt.
     * @return The valid row or column index.
     */
    public int getUserInput(String label) {
        int index;
        while (true) {
            System.out.print(name + ", what " + label + " should your next " + mark + " be placed in? ");
            try {
                index = Integer.parseInt(getKeyboardChar());
                if (isValidIndex(index)) return index;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid " + label + ".");
            }
        }
    }

    /**
     * Retrieves a character input from the keyboard.
     *
     * @return A string representation of the character input.
     */
    private String getKeyboardChar(){
        if(sc.hasNext()) {
            char c = sc.next().charAt(0);
            return String.valueOf(c);
        }
        return "Not a valid input.";
    }

    /**
     * Checks if the given index is valid for the Tic Tac Toe board.
     *
     * @param index The index to check.
     * @return True if the index is valid (0, 1, or 2), false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index <= 2;
    }
}
