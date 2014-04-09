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
	public void tick() throws InterruptedException{
		
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
				
				Thread.sleep(400);


				System.out.println("MoveDown");

			}
			
			testPrint();
		}
	}
	
	public void checkGameFinish(){
		if(tet == null){
			gameFinish = true;
		}


		//testPrint();
	

		
		if(playingBoard[4][0]%10 != 0 && playingBoard[4][0] != 1 || playingBoard[5][0]%10 != 0 && playingBoard[5][0] != 1 ){
			gameFinish = true;
		}

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
		int blockNum = 0;
		for(int y = 22; y > -1; y--){
			for(int x = 0; x < 10; x++){
				//Checks for blocks in play and then moves them down one.
				if(playingBoard[x][y]/10 == 1){
					blockNum++;
					playingBoard[x][y+1] = 10 + tet.getType();
					guiBoard.moveDown(blockNum);
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
			guiBoard.setShape(1, 1, 5, 0);
			guiBoard.setShape(2, 1, 5, 1);
			guiBoard.setShape(3, 1, 5, 2);
			guiBoard.setShape(4, 1, 5, 3);
		}
		else if(tet.getType() == 2){
			playingBoard[5][0] = 12;
			playingBoard[5][1] = 12;
			playingBoard[5][2] = 12;
			playingBoard[4][2] = 12;
			guiBoard.setShape(1, 2, 5, 0);
			guiBoard.setShape(2, 2, 5, 1);
			guiBoard.setShape(3, 2, 5, 2);
			guiBoard.setShape(4, 2, 4, 2);
		}
		else if(tet.getType() == 3){
			playingBoard[4][0] = 13;
			playingBoard[4][1] = 13;
			playingBoard[4][2] = 13;
			playingBoard[5][2] = 13;
			guiBoard.setShape(1, 3, 4, 0);
			guiBoard.setShape(2, 3, 4, 1);
			guiBoard.setShape(3, 3, 4, 2);
			guiBoard.setShape(4, 3, 5, 2);
		}
		else if(tet.getType() == 4){
			playingBoard[4][0] = 14;
			playingBoard[5][0] = 14;
			playingBoard[4][1] = 14;
			playingBoard[5][1] = 14;
			guiBoard.setShape(1, 4, 4, 0);
			guiBoard.setShape(2, 4, 5, 0);
			guiBoard.setShape(3, 4, 4, 1);
			guiBoard.setShape(4, 4, 5, 1);
		}
		else if(tet.getType() == 5){
			playingBoard[6][0] = 15;
			playingBoard[5][0] = 15;
			playingBoard[5][1] = 15;
			playingBoard[4][1] = 15;
			guiBoard.setShape(1, 5, 6, 0);
			guiBoard.setShape(2, 5, 5, 0);
			guiBoard.setShape(3, 5, 5, 1);
			guiBoard.setShape(4, 5, 4, 1);
		}
		else if(tet.getType() == 6){
			playingBoard[4][0] = 16;
			playingBoard[5][0] = 16;
			playingBoard[6][0] = 16;
			playingBoard[5][1] = 16;
			guiBoard.setShape(1, 6, 4, 0);
			guiBoard.setShape(2, 6, 5, 0);
			guiBoard.setShape(3, 6, 6, 0);
			guiBoard.setShape(4, 6, 5, 1);
		}
		else{
			playingBoard[4][0] = 17;
			playingBoard[5][0] = 17;
			playingBoard[5][1] = 17;
			playingBoard[6][1] = 17;
			guiBoard.setShape(1, 7, 4, 0);
			guiBoard.setShape(2, 7, 5, 0);
			guiBoard.setShape(3, 7, 5, 1);
			guiBoard.setShape(4, 7, 6, 1);
		}
		
		blockInPlay = true;
	}
	
	//Takes 4 xy-values and places the blocks into TetrisBlock[][] accordingly. First digit is x-value, second digit is y-value, third digit is blockValue.
	public void blockPlace(){
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 24; y++){
				if(playingBoard[x][y]/10 == 1){
					guiBoard.placeBlock(x, y);
					playingBoard[x][y] = tet.getType();
				}
			}
		}
		
		checkLineClear();
		guiBoard.clearBlockType();
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
		else{
			addScore(lc*lc*10000);
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
		boolean twisted = false;
		
		for(int y = 0; y < 24; y++){
			for(int x = 0; x < 10; x++){
				if(playingBoard[x][y]/10 == 1){
					if(xy1 == -1){
						xy1 = x*10+y;
					}
					else if(xy1 == -1){
						xy2 = x*10+y;
					}
					else if(xy1 == -1){
						xy3 = x*10+y;
					}
					else{
						xy4 = x*10+y;
					}
				}
			}
		}
		
		if(tet.getType() == 1){
			int distanceFromSide = 3;
			int distanceFromSide2 = 3;
			
			if(tet.getOrientation() == 1 || tet.getOrientation() == 3){
				if(xy1/10 < 3){
					distanceFromSide = xy1/10;
				}
				else if(xy1/10 > 6){
					distanceFromSide2 = 9 - (int)(xy1/10);
				}
				
				for(int y = xy1%10; y > xy1%10+4; y++){
					
					for(int x = xy1/10 - distanceFromSide; x <= xy1/10; x++){
						if(distanceFromSide2 == 3){
							if(!twisted && playingBoard[x][y]/10 == 1 || playingBoard[x][y]/10 == 1 && playingBoard[x+1][y]/10 == 1 || playingBoard[x+1][y]/10 == 1  && playingBoard[x+2][y]/10 == 1 || playingBoard[x+2][y]/10 == 1  && playingBoard[x+3][y]/10 == 1 || playingBoard[x+3][y]/10 == 1){
								playingBoard[xy1/10][xy1%10] = 0;
								playingBoard[xy2/10][xy2%10] = 0;
								playingBoard[xy3/10][xy3%10] = 0;
								playingBoard[xy4/10][xy4%10] = 0;
								
								playingBoard[x][y] = 10 + tet.getType();
								playingBoard[x+1][y] = 10 + tet.getType();
								playingBoard[x+2][y] = 10 + tet.getType();
								playingBoard[x+3][y] = 10 + tet.getType();
								
								twisted = true;
								tet.changeOrientation();
							}
						}
					}
					
					for(int x = xy1/10; x <= xy1/10 + distanceFromSide2; x++){
						if(distanceFromSide == 3){
							if(!twisted && playingBoard[x-3][y]%10 == 0 || playingBoard[x-3][y]/10 == 1 && playingBoard[x-2][y]%10 == 0  || playingBoard[x-2][y]/10 == 1 && playingBoard[x-1][y]%10 == 0  || playingBoard[x-1][y]/10 == 1 && playingBoard[x][y]%10 == 0 || playingBoard[x][y]/10 == 1){
								playingBoard[xy1/10][xy1%10] = 0;
								playingBoard[xy2/10][xy2%10] = 0;
								playingBoard[xy3/10][xy3%10] = 0;
								playingBoard[xy4/10][xy4%10] = 0;
								
								playingBoard[x-3][y] = 10 + tet.getType();
								playingBoard[x-2][y] = 10 + tet.getType();
								playingBoard[x-1][y] = 10 + tet.getType();
								playingBoard[x][y] = 10 + tet.getType();
								
								twisted = true;
								tet.changeOrientation();
							}
						}
					}
				}
			}
			else if(tet.getOrientation() == 2 || tet.getOrientation() == 4){
				if(xy1%10 < 3){
					distanceFromSide = xy1%10;
				}
				if(xy1%10 > 20){
					distanceFromSide2 = 23 - xy1%10;
				}
				
				for(int x = xy1/10; x < xy1/10 + 4; x++){
					
					for(int y = xy1%10; y <= xy1%10 + distanceFromSide2; y++){
						if(distanceFromSide == 3){
							if(!twisted && playingBoard[x][y-3]%10 == 0 || playingBoard[x][y-3]/10 == 1 && playingBoard[x][y-2]%10 == 0  || playingBoard[x][y-2]/10 == 1 && playingBoard[x][y-1]%10 == 0  || playingBoard[x][y-1]/10 == 1 && playingBoard[x][y]%10 == 0 || playingBoard[x][y]/10 == 1){
								playingBoard[xy1/10][xy1%10] = 0;
								playingBoard[xy2/10][xy2%10] = 0;
								playingBoard[xy3/10][xy3%10] = 0;
								playingBoard[xy4/10][xy4%10] = 0;
								
								playingBoard[x][y-3] = 10 + tet.getType();
								playingBoard[x][y-2] = 10 + tet.getType();
								playingBoard[x][y-1] = 10 + tet.getType();
								playingBoard[x][y] = 10 + tet.getType();
								
								twisted = true;
								tet.changeOrientation();
							}
						}
					}
				}
			}
		}
		else if(tet.getType() == 2){
			if(tet.getOrientation() == 1){
				if(xy4/10 != 9){
					if(playingBoard[xy2/10-1][xy2%10] == 0 && playingBoard[xy4/10+1][xy4%10] == 0){
						playingBoard[xy2/10-1][xy2%10] = 10 + tet.getType();
						playingBoard[xy4/10+1][xy4%10] = 10 + tet.getType();
						
						playingBoard[xy1/10][xy1%10] = 0;
						playingBoard[xy2/10][xy2%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
			else if(tet.getOrientation() == 2){
				if(playingBoard[xy1/10][xy1%10-1] == 0 && playingBoard[xy1/10+1][xy1%10-1] == 0){
					playingBoard[xy1/10][xy1%10-1] = 10 + tet.getType();
					playingBoard[xy1/10+1][xy1%10-1] = 10 + tet.getType();
					
					playingBoard[xy3/10][xy3%10] = 0;
					playingBoard[xy4/10][xy4%10] = 0;
					
					tet.changeOrientation();
				}
			}
			else if(tet.getOrientation() == 3){
				if(xy1/10 != 0){
					if(playingBoard[xy1/10-1][xy1%10] == 0 && playingBoard[xy3/10+1][xy3%10] == 0){
						playingBoard[xy1/10-1][xy1%10] = 10 + tet.getType();
						playingBoard[xy3/10+1][xy3%10] = 10 + tet.getType();
						
						playingBoard[xy3/10][xy3%10] = 0;
						playingBoard[xy4/10][xy4%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
			else if(tet.getOrientation() == 4){
				if(playingBoard[xy2/10][xy2%10+1] == 0 && playingBoard[xy3/10][xy3%10-1] == 0){
					playingBoard[xy2/10][xy2%10+1] = 10 + tet.getType();
					playingBoard[xy3/10][xy3%10-1] = 10 + tet.getType();
					
					playingBoard[xy1/10][xy1%10] = 0;
					playingBoard[xy2/10][xy2%10] = 0;
					
					tet.changeOrientation();
				}
			}
		}
		else if(tet.getType() == 3){
			if(tet.getOrientation() == 1){
				if(xy2/10 != 0){
					if(playingBoard[xy2/10-1][xy2%10] == 0 && playingBoard[xy3/10-1][xy3%10] == 0 && playingBoard[xy2/10+1][xy2%10] == 0){
						playingBoard[xy2/10-1][xy2%10] = 10 + tet.getType();
						playingBoard[xy3/10-1][xy3%10] = 10 + tet.getType();
						playingBoard[xy2/10+1][xy2%10] = 10 + tet.getType();

						playingBoard[xy1/10][xy1%10] = 0;
						playingBoard[xy3/10][xy3%10] = 0;
						playingBoard[xy4/10][xy4%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
			else if(tet.getOrientation() == 2){
				if(playingBoard[xy1/10][xy1%10-1] == 0 && playingBoard[xy2/10][xy2%10-1] == 0 && playingBoard[xy3/10-1][xy3%10-2] == 0){
					playingBoard[xy1/10][xy1%10-1] = 10 + tet.getType();
					playingBoard[xy2/10][xy2%10-1] = 10 + tet.getType();
					playingBoard[xy3/10-1][xy3%10-2] = 10 + tet.getType();
					
					playingBoard[xy1/10][xy1%10] = 0;
					playingBoard[xy3/10][xy3%10] = 0;
					playingBoard[xy4/10][xy4%10] = 0;
					
					tet.changeOrientation();
				}
			}
			else if(tet.getOrientation() == 3){
				if(xy1/10 != 0){
					if(playingBoard[xy3/10-1][xy3%10] == 0 && playingBoard[xy3/10-2][xy3%10] == 0){
						playingBoard[xy3/10-1][xy3%10] = 10 + tet.getType();
						playingBoard[xy3/10-2][xy3%10] = 10 + tet.getType();
								
						playingBoard[xy1/10][xy1%10] = 0;
						playingBoard[xy4/10][xy4%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
			else if(tet.getOrientation() == 4){
				if(playingBoard[xy3/10][xy3%10-1] == 0 && playingBoard[xy3/10][xy3%10-2] == 0){
					playingBoard[xy3/10][xy3%10-1] = 10 + tet.getType();
					playingBoard[xy3/10][xy3%10-2] = 10 + tet.getType();
							
					playingBoard[xy1/10][xy1%10] = 0;
					playingBoard[xy2/10][xy2%10] = 0;
					
					tet.changeOrientation();
				}
			}
		}
		else if(tet.getType() == 4){
			//This block is a square.
		}
		else if(tet.getType() == 5){
			if(tet.getOrientation() == 1 || tet.getOrientation() == 3){
				if(playingBoard[xy1/10][xy1%10-1] == 0 && playingBoard[xy2/10][xy2%10+1] == 0){
					playingBoard[xy1/10][xy1%10-1] = 10 + tet.getType();
					playingBoard[xy2/10][xy2%10+1] = 10 + tet.getType();
					
					playingBoard[xy3/10][xy3%10] = 0;
					playingBoard[xy4/10][xy4%10] = 0;
					
					tet.changeOrientation();
				}
			}
			else if(tet.getOrientation() == 2 || tet.getOrientation() == 4){
				if(xy2/10 != 0){
					if(playingBoard[xy3/10][xy3%10-1] == 0 && playingBoard[xy2/10-1][xy2%10] == 0){
						playingBoard[xy3/10][xy3%10-1] = 10 + tet.getType();
						playingBoard[xy2/10-1][xy2%10] = 10 + tet.getType();
						
						playingBoard[xy3/10][xy3%10] = 0;
						playingBoard[xy4/10][xy4%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
		}
		else if(tet.getType() == 6){
			if(tet.getOrientation() == 1){
				if(playingBoard[xy2/10][xy2%10-1] == 0){
					playingBoard[xy2/10][xy2%10 - 1] = 10 + tet.getType();
					playingBoard[xy3/10][xy3%10] = 0;
					
					tet.changeOrientation();
				}
			}
			else if(tet.getOrientation() == 2){
				if(xy3/10 != 9){
					if(playingBoard[xy3/10+1][xy3%10] == 0){
						playingBoard[xy3/10+1][xy3%10] = 10 + tet.getType();
						playingBoard[xy4/10][xy4%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
			else if(tet.getOrientation() == 3){
				if(xy3%10+1 != 24){
					if(playingBoard[xy3/10][xy3%10+1] == 0){
						playingBoard[xy3/10][xy3%10 + 1] = 10 + tet.getType();
						playingBoard[xy2/10][xy2%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
			else if(tet.getOrientation() == 4){
				if(xy2/10 != 0){
					if(playingBoard[xy2/10-1][xy2%10] == 0){
						playingBoard[xy2/10-1][xy2%10] = 10 + tet.getType();
						playingBoard[xy1/10][xy1%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
		}
		else if(tet.getType() == 7){
			if(tet.getOrientation() == 1 || tet.getOrientation() == 3){
				if(playingBoard[xy1/10][xy1%10+1] == 0 && playingBoard[xy2/10][xy2%10-1] == 0){
					playingBoard[xy1/10][xy1%10+1] = 10 + tet.getType();
					playingBoard[xy2/10][xy2%10-1] = 10 + tet.getType();
					
					playingBoard[xy3/10][xy3%10] = 0;
					playingBoard[xy4/10][xy4%10] = 0;
					
					tet.changeOrientation();
				}
			}
			else if(tet.getOrientation() == 2 || tet.getOrientation() == 4){
				if(xy2/10 != 9){
					if(playingBoard[xy3/10][xy3%10-1] == 0 && playingBoard[xy2/10+1][xy2%10] == 0){
						playingBoard[xy3/10][xy3%10-1] = 10 + tet.getType();
						playingBoard[xy2/10+1][xy2%10] = 10 + tet.getType();
						
						playingBoard[xy3/10][xy3%10] = 0;
						playingBoard[xy4/10][xy4%10] = 0;
						
						tet.changeOrientation();
					}
				}
			}
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
