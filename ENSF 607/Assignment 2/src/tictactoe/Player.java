package tictactoe;

import java.util.Scanner;

public class Player {
    private String name;
    private Board board;

    private Player opponent;
    private char mark;
    Scanner sc = new Scanner(System.in);

    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
        this.sc = new Scanner(System.in);
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }


    public void play() {
        // board has space and no winner
        if (!board.isFull() && !board.oWins() && !board.xWins()) makeMove();

        if (board.xWins()) {
            System.out.println("X Wins!");
        } else if (board.oWins()){
            System.out.println("O Wins!");
        } else if (board.isFull()){
            System.out.println("Draw!");
        } else {
            board.display();
            opponent.play();
        }
    }

    public void makeMove() {
        int tmpMarkRow = getUserInput("row");
        int tmpMarkColumn = getUserInput("column");
        board.addMark(tmpMarkRow, tmpMarkColumn, mark);
    }

    public int getUserInput(String label){
        int index=-1;
        System.out.print(name + ", what " + label +" should your next "
                + mark + " be placed in? ");

        try {
            index = Integer.parseInt(getKeyboardChar());
            System.out.println();
            if (isValidIndex(index)) return index;
            else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid " + label + ".");
            index = getUserInput(label);
        }
        return index;
    }

    private String getKeyboardChar(){
        if(sc.hasNext()) {
            char c = sc.next().charAt(0);
            return String.valueOf(c);
        }
        return "Not a valid input.";
    }

    public boolean isValidIndex(int index) {
        if (index >=0 && index <=2) return true;
        else return false;
    }
}
