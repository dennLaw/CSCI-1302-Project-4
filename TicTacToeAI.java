import java.util.Random;


public class TicTacToeAI extends TicTacToePlayer{
	private int difficulty; //Will hold difficulty of AI. 0 will be "Toddler", 1 will be "Intermediate", 2 will be "The Only Winning Move Is Not To Play", 3 is "Softlock"
	private int xCoord;
	private int yCoord;
	private Random math;
	
	public TicTacToeAI(int dif){
		difficulty = dif;
		xCoord = 0;
		yCoord = 0;
	}
	
	//Will be what the AI does to make a choice of what move it wants to do. Returns a two-digit value with first digit being X and second digit being Y.
	public void makeChoice(int[][] board){
		if(difficulty == 0){
			toddlerC(board);
		}
		else if(difficulty == 1){
			intermediateC(board);
		}
		else if(difficulty == 2){
			warGamesC(board);
		}
		else{
			softLockC(board);
		}
	}
	
	public void resetAI(){
		xCoord = 0;
		yCoord = 0;
	}
	
	public int returnXChoice(){
		return xCoord;
	}
	
	public int returnYChoice(){
		return yCoord;
	}
	
	public void toddlerC(int[][] board){
		
		xCoord = (int)(Math.random()*3);
		yCoord = (int)(Math.random()*3);
	}
	
	public void intermediateC(int[][] board){
		if(board[0][0] == 1 && board[0][1] == 1 || board[1][2] == 1 && board[2][2] == 1 || board[2][0] == 1 && board[1][1] == 1){
			xCoord = 0;
			yCoord = 2;
		}
		else if(board[0][1] == 1 && board[0][2] == 1 || board[1][0] == 1 && board[2][0] == 1 || board[1][1] == 1 && board[2][2] == 1){
			xCoord = 0;
			yCoord = 0;
		}
		else if(board[0][0] == 1 && board[0][2] == 1 || board[1][1] == 1 && board[2][1] == 1){

			xCoord = 0;
			yCoord = 1;
		}
		else if(board[1][0] == 1 && board[1][1] == 1 || board[2][2] == 1 && board[0][2] == 1){

			xCoord = 1;
			yCoord = 2;
			
		}
		else if(board[1][0] == 1 && board[1][2] == 1 || board[0][1] == 1 && board[2][1] == 1 || board[0][0] == 1 && board[2][2] == 1 || board[0][2] == 1 && board[2][0] == 1){

			xCoord = 1;
			yCoord = 1;
		}
		else if(board[1][1] == 1 && board[1][2] == 1 || board[0][0] == 1 && board[2][0] == 1){

			xCoord = 1;
			yCoord = 0;
		}
		else if(board[2][0] == 1 && board[2][1] == 1 || board[1][1] == 1 && board[1][2] == 1 || board[0][0] == 1 && board[1][1] == 1){

			xCoord = 2;
			yCoord = 2;
		}
		else if(board[2][0] == 1 && board[2][2] == 1 || board[0][1] == 1 && board[1][1] == 1){

			xCoord = 2;
			yCoord = 1;
		}
		else if(board[2][1] == 1 && board[2][2] == 1 || board[1][0] == 1 && board[0][0] == 1 || board[0][2] == 1 && board[1][1] == 1){

			xCoord = 2;
			yCoord = 0;
		}
		
		else{
			xCoord = (int)(Math.random()*3);
			yCoord = (int)(Math.random()*3);
		}
	}
	
	public void warGamesC(int[][] board){
		xCoord = 0;
		yCoord = xCoord;
	}
	
	public void softLockC(int[][] board){
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				if(!(board[x][y] == 0)){
					xCoord = x;
					yCoord = y;
				}
			}
		}
		
		xCoord = 2;
		yCoord = 2;
	}
}
