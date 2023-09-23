package tictactoe;

public class HumanPlayer extends Player{
	public HumanPlayer(String name, char mark) {
		super(name, mark);
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
                if (super.isValidIndex(index)) return index;
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

}
