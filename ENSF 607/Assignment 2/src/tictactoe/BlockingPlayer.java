package tictactoe;

import java.util.HashMap;
import java.util.Map;

public class BlockingPlayer extends RandomPlayer{
	Map<Integer, int[][]> blockingMap = new HashMap<>();		// spots to block
	
	public BlockingPlayer(String name, char mark) {
		super(name, mark);
		
		blockingMap.put(00, new int[][]{{01, 02}, {11, 22}, {10, 20}});
		blockingMap.put(01, new int[][]{{00, 02}, {11, 21}});
		blockingMap.put(02, new int[][]{{00, 01}, {11, 20}, {12, 22}});
		
		blockingMap.put(10, new int[][]{{00, 20}, {11, 12}});
		blockingMap.put(11, new int[][]{{00, 22}, {01, 21}, {02, 20}, {10, 12}});
		blockingMap.put(12, new int[][]{{02, 22}, {10, 11}});
		
		blockingMap.put(20, new int[][]{{00, 10}, {21, 22}, {02, 11}});
		blockingMap.put(21, new int[][]{{01, 21}, {20, 22}});
	}
	
	@Override
	public void makeMove() {
		
		for (int row = lowIndex; row <= highIndex; row ++) {
			for (int col = lowIndex; col <= highIndex; col++) {
				// check if spot you're traversing is empty first.
				if ((super.isSpotEmpty(row, col) && testForBlocking(row, col)) == true) {
				} else {
					super.makeMove();		// No blocking to be done - randomly place mark.
				}
			}
		}
		// Try blocking first. If no blocking, then revert to using super.makeMove().
	}
	
	/**
	 * @return True if there is an opportunity to block.
	 */
	protected boolean testForBlocking(int row, int col) {
		// TODO Determine opponent winning situations for current spot
		// there are 8 winning combinations ; if 2 out of 3 spots in a winning condition are occupied, return true.
			
		// 
		
		// store row and col as string (?)
		int checkSpot = row * 10 + col;
		
		int blockingScenario[];
		
		
		// create a switch case for each of the 9 string combinations
		switch (checkSpot) {
			case 00:
				for (int i = 0; i < blockingMap.get(checkSpot).length; i++) {
					// retrieve values from int array
//					for (int j = 0; j < 2; j++) {
					blockingScenario = blockingMap.get(checkSpot)[i]; // blockingScenario contains {01, 02}
						
					markCounter(blockingScenario);
//					}
				}
				// int[][] retrievedArray = map.get(1);
				board.getMark(row, col);
				
				//
				opponentMarkCounter = 0;
			case 01:
				
			case 02:
				
			case 10:
				
			case 11:
				
			case 12:
				
			case 20:
				
			case 21:
			
			case 22:
				// check the opponent marks according to the map
					// for 22, check spot 02 and spot 12 if there are opponent marks
				// retrieve the blocking scenarios from the map
		}
		// count the number of opponent marks in each scenario inside that case
			// possibly a method with access to a C-struct-like Java class.
			// markCounter();
		// row/col -> switch case -> scenario -> count -> decide
		
		// testForBlocking is already being looped at function call
		return false;
	}
	
	
	/**	Checks the board to see if it matches a blocking scenario
	 * @param blockingScenario
	 * @return true if the blocking player needs to block checkSpot
	 */
	protected boolean markCounter(int[] blockingScenario) {
		int row;
        int col;
        int opponentMarkCounter = 0;
		
		// loop out here
        // Extract the first digit
		for (int j = 0; j < 2; j++) {
			// then split the 2 digits apart - this is the spot
			row = blockingScenario[j] / 10; // Extract the first digit
	        col = blockingScenario[j] % 10;
	        if (board.getMark(row, col) == opponent.mark){
	        	opponentMarkCounter++;
	        }
	        
		}
		
		// reach into the array blockingScenario
		
		
        
        // check the spot's (row, col) mark
        // we want to count the opponent's marks in the chosen spot
        
		return false;
	}
}
