package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * The BlockingPlayer class represents a player in a Tic-Tac-Toe game that attempts to block the opponent from winning.
 * This player inherits from the RandomPlayer class.
 */
public class BlockingPlayer extends RandomPlayer{
	Map<Integer, int[][]> blockingMap = new HashMap<>();		// spots to block
	
	/**
     * Creates a new BlockingPlayer with the specified name and game mark.
     *
     * @param name The name of the player.
     * @param mark The game mark (X or O) associated with the player.
     */
	public BlockingPlayer(String name, char mark) {
		super(name, mark);
		
		// Initialize the blocking map with blocking scenarios for each spot on the board.
		blockingMap.put(00, new int[][]{{01, 02}, {11, 22}, {10, 20}});
		blockingMap.put(01, new int[][]{{00, 02}, {11, 21}});
		blockingMap.put(02, new int[][]{{00, 01}, {11, 20}, {12, 22}});
		
		blockingMap.put(10, new int[][]{{00, 20}, {11, 12}});
		blockingMap.put(11, new int[][]{{00, 22}, {01, 21}, {02, 20}, {10, 12}});
		blockingMap.put(12, new int[][]{{02, 22}, {10, 11}});
		
		blockingMap.put(20, new int[][]{{00, 10}, {21, 22}, {02, 11}});
		blockingMap.put(21, new int[][]{{01, 21}, {20, 22}});
		blockingMap.put(22, new int[][]{{02, 12}, {20, 21}, {00, 11}});
	}
	
	/**
     * Makes a move in the Tic-Tac-Toe game. This method attempts to block the opponent from winning if possible.
     * If no blocking move is available, it falls back to making a random move.
     */
	@Override
	public void makeMove() {
		
		boolean blocking = false;
		
		for (int row = lowIndex; row <= highIndex; row ++) {
			for (int col = lowIndex; col <= highIndex; col++) {
				// check if spot you're traversing is empty first.
				if ((super.isSpotEmpty(row, col) == true) && (testForBlocking(row, col) == true)) {
					System.out.println(super.name + "'s turn.");
					board.addMark(row, col, mark);
					blocking = true;
					break;
				}
				
			}
			if (blocking == true) {break; }				// break out of loop if player needs to block now
		}
		// Tried blocking first. If no blocking, then revert to using super.makeMove().
		if (blocking == false) {
			super.makeMove();
		}
	}
	
	/**
     * Checks if there is an opportunity to block the opponent from winning at the specified row and column.
     *
     * @param row The row to check.
     * @param col The column to check.
     * @return True if there is an opportunity to block the opponent.
     */
	protected boolean testForBlocking(int row, int col) {
		// there are 8 winning combinations ; if 2 out of 3 spots in a winning condition are occupied, return true.	
		boolean blockSpot = false;
		
		int checkSpot = row * 10 + col;
		
		int blockingScenario[];
		
		
		for (int i = 0; i < blockingMap.get(checkSpot).length; i++) {
			
			// retrieve the blocking scenarios from the map
			blockingScenario = blockingMap.get(checkSpot)[i]; // blockingScenario contains {01, 02}
			
			blockSpot = opponentMarkCounter(blockingScenario);
			if (blockSpot == true) {
				break;
			}
		}

		return blockSpot;
	}
	
	/**
     * Counts the opponent's marks in a specific blocking scenario.
     *
     * @param blockingScenario The blocking scenario to check.
     * @return True if the blocking player needs to block the specified scenario.
     */
	protected boolean opponentMarkCounter(int[] blockingScenario) {
		int row;
        int col;
        int opponentMarkCounter = 0;
		
		for (int j = 0; j < 2; j++) {
			// reach into the array blockingScenario, then split the 2 digits apart - this is the spot
			row = blockingScenario[j] / 10; // Extract the first digit
	        col = blockingScenario[j] % 10;
	        if (board.getMark(row, col) == opponent.mark){ 		// check if the spot has an opponent mark
	        	opponentMarkCounter++;
	        }
		}
		if ((opponentMarkCounter == 2)) {
			return true;
		}
        
		return false;
	}
}
