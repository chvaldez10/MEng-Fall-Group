package tictactoe;

public class Player {
    private String name;
    private Board board;

    private Player opponent;
    private char mark;

    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void play() {

    }

    public void makeMove() {}

    public void setOpponent() {

    }

}
