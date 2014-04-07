
public class TicTacToeBoard {
	
	private int[][] board;
	
	public TicTacToeBoard(int[][] bBoard){
		board = bBoard;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public void setBoard(int[][] bBoard){
		board = bBoard;
	}
}
