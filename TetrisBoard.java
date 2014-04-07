public class TetrisBoard {
	
	//Make a Queue for Tetriminos here. Call the queue tetQ.
	private TetriminoQueue tetQ;
	private TetriminoGenerator tetGen;
	private Tetrimino tet; // Holder for the current paying block.
	private int[][] playingBoard; //Keeps track of placed blocks. The integer value inside will determine what "color" block was placed there.
	private boolean gameFinish;
	private int score;
	
	public TetrisBoard(){
		gameFinish = false;
		score = 0;
		tetGen = new TetriminoGenerator();
		
		//Construct the Tetrimino queue here.
		
		tetQ = tetGen.generateQueue(200);
		
		//Construct the playingBoard here.
		
		playingBoard = new int[10][22];
		
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 0; y++){
				playingBoard[x][y] = 0;
			}
		}
	}
	
	//Takes a block from the Tetrimino queue and makes it the new playing block.
	public Tetrimino getBlock(){
		if(tetQ.isEmpty()){
			return null;
		}
		else{
			return tetQ.dequeue();
		}
	}
	
	//Inspects a block from the queue. To be used for displaying "Blocks to come".
	public Tetrimino inspectBlock(int e){
		return tetQ.element(e);
	}
	
	//Takes 4 xy-values and places the blocks into TetrisBlock[][] accordingly. First digit is x-value, second digit is y-value, and third digit is blockValue.
	public void blockPlace(int xyv1, int xyv2, int xyv3, int xyv4){
		playingBoard[xyv1/100][(xyv1/10)%10] = xyv1%100;
		playingBoard[xyv2/100][(xyv2/10)%10] = xyv2%100;
		playingBoard[xyv3/100][(xyv3/10)%10] = xyv3%100;
		playingBoard[xyv4/100][(xyv4/10)%10] = xyv4%100;
	}
	
	//Returns the block line that's been cleared. Modifies the board as necessary. This should run in a while loop until -1 is achieved.
	public void checkLineClear(){
		boolean isDone = false;
		int countBlock = 0;
		
		while(!isDone){
			isDone = true;
			countBlock = 0;
		
			for(int y = 0; y < 22; y++){
				for(int x = 0; x < 10; x++){
					if(!(playingBoard[x][y] == 0)){
						countBlock++;
					}
					
					if(countBlock == 10){
						clearLine(y);
						isDone = false;
					}
				}
			}
		}
	}
	
	public void clearLine(int lineToClear){
		int[][] tempBoard = playingBoard;
		
		for(int y = lineToClear; y > 0; y--){
			for(int x = 0; x < 10; x++){
				playingBoard[x][y] = tempBoard[x][y-1];
			}
		}
	}
	
	public int[][] getBoard(){
		return playingBoard;
	}
	
	public boolean gameFinished(){
		return gameFinish;
	}
	
	public void addScore(int scoreAdd){
		score += scoreAdd;
	}
	
	public int getScore(){
		return score;
	}
}
