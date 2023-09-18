import java.io.*;

/**
 * The `Game` class represents the main entry point for a tic-tac-toe game.
 * It initializes the game board, appoints a referee, and manages player setup.
 */
public class Game implements Constants {

    private Board theBoard; // Represents the game board.
    private Referee theRef; // Manages the game and checks for game outcomes.

    /**
     * Constructs a new `Game` instance and initializes the game board.
     */
    public Game() {
        theBoard = new Board();
    }

    /**
     * Appoints a referee to manage the game and runs the game.
     *
     * @param r The referee responsible for controlling the game.
     * @throws IOException if there is an error during game setup.
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.runTheGame();
    }

    /**
     * The main method for starting and running a tic-tac-toe game.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException if there is an error during input/output operations.
     */
    public static void main(String[] args) throws IOException {
        Referee theRef;
        Player xPlayer, oPlayer;
        BufferedReader stdin;
        Game theGame = new Game();
        stdin = new BufferedReader(new InputStreamReader(System.in));

        // Prompt for the 'X' player's name and initialize the player.
        System.out.print("\nPlease enter the name of the 'X' player: ");
        String name = stdin.readLine();
        while (name == null) {
            System.out.print("Please try again: ");
            name = stdin.readLine();
        }
        xPlayer = new Player(name, LETTER_X);
        xPlayer.setBoard(theGame.theBoard);

        // Prompt for the 'O' player's name and initialize the player.
        System.out.print("\nPlease enter the name of the 'O' player: ");
        name = stdin.readLine();
        while (name == null) {
            System.out.print("Please try again: ");
            name = stdin.readLine();
        }
        oPlayer = new Player(name, LETTER_O);
        oPlayer.setBoard(theGame.theBoard);

        // Create a referee, set the board, 'X' player, and 'O' player, then appoint the referee.
        theRef = new Referee();
        theRef.setBoard(theGame.theBoard);
        theRef.setoPlayer(oPlayer);
        theRef.setxPlayer(xPlayer);

        theGame.appointReferee(theRef);
    }
}
