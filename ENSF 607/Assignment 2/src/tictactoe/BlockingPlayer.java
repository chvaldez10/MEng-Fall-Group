package tictactoe;

public class BlockingPlayer extends RandomPlayer{
	public BlockingPlayer(String name, char mark) {
		super(name, mark);
	}
	
	@Override
	public void makeMove() {
		
		for (int row = lowIndex; row <= highIndex; row ++) {
			for (int col = lowIndex; col <= highIndex; col++) {
				if (testforBlocking(row, col) == true) {
					board.addMark(row, col, mark);
				} else {
					super.makeMove();
				}
			}
		}
		// Try blocking first. If no blocking, then revert to using super.makeMove().
	}
	
	/**
	 * @return True if there is an opportunity to block.
	 */
	protected boolean testforBlocking(int row, int col) {
			board.getMark(row, col);
		return false;
	}
}
