package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

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
		blockingMap.put(22, new int[][]{{02, 12}, {20, 21}, {00, 11}});
	}
	
	@Override
	public void makeMove() {
		
		boolean blocking = false;
		
		for (int row = lowIndex; row <= highIndex; row ++) {
			for (int col = lowIndex; col <= highIndex; col++) {
				// check if spot you're traversing is empty first.
				if ((super.isSpotEmpty(row, col) == true) && (testForBlocking(row, col) == true)) {
					board.addMark(row, col, mark);
					System.out.println("Blocking Player move on " + row + col);					// test code
					blocking = true;
					break;
				} else {
//					super.makeMove();		// No blocking to be done - randomly place mark.
				}
				
			}
			if (blocking == true) {break; }
		}
		// Tried blocking first. If no blocking, then revert to using super.makeMove().
		if (blocking == false) {
			super.makeMove();				// No blocking to be done - randomly place mark.
			System.out.println("Random Player move.");								// test code
		}
	}
	
	/**
	 * @return True if there is an opportunity to block.
	 */
	protected boolean testForBlocking(int row, int col) {
		// there are 8 winning combinations ; if 2 out of 3 spots in a winning condition are occupied, return true.	
		boolean blockSpot = false;
		
		int checkSpot = row * 10 + col;
		
		int blockingScenario[];
		
//		int testCounter_nullValue = 0;									// test code
		
		for (int i = 0; i < blockingMap.get(checkSpot).length; i++) {
//			System.out.println(checkSpot);								// test code
			
			// retrieve the blocking scenarios from the map
			blockingScenario = blockingMap.get(checkSpot)[i]; // blockingScenario contains {01, 02}
//			System.out.println(Arrays.toString(blockingScenario));		// test code
			
			blockSpot = markCounter(blockingScenario);
			if (blockSpot == true) {
				break;
			}
//			testCounter_nullValue++;									// test code
		}
		
//		for (int blockingScenario1[] : blockingMap.get(checkSpot)) {			
//			// retrieve the blocking scenarios from the map
//			blockSpot = markCounter(blockingScenario1);					// blockingScenario contains {01, 02}
//		}
		
		
				// check the opponent marks according to the map
					// for 22, check spot 02 and spot 12 if there are opponent marks
		// count the number of opponent marks in each scenario inside that case
			// possibly a method with access to a C-struct-like Java class.
		// row/col -> switch case -> scenario -> count -> decide
		
		// testForBlocking is already being looped at function call
		return blockSpot;
	}
	
	// TODO consider renaming markCounter
	/**	Checks the board to see if it matches a blocking scenario
	 * @param blockingScenario
	 * @return true if the blocking player needs to block checkSpot
	 */
	protected boolean markCounter(int[] blockingScenario) {
		int row;
        int col;
        int opponentMarkCounter = 0;
//        int spaceMarkCounter = 0;
		
		for (int j = 0; j < 2; j++) {
			// reach into the array blockingScenario, then split the 2 digits apart - this is the spot
			row = blockingScenario[j] / 10; // Extract the first digit
	        col = blockingScenario[j] % 10;
	        if (board.getMark(row, col) == opponent.mark){ 		// check if the spot has an opponent mark
	        	opponentMarkCounter++;
	        } else if (board.getMark(row, col) == SPACE_CHAR) {
//	        	spaceMarkCounter++;
	        }
		}
		if ((opponentMarkCounter == 2)) {
//		if ((opponentMarkCounter == 2) && (spaceMarkCounter == 1)) {
			return true;
		}
        // we want to count the opponent's marks in the chosen spot
        
		return false;
	}
}
