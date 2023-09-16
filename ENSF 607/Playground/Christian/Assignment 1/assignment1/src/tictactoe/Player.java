package tictactoe;

import java.util.Scanner;

/**
 * Represents a player in the Tic Tac Toe game.
 * Each player has a name and a mark ('X' or 'O').
 */
public class Player implements Constants{
    /** The name of the player. */
    private String name;

    /** The game board. */
    private Board board;

    /** The opponent player. */
    private Player opponent;

    /** The mark ('X' or 'O') of the player. */
    private char mark;

    /** Scanner for getting input from the player. */
    private Scanner sc = new Scanner(System.in);


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
     * Initiates the player's turn. If the game can continue, it prompts the player to make a move.
     * After the move, it checks for a game conclusion and, if necessary, displays the result.
     */
    public void play() {
        if (isGameContinuable()) {
            makeMove();
        }

        if (isGameOver()) {
            displayGameResult();
        } else {
            board.display();
            opponent.play();
        }
    }

    /**
     * Checks if the game can continue. The game can continue if the board isn't full and there's no winner yet.
     *
     * @return true if the game can continue, false otherwise.
     */
    private boolean isGameContinuable() {
        return !board.isFull() && !board.oWins() && !board.xWins();
    }

    /**
     * Determines if the game has concluded, either due to a win or a draw.
     *
     * @return true if the game is over, false otherwise.
     */
    private boolean isGameOver() {
        return board.isFull() || board.oWins() || board.xWins();
    }

    /**
     * Displays the result of the game. This method determines which player (if any) won the game,
     * or if the game ended in a draw, and then prints an appropriate message.
     * <p>
     * If player X wins, it displays "THE GAME IS OVER: [X's name] is the winner!"
     * If player O wins, it displays "THE GAME IS OVER: [O's name] is the winner!"
     * If the board is full and neither player has won, it displays "Draw!"
     * </p>
     */
    private void displayGameResult() {
        if (board.xWins()) {
            System.out.println("THE GAME IS OVER: " + (mark == 'X' ? name : opponent.name) + " is the winner!");
        } else if (board.oWins()) {
            System.out.println("THE GAME IS OVER: " + (mark == 'O' ? name : opponent.name) + " is the winner!");
        } else {
            System.out.println("Draw!");
        }
    }

    /**
     * Allows the player to make a move on the board.
     */
    public void makeMove() {
        int tmpMarkRow;
        int tmpMarkColumn;
        char tmpMark;

        while (true) {
            tmpMarkRow = getUserInput("row");
            tmpMarkColumn = getUserInput("column");
            tmpMark = board.getMark(tmpMarkRow, tmpMarkColumn);

            if (tmpMark == SPACE_CHAR) {
                board.addMark(tmpMarkRow, tmpMarkColumn, mark);
                break; // exits the loop when a valid spot is marked
            } else {
                System.out.println("Spot not available. Try again.");
            }
        }
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
