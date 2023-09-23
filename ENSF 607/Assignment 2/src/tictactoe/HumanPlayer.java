package tictactoe;

public class HumanPlayer extends Player{
	public HumanPlayer(String name, char mark) {
		super(name, mark);
	}
	@Override
	/**
     * If the game can continue, it prompts the player to make a move.
     * After the move, it checks for a game conclusion and, if necessary, displays the result.
     */
	public void play() {
		if (super.isGameContinuable()) {
            makeMove();
        }

        if (super.isGameOver()) {
            super.displayGameResult();
        } else {
            board.display();
            opponent.play();
        }
	}
	
	@Override
	/**
     * Allows the player to make a move on the board.
     */
    public void makeMove() {
        int tmpMarkRow, tmpMarkColumn;
        boolean isEmpty;

        while (true) {
            tmpMarkRow = getUserInput("row");
            tmpMarkColumn = getUserInput("column");
            isEmpty = super.isSpotEmpty(tmpMarkRow, tmpMarkColumn);

            if (isEmpty) {
                board.addMark(tmpMarkRow, tmpMarkColumn, mark);
                break; // exits the loop when a valid spot is marked
            } else {
                System.out.println("Spot not available. Try again.");
            }
        }
    }
    
    /**
     * Prompts the player for input (row or column) and ensures it's valid.
     *
     * @param label The label ("row" or "column") for the input prompt.
     * @return The valid row or column index.
     */
    public int getUserInput(String label) {
        int index;
        while (true) {
            System.out.print(name + ", what " + label + " should your next " + mark + " be placed in? ");
            try {
                index = Integer.parseInt(getKeyboardChar());
                if (isValidIndex(index)) return index;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid " + label + ".");
            }
        }
    }

    /**
     * Retrieves a character input from the keyboard.
     *
     * @return A string representation of the character input.
     */
    private String getKeyboardChar(){
        if(sc.hasNext()) {
            char c = sc.next().charAt(0);
            return String.valueOf(c);
        }
        return "Not a valid input.";
    }
    /**
     * Checks if the given index is valid for the Tic Tac Toe board.
     *
     * @param index The index to check.
     * @return True if the index is valid (0, 1, or 2), false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index <= 2;
    }
}
