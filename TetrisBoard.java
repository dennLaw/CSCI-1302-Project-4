public class TetrisBoard {
	
	//Make a Queue for Tetriminos here. Call the queue tetQ.
	private TetriminoQueue tetQ;
	private TetriminoGenerator tetGen;
	private Tetrimino tet; // Holder for the current paying block.
	//Keeps track of placed blocks. The integer value inside will determine what "color" block is there in the second digit.
	//first digit represents whether the block is in play or not. 0 for "Not in play" and 1 for "In play".
	private int[][] playingBoard;
	private boolean gameFinish;
	private int score;
	private boolean blockInPlay;
	private boolean collisionOnce; //Used for checking if a single tick passed and a block is blocked from going down twice.
	private boolean collisionTwice;
	private boolean placeBlock;
	private TetrisForm guiBoard;
	
	public TetrisBoard(){
		gameFinish = false;
		blockInPlay = false;
		collisionOnce = false;
		collisionTwice = false;
		placeBlock = false;
		score = 0;
		tetGen = new TetriminoGenerator();
		guiBoard = new TetrisForm();
		
		//Construct the Tetrimino queue here.
		
		tetQ = tetGen.generateQueue(200);
		
		//Construct the playingBoard here.
		
		playingBoard = new int[10][24];
		
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 24; y++){
				playingBoard[x][y] = 0;
			}
		}
	}
	
	public void createForm() {
		
		guiBoard.makeFrame();
		
	}
	
	//Ticks every interval. All game logic occurs here.
	public void tick(){
		
		if(!blockInPlay){
			tet = getBlock();
			
			checkGameFinish();
			//Handle loading in the block here.
			if(!gameFinish){
				loadBlock();
			}
		}
		
		if(!gameFinish){
			checkCollision();
			
			//CollisionOnce is set by checkCollision. If it detects that a static block is underneath a playable block, it flags "CollisionOnce" and prevents the piece from incrementing downwards.
			if(!collisionOnce && blockInPlay){
				moveDown();
<<<<<<< HEAD
				guiBoard.tickDown();
				System.out.println("MoveDown");
=======
>>>>>>> a2f28c33f3773a5d53361be4453cd691cc59f3ce
			}
			
			testPrint();
		}
	}
	
	public void checkGameFinish(){
		if(tet == null){
			gameFinish = true;
		}
<<<<<<< HEAD
		

		//testPrint();
	
=======
		
		if(playingBoard[4][0]%10 != 0 && playingBoard[4][0] != 1 || playingBoard[5][0]%10 != 0 && playingBoard[5][0] != 1 ){
			gameFinish = true;
		}
>>>>>>> a2f28c33f3773a5d53361be4453cd691cc59f3ce
	}
	
	public void testImage(String path){
		
		String blockLocation = path;
<<<<<<< HEAD
		//guiBoard.addBlock(blockLocation);
		

		if(playingBoard[4][0]%10 != 0 && playingBoard[4][0] != 1 || playingBoard[5][0]%10 != 0 && playingBoard[5][0] != 1 ){
			gameFinish = true;
		}

=======
		guiBoard.addBlock(blockLocation);
>>>>>>> a2f28c33f3773a5d53361be4453cd691cc59f3ce
	}
	
	public void testPrint(){
		for(int y = 0; y < 24; y++){
			for(int x = 0; x < 10; x++){
				if(playingBoard[x][y] == 0){
					System.out.print("O");
				}
				else{
					System.out.print("X");
				}
			}
			System.out.println("");
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
	
	public void checkCollision(){
		if(collisionOnce){
			collisionTwice = true;
			collisionOnce = false;
		}
		
		for(int y = 23; y > -1; y--){
			for(int x = 0; x < 10; x++){
				//Checks for blocks in play and then checks if there is space below it.
				if(playingBoard[x][y]/10 == 1){
					if(y+1 == 24 || playingBoard[x][y+1]%10 != 0 && playingBoard[x][y+1]/10 == 0){
						if(collisionTwice){
							placeBlock = true;
						}
						else{
							collisionOnce = true;
						}
					}
				}
			}
		}
		
		if(placeBlock && collisionTwice){
			blockPlace();
			collisionTwice = false;
			placeBlock = false;
			System.out.println("Block Placed");
		}
		else{
			collisionTwice = false;
			placeBlock = false;
		}
	}
	
	public void moveDown(){
		for(int y = 22; y > -1; y--){
			for(int x = 0; x < 10; x++){
				//Checks for blocks in play and then moves them down one.
				if(playingBoard[x][y]/10 == 1){
					playingBoard[x][y+1] = 10 + tet.getType();
					//guiBoard.moveDown(x, y + 1);
					playingBoard[x][y] = 0;
				}
			}
		}
	}
	
	//Inspects a block from the queue. To be used for displaying "Blocks to come".
	public Tetrimino inspectBlock(int e){
		return tetQ.element(e);
	}
	
	public void loadBlock(){
		if(tet.getType() == 1){
			playingBoard[5][0] = 11;
			playingBoard[5][1] = 11;
			playingBoard[5][2] = 11;
			playingBoard[5][3] = 11;
			guiBoard.setShape(1, 5, 0);
		}
		else if(tet.getType() == 2){
			playingBoard[5][0] = 12;
			playingBoard[5][1] = 12;
			playingBoard[5][2] = 12;
			playingBoard[4][2] = 12;
			guiBoard.setShape(2, 4, 0);
		}
		else if(tet.getType() == 3){
			playingBoard[4][0] = 13;
			playingBoard[4][1] = 13;
			playingBoard[4][2] = 13;
			playingBoard[5][2] = 13;
			guiBoard.setShape(3, 4, 0);
		}
		else if(tet.getType() == 4){
			playingBoard[4][0] = 14;
			playingBoard[5][0] = 14;
			playingBoard[4][1] = 14;
			playingBoard[5][1] = 14;
			guiBoard.setShape(4, 4, 0);
		}
		else if(tet.getType() == 5){
			playingBoard[6][0] = 15;
			playingBoard[5][0] = 15;
			playingBoard[5][1] = 15;
			playingBoard[4][1] = 15;
			guiBoard.setShape(5, 4, 0);
		}
		else if(tet.getType() == 6){
			playingBoard[4][0] = 16;
			playingBoard[5][0] = 16;
			playingBoard[6][0] = 16;
			playingBoard[5][1] = 16;
			guiBoard.setShape(6, 4, 0);
		}
		else{
			playingBoard[4][0] = 17;
			playingBoard[5][0] = 17;
			playingBoard[5][1] = 17;
			playingBoard[6][1] = 17;
			guiBoard.setShape(7, 4, 0);
		}
		
		blockInPlay = true;
	}
	
	//Takes 4 xy-values and places the blocks into TetrisBlock[][] accordingly. First digit is x-value, second digit is y-value, third digit is blockValue.
	public void blockPlace(){
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 24; y++){
				if(playingBoard[x][y]/10 == 1){
					playingBoard[x][y] = tet.getType();
				}
			}
		}
		
		guiBoard.placeBlock();
		checkLineClear();
		blockInPlay = false;
	}
	
	//Returns the block line that's been cleared. Modifies the board as necessary. This should run in a while loop until -1 is achieved.
	public void checkLineClear(){
		boolean isDone = false;
		int countBlock = 0;
		int countLinesCleared = 0;
		
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
						countLinesCleared++;
					}
				}
			}
		}
		
		lineClearScore(countLinesCleared);
	}
	
	public void clearLine(int lineToClear){
		int[][] tempBoard = playingBoard;
		
		for(int y = lineToClear; y > 0; y--){
			for(int x = 0; x < 10; x++){
				playingBoard[x][y] = tempBoard[x][y-1];
			}
		}
	}
	
	public void lineClearScore(int lc){
		if(lc == 1){
			addScore(1000);
		}
		else if(lc == 2){
			addScore(5000);
		}
		else if(lc == 3){
			addScore(15000);
		}
		else if(lc == 4){
			addScore(50000);
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
	
	//Twist
	public void Twist(){
		int xy1 = -1;
		int xy2 = -1;
		int xy3 = -1;
		int xy4 = -1;
		
		for(int y = 0; y < 24; y++){
			for(int x = 0; x < 10; x++){
				if(playingBoard[x][y]/10 == 1){
					if(xy1 != -1){
						xy1 = x*10+y;
					}
					else if(xy1 != -1){
						xy2 = x*10+y;
					}
					else if(xy1 != -1){
						xy3 = x*10+y;
					}
					else{
						xy4 = x*10+y;
					}
				}
			}
		}
		
		if(tet.getType() == 1){
			
		}
		else if(tet.getType() == 2){
			
		}
		else if(tet.getType() == 3){
			
		}
		else if(tet.getType() == 4){
			//This block is a square.
		}
		else if(tet.getType() == 5){
			
		}
		else if(tet.getType() == 6){
			
		}
		else{
			
		}
	}
	
	//Move Right
	public void moveRight(){
		boolean canMove = true;
		
		for(int y = 22; y > -1; y--){
			
			if(canMove && playingBoard[9][y]/10 == 1){
				canMove = false;
			}
			
			for(int x = 1; x < 10; x++){
				if(canMove && playingBoard[x-1][y]%10 != 0 && playingBoard[x-1][y]/10 == 0 && playingBoard[x][y]/10 == 1){
					canMove = false;
				}
			}
		}
		
		if(canMove){
			for(int y = 22; y > -1; y--){
				for(int x = 0; x < 9; x++){
					if(playingBoard[x][y]/10 == 1){
						playingBoard[x+1][y] = 10 + tet.getType();
						playingBoard[x][y] = 0;
					}
				}
			}
		}
	}
	
	//Move Left
	public void moveLeft(){
		boolean canMove = true;
		
		for(int y = 22; y > -1; y--){
			
			if(canMove && playingBoard[0][y]/10 == 1){
				canMove = false;
			}
			
			for(int x = 0; x < 9; x++){
				if(canMove && playingBoard[x][y]%10 != 0 && playingBoard[x][y]/10 == 0 && playingBoard[x+1][y]/10 == 1){
					canMove = false;
				}
			}
		}
		
		if(canMove){
			for(int y = 22; y > -1; y--){
				for(int x = 1; x < 10; x++){
					if(playingBoard[x][y]/10 == 1){
						playingBoard[x-1][y] = 10 + tet.getType();
						playingBoard[x][y] = 0;
					}
				}
			}
		}
	}
	
	//Instant Drop
	public void instantDrop(){
		int shortestDrop = 50;
		int lowestPlayingInColumn = 0;
		int lowestPlayingUnit = 0;
		
		for(int x = 0; x < 10; x++){
			lowestPlayingInColumn = 0;
			
			for(int y = 0; y < 23; y--){
				if(playingBoard[x][y]/10 == 1){
					lowestPlayingInColumn = y;
					
					if(y > lowestPlayingUnit){
						lowestPlayingUnit = y;
					}
				}
				
				if(lowestPlayingInColumn != 0 && playingBoard[x][y]%10 != 0 && playingBoard[x][y]/10 == 0){
					if(shortestDrop > y - lowestPlayingInColumn){
						shortestDrop = y - lowestPlayingInColumn;
					}
				}
			}
		}
		
		if(shortestDrop != 50){
			for(int y = 22; y > -1; y--){
				for(int x = 0; x < 10; x++){
					//Checks for blocks in play and then moves them down one.
					if(playingBoard[x][y]/10 == 1){
						playingBoard[x][y+shortestDrop] = 10 + tet.getType();
						playingBoard[x][y] = 0;
					}
				}
			}
		}
		else{
			for(int y = 22; y > -1; y--){
				for(int x = 0; x < 10; x++){
					//Checks for blocks in play and then moves them down one.
					if(playingBoard[x][y]/10 == 1){
						playingBoard[x][y+(23-lowestPlayingUnit)] = 10 + tet.getType();
						playingBoard[x][y] = 0;
					}
				}
			}
		}
	}
}
