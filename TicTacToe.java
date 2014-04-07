
public class TicTacToe {
	private int[][] board; //The board. Values determine the state of the board. 0 is null, 1 is O, 2 is X.
	private int player; //The player placeholder. (Who is O, who is X). 1 for O, 2 for X.
	private boolean gameFinished;
	private TicTacToeStack ticStack;
	
	public TicTacToe(){
		board = new int[3][3];
		player = 1;
		gameFinished = false;
		
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				board[x][y] = 0;
			}
		}
		
		ticStack.push(new TicTacToeBoard(board));
	}
	
	//This will place a piece on the board, with x being a 2 digit number with first digit being x value and second digit being y value.
	public void play(int xy){
		int x = xy/10;
		int y = xy%10;
		if(!(board[x][y] == 0)){
			board[x][y] = player;
			
			//Keeps track of the history of the board as it's changed.
			ticStack.push(new TicTacToeBoard(board));
			
			checkFinished();
			
			if(player == 1){
				player = 2;
			}
			else{
				player = 1;
			}
		}
		else{
			//Show a message saying they cannot select an already played spot.
		}
	}
	
	//Undo an action.
	public void undo(){
		board = ticStack.pop().getBoard();
	}
	
	//Returns true if game is finished.
	public boolean gameFinished(){
		return gameFinished;
	}
	
	public void checkFinished(){
		//Checks if a 3-in-a-row has been made.
		
		for(int x = 0; x < 3; x++){
			if(!(board[x][1] == 0) && board[x][1] == board[x][2] && board[x][1] == board[x][3]){
				gameFinished = true;
			}
		}
		
		for(int y = 0; y < 3; y++){
			if(!(board[1][y] == 0) && board[1][y] == board[2][y] && board[1][y] == board[3][y]){
				gameFinished = true;
			}
		}
		
		if(!(board[1][1] == 0) && board[1][1] == board[2][2] && board[1][1] == board[3][3]){
			gameFinished = true;
		}
		
		if(!(board[1][3] == 0) && board[1][3] == board[2][2] && board[1][3] == board[3][1]){
			gameFinished = true;
		}
	}
}