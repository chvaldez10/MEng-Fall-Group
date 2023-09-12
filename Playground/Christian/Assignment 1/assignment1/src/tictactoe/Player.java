package tictactoe;

import java.util.Scanner;

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

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }


    public void play() {
        makeMove();
        board.display();
    }

    public void makeMove() {
        Scanner sc = new Scanner(System.in);

        // row mark
        System.out.print(name + ", what row should your next "
                + mark + " be placed in? ");
        int tmpMarkRow = sc.nextInt();

        // column mark
        System.out.println();
        System.out.print(name + ", what column should your next "
                + mark + " be placed in? ");
        int tmpMarkColumn = sc.nextInt();
        System.out.println();

        board.addMark(tmpMarkRow, tmpMarkColumn, mark);

    }
}
