
/**
 * The Board class represents the game board for tic-tac-toe.
 * It manages the state of the board, checks for game outcomes, and displays the board.
 */

public class Board implements Constants {
	private char theBoard[][];
	private int markCount;

    /**
     * Constructs a new Board instance and initializes the game board.
     * The board is initially empty (filled with SPACE_CHAR).
     */
	
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	
    /**
     * Gets the mark (X, O, or SPACE_CHAR) at a specified row and column on the board.
     * @param row The row index.
     * @param col The column index.
     * @return The mark at the specified position.
     */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	
    /**
     * Checks if the game board is full (no empty spaces left).
     * @return true if the board is full, false otherwise.
     */
	public boolean isFull() {
		return markCount == 9;
	}

    /**
     * Checks if the 'X' player wins the game.
     * @return true if 'X' wins, false otherwise.
     */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

    /**
     * Checks if the 'O' player wins the game.
     * @return true if 'O' wins, false otherwise.
     */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

    /**
     * Displays the current state of the game board including row and column headers.
     */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

    /**
     * Places a mark (X or O) on the game board at the specified row and column.
     * @param row  The row where the mark is placed.
     * @param col  The column where the mark is placed.
     * @param mark The mark to be placed (X or O).
     */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

    /**
     * Clears the game board by resetting all cells to SPACE_CHAR and mark count to 0.
     */

	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

    /**
     * Checks if a player with a specified mark wins the game. Checks for a winning pattern horizontally, vertically or diagonally
     * @param mark The mark to check for (X or O).
     * @return 1 if the player wins, 0 if not.
     */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

    /**
     * Displays column headers for the game board.
     */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

    /**
     * Adds hyphens to visually separate rows on the game board.
     */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

    /**
     * Adds spaces to visually separate cells within rows on the game board.
     */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
