
public class TicTacToe {
	private int[][] board; //The board. Values determine the state of the board. 0 is null, 1 is O, 2 is X.
	private int player; //The player placeholder. (Who is O, who is X). 1 for O, 2 for X.
	private boolean gameFinished;
	private TicTacToeStack ticStack;
	
	public TicTacToe(){
		ticStack = new TicTacToeStack();
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
	
	public int[][] returnBoard(){
		return board;
	}
	
	//This will place a piece on the board, with first digit being x value and second digit being y value.
	public void play(int x, int y){
		
		if(board[x][y] == 0){
			board[x][y] = player;
			//System.out.println(String.valueOf(board[x][y]));
			
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
			if(!(board[x][0] == 0) && board[x][0] == board[x][1] && board[x][1] == board[x][2]){
				gameFinished = true;
				//System.out.println("You win!");
			}
		}
		
		for(int y = 0; y < 3; y++){
			if(!(board[0][y] == 0) && board[0][y] == board[1][y] && board[0][y] == board[2][y]){
				gameFinished = true;
				//System.out.println("You win!");
			}
		}
		
		if(!(board[0][0] == 0) && board[0][0] == board[1][1] && board[0][0] == board[2][2]){
			gameFinished = true;
			//System.out.println("You win!");
		}
		
		if(!(board[0][2] == 0) && board[0][2] == board[1][1] && board[0][2] == board[2][0]){
			gameFinished = true;
			//System.out.println("You win!");
		}
	}
}