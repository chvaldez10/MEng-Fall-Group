package tictactoe;

public class Referee {
    private Player xPlayer;
    private Player oPlayer;
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    public void runTheGame() {
        // set the opponent
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);

        board.display(); // show empty board
        
     // test code - test cases:
// 		board.addMark(0, 0, 'X');
// 		board.addMark(1, 0, 'O');
// 		board.addMark(0, 1, 'X');
// 		board.addMark(0, 0, mark);
// 		board.addMark(0, 0, mark);
// 		board.addMark(0, 0, mark);
        
        System.out.println("\nThe Referee has started the game ...\n");
        xPlayer.play(); // start the game
    }
}
