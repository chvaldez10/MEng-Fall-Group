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
        
        System.out.println("The Referee has started the game ...");
        xPlayer.play(); // start the game
    }
}
