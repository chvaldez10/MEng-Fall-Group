package tictactoe;

public class BlockingPlayer extends Player{
	public BlockingPlayer(String name, char mark) {
		super(name, mark);
	}
	
	@Override
	public void play() {
	}
	
	@Override
	public void makeMove() {}
}
