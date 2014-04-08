
public class TicTacToeAI extends TicTacToePlayer{
	private int difficulty; //Will hold difficulty of AI. 0 will be "Toddler", 1 will be "Intermediate", 2 will be "The Only Winning Move Is Not To Play", 3 is "Softlock"
	
	public TicTacToeAI(int dif){
		difficulty = dif;
	}
	
	//Will be what the AI does to make a choice of what move it wants to do. Returns a two-digit value with first digit being X and second digit being Y.
	public int makeChoice(int[][] board){
		if(difficulty == 0){
			return toddlerC(board);
		}
		else if(difficulty == 1){
			return intermediateC(board);
		}
		else if(difficulty == 2){
			return warGamesC(board);
		}
		else{
			return softLockC(board);
		}
	}
	
	public int toddlerC(int[][] board){
		return (int)(Math.random()*3)*10 + (int)(Math.random()*3);
	}
	
	public int intermediateC(int[][] board){
		if(board[0][0] == 1 && board[0][1] == 1 || board[1][2] == 1 && board[2][2] == 1 || board[2][0] == 1 && board[1][1] == 1){
			return 02;
		}
		else if(board[0][1] == 1 && board[0][2] == 1 || board[1][0] == 1 && board[2][0] == 1 || board[1][1] == 1 && board[2][2] == 1){
			return 00;
		}
		else if(board[0][0] == 1 && board[0][2] == 1 || board[1][1] == 1 && board[2][1] == 1){
			return 01;
		}
		else if(board[1][0] == 1 && board[1][1] == 1 || board[2][2] == 1 && board[0][2] == 1){
			return 12;
		}
		else if(board[1][0] == 1 && board[1][2] == 1 || board[0][1] == 1 && board[2][1] == 1 || board[0][0] == 1 && board[2][2] == 1 || board[0][2] == 1 && board[2][0] == 1){
			return 11;
		}
		else if(board[1][1] == 1 && board[1][2] == 1 || board[0][0] == 1 && board[2][0] == 1){
			return 10;
		}
		else if(board[2][0] == 1 && board[2][1] == 1 || board[1][1] == 1 && board[1][2] == 1 || board[0][0] == 1 && board[1][1] == 1){
			return 22;
		}
		else if(board[2][0] == 1 && board[2][2] == 1 || board[0][1] == 1 && board[1][1] == 1){
			return 21;
		}
		else if(board[2][1] == 1 && board[2][2] == 1 || board[1][0] == 1 && board[0][0] == 1 || board[0][2] == 1 && board[1][1] == 1){
			return 20;
		}
		
		return (int)(Math.random()*3)*10 + (int)(Math.random()*3);
	}
	
	public int warGamesC(int[][] board){
		return 0;
	}
	
	public int softLockC(int[][] board){
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				if(!(board[x][y] == 0)){
					return x*10+y;
				}
			}
		}
		
		return 22;
	}
}
