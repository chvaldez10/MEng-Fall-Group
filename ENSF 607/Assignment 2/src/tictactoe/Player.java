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
}