package tictactoe;

import java.util.Random;

/**
 * The SmartPlayer class represents a player in a Tic-Tac-Toe game that makes smart moves based on the criteria provided.
 * This player inherits from the BlockingPlayer class.
 */
public class SmartPlayer extends BlockingPlayer {

    /**
     * Creates a new SmartPlayer with the specified name and game mark.
     *
     * @param name The name of the player.
     * @param mark The game mark (X or O) associated with the player.
     */
    public SmartPlayer(String name, char mark) {
        super(name, mark);
    }

    /**
     * Makes a move in the Tic-Tac-Toe game based on the following criteria:
     * 1. Looks for a move to win immediately.
     * 2. Looks for a way to block the opponent from winning on the next move.
     * 3. Picks a vacant square at random if no winning or blocking move is available.
     */
    @Override
    public void makeMove() {
        if (makeWinningMove()) {
            return; // Made a winning move
        } else if (makeBlockingMove()) {
            return; // Made a blocking move
        } else {
            // No winning or blocking move available, make a random move
            makeRandomMove();
        }
    }

    /**
     * Attempts to make a move to win the game immediately.
     *
     * @return True if a winning move is made, false otherwise.
     */
    private boolean makeWinningMove() {
        // Iterate through the board and check for a spot to win immediately
        for (int row = lowIndex; row <= highIndex; row++) {
            for (int col = lowIndex; col <= highIndex; col++) {
                if (isSpotEmpty(row, col) && testForWinning(row, col)) {
                    System.out.println(super.name + "'s turn.");
                    board.addMark(row, col, mark);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Attempts to make a move to block the opponent from winning on the next move.
     *
     * @return True if a blocking move is made, false otherwise.
     */
    private boolean makeBlockingMove() {
        // Iterate through the board and check for a spot to block the opponent
        for (int row = lowIndex; row <= highIndex; row++) {
            for (int col = lowIndex; col <= highIndex; col++) {
                if (isSpotEmpty(row, col) && testForBlocking(row, col)) {
                    System.out.println(super.name + "'s turn.");
                    board.addMark(row, col, mark);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is an opportunity to win the game at the specified row and column.
     *
     * @param row The row to check.
     * @param col The column to check.
     * @return True if there is an opportunity to win the game.
     */
    protected boolean testForWinning(int row, int col) {
        // Check if placing the mark at (row, col) will lead to a win
        board.addMark(row, col, mark);
        boolean isWinningMove = board.checkWinner(mark) == 1;
        board.addMark(row, col, SPACE_CHAR); // Reset the board to its previous state
        return isWinningMove;
    }

    /**
     * Makes a random move on the board.
     */
    private void makeRandomMove() {
        int row, col;
        boolean isEmpty;
        RandomGenerator randomGen = new RandomGenerator();
        System.out.println(super.name + "'s turn.");

        while (true) {
            row = randomGen.discrete(lowIndex, highIndex);
            col = randomGen.discrete(lowIndex, highIndex);
            isEmpty = isSpotEmpty(row, col);

            if (isEmpty) {
                board.addMark(row, col, mark);
                return; // exits the loop when a valid spot is marked
            }
        }
    }
}