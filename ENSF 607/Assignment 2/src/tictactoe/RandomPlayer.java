package tictactoe;

public class RandomPlayer extends Player{
	public RandomPlayer(String name, char mark) {
		super(name, mark);
	}
	
	
	@Override
	/**
     * Generate random spot on the board.
     */
	public void makeMove() {
		int row, col;
		boolean isEmpty;
		RandomGenerator randomGen = new RandomGenerator(); 
		System.out.println(super.name + "'s turn.");
		
		while (true) {
			row = randomGen.discrete(lowIndex, highIndex);
			col = randomGen.discrete(lowIndex, highIndex);
			isEmpty = super.isSpotEmpty(row, col);
			
            if (isEmpty) {
                board.addMark(row, col, mark);
                break; // exits the loop when a valid spot is marked
            } 
		}
	}
}
