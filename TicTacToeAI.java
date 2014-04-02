
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
		return 0;
	}
	
	public int intermediateC(int[][] board){
		return 0;
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
