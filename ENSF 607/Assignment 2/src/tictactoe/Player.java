package tictactoe;

import java.util.Scanner;

abstract class Player implements Constants{
    /** The name of the player. */
    protected String name;

    /** The game board. */
    protected Board board;

    /** The opponent player. */
    protected Player opponent;

    /** The mark ('X' or 'O') of the player. */
    protected char mark;

    /** Scanner for getting input from the player. */
    protected Scanner sc = new Scanner(System.in);

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
     * Initiates a player's turn. If the game can continue, it prompts the player to make a move.
     */
    protected abstract void play();
    
    /**
     * Allows the player to make a move on the board.
     */
    protected abstract void makeMove();

    /**
     * Sets the opponent player.
     *
     * @param opponent The opponent player.
     */
    protected void setOpponent(Player opponent) {
    	this.opponent = opponent;
    }
    
    /**
     * Sets the game board for the player.
     *
     * @param board The Tic Tac Toe game board.
     */
    protected void setBoard(Board board) {
        this.board = board;
    }
    
	/**
     * Checks if the game can continue. The game can continue if the board isn't full and there's no winner yet.
     *
     * @return true if the game can continue, false otherwise.
     */
    protected boolean isGameContinuable() {
        return !board.isFull() && !board.oWins() && !board.xWins();
    }
    
    /**
     * Determines if the game has concluded, either due to a win or a draw.
     *
     * @return true if the game is over, false otherwise.
     */
    protected boolean isGameOver() {
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
    protected void displayGameResult() {
        if (board.xWins()) {
            System.out.println("THE GAME IS OVER: " + (mark == 'X' ? name : opponent.name) + " is the winner!");
        } else if (board.oWins()) {
            System.out.println("THE GAME IS OVER: " + (mark == 'O' ? name : opponent.name) + " is the winner!");
        } else {
            System.out.println("Draw!");
        }
    }

    /**
     * Check if spot is empty.
     * 
     * @param row The row index on the board.
     * @param col The column index on the board.
     * @return true if spot contains a space character, false otherwise.
     */
    protected boolean isSpotEmpty(int row, int col) {
    	return board.getMark(row, col) == SPACE_CHAR;
    }
}