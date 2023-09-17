

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 


/** 
 * Board class is the logical representation of the board. It contains all methods pertaining to game state,
 * win conditions, resetting the board, and displaying the board on the console.
 *
 */
public class Board implements Constants {
	private char theBoard[][];
	private int markCount;

	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/** Fetches the mark in the specified row and column of the board.
	 * @param row - tic-tac-toe board row
	 * @param col - tic-tac-toe board column
	 * @return mark (char) - either X or O depending on whose turn it is.
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/** Condition: All 9 spots on the board are filled
	 *
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	
	/** Condition: x Player wins.
	 * @return true if x Player has won.
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/** Condition: o Player wins.
	 * @return true if o Player has won.
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/** All display elements of the board.
	 * 	Prints a visual representation of the board while the game is going.
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

	/** Adds either an 'X' or an 'O' on the specified row and column of the board.
	 * @param row - tic-tac-toe board row
	 * @param col - tic-tac-toe board column
	 * @param mark (char) - either X or O depending on whose turn it is.
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/** clear() resets the entire board. Resets markCount to 0.
	 * 
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * checkWinner checks the game if there is a winner.
	 * @param mark (char) - either X or O depending on whose turn it is.
	 * @return result (int) - winner (either 0 or 1).
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

	/** Prints the column header
	 * 
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

	/** Prints the hyphens
	 * 
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	
	/** Prints the space padding
	 * 
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
