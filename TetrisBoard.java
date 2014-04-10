public class TetrisBoard{
	
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
			
				System.out.println("MoveDown");

			}
			
			testPrint();
		}
	}
	
	public void checkGameFinish(){
		if(tet == null){
			gameFinish = true;
		}
		
		if(playingBoard[4][0]%10 != 0 && playingBoard[4][0]/10 != 1 || playingBoard[5][0]%10 != 0 && playingBoard[5][0]/10 != 1 ){
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
		for(int y = 22; y > -1; y--){
			for(int x = 0; x < 10; x++){
				//Checks for blocks in play and then moves them down one.
				if(playingBoard[x][y]/10 == 1){
					playingBoard[x][y+1] = 10 + tet.getType();
					playingBoard[x][y] = 0;
					
					draw();
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
		
			for(int y = 0; y < 24; y++){
				countBlock = 0;
				
				for(int x = 0; x < 10; x++){
					if(playingBoard[x][y]/10 == 0 && playingBoard[x][y]%10 != 0){
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

		
					for (int x = 0; x < 10; x++){			
							boolean clearThis = guiBoard.checkPos(x, lineToClear);
							
							if (clearThis == true){
								guiBoard.wipeBlock(x, lineToClear);
						}
					}
						
					for (int y = lineToClear; y > 0; y--){
						for (int x = 0; x < 10; x++){
								boolean clearThis = guiBoard.checkPos(x, y - 1);
								
								if (clearThis == true){
			guiBoard.setBlockRow(guiBoard.getBlockRow(x, y - 1), x, y);
			}
			}
			}

		
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
		guiBoard.setScore(score);
	}
	
	public int getScore(){
		return score;
	}
	
	//Twist
	public void twist(){
		if(guiBoard.getTwist()){
			int xy1 = -1;
			int xy2 = -1;
			int xy3 = -1;
			int xy4 = -1;
		
			int counter = 0;
			
			for(int y = 0; y < 24; y++){
				for(int x = 0; x < 10; x++){
					if(playingBoard[x][y]/10 == 1){
						if(counter == 0){
							xy1 = x*100+y;
						}
						else if(counter == 1){
							xy2 = x*100+y;
						}
						else if(counter == 2){
							xy3 = x*100+y;
						}
						else{
							xy4 = x*100+y;
						}
						
						counter++;
					}
				}
			}
			//USE A [][] X [][]Y SYSTEM. DOUBLE DIGITS!
			if(xy1 != -1 && xy1%100 > 0){
				if(tet.getType() == 1){
					int distanceFromSide1 = xy1/100;
					int distanceFromSide2 = xy1%100;
					
					if(tet.getOrientation() == 1 || tet.getOrientation() == 3){
						if(distanceFromSide1 > 2){
							if(playingBoard[xy1/100-1][xy1%100] == 0 && playingBoard[xy1/100-2][xy1%100] == 0 && playingBoard[xy1/100-3][xy1%100] == 0){
								
								setZero();
								setValue(xy1, xy1-100, xy1-200, xy1-300);
								
								tet.changeOrientation();
							}
						}
						else{
							if(playingBoard[xy1/100+1][xy1%100] == 0 && playingBoard[xy1/100+2][xy1%100] == 0 && playingBoard[xy1/100+3][xy1%100] == 0){
								
								setZero();
								setValue(xy1+100, xy1+200, xy1+300, xy1);
							
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 2 || tet.getOrientation() == 4){
						if(distanceFromSide2 > 2){
							if(playingBoard[xy1/100][xy1%100-1] == 0 && playingBoard[xy1/100][xy1%100-2] == 0 && playingBoard[xy1/100][xy1%100-3] == 0){
								
								setZero();
								setValue(xy1, xy1-1, xy1-2, xy1-3);
								
								tet.changeOrientation();
							}
						}
						else{
							if(playingBoard[xy1/100][xy1%100+1] == 0 && playingBoard[xy1/100][xy1%100+2] == 0 && playingBoard[xy1/100][xy1%100+3] == 0){
								
								setZero();
								setValue(xy1+1, xy1+2, xy1+3, xy1);
							
								tet.changeOrientation();
							}
						}
					}
				}
				else if(tet.getType() == 2){
					if(tet.getOrientation() == 1){
						if(xy4/100 != 9){
							if(playingBoard[xy2/100-1][xy2%100] == 0 && playingBoard[xy4/100+1][xy4%100] == 0){
								//playingBoard[xy2/100-1][xy2%100] = 10 + tet.getType();
								//playingBoard[xy4/100+1][xy4%100] = 10 + tet.getType();
								
								setZero();
								setValue(xy3-1, xy3, xy3+100, xy3+200);
								
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 2){
						if(playingBoard[xy1/100][xy1%100-1] == 0 && playingBoard[xy1/100+1][xy1%100-1] == 0){
							//playingBoard[xy1/100][xy1%100-1] = 10 + tet.getType();
							//playingBoard[xy1/100+1][xy1%100-1] = 10 + tet.getType();
							
							setZero();
							setValue(xy1+100-1,xy1-1,xy1,xy1+1);
							
							tet.changeOrientation();
						}
					}
					else if(tet.getOrientation() == 3){
						if(xy1/100 != 0){
							if(playingBoard[xy1/100-1][xy1%100] == 0 && playingBoard[xy3/100+1][xy3%100] == 0){
								//playingBoard[xy1/100-1][xy1%100] = 10 + tet.getType();
								//playingBoard[xy3/100+1][xy3%100] = 10 + tet.getType();
								
								setZero();
								setValue(xy1-100,xy1,xy1+100,xy1+101);
								
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 4){
						if(xy3%100 != 0){
							if(playingBoard[xy2/100][xy2%100+1] == 0 && playingBoard[xy3/100][xy3%100-1] == 0){
								//playingBoard[xy2/100][xy2%100+1] = 10 + tet.getType();
								//playingBoard[xy3/100][xy3%100-1] = 10 + tet.getType();
								
								setZero();
								setValue(xy4-100,xy4,xy4-1,xy4-2);
								
								tet.changeOrientation();
							}
						}
					}
				}
				else if(tet.getType() == 3){
					if(tet.getOrientation() == 1){
						if(xy2/100 != 0){
							if(playingBoard[xy2/100-1][xy2%100] == 0 && playingBoard[xy3/100-1][xy3%100] == 0 && playingBoard[xy2/100+1][xy2%100] == 0){
								//playingBoard[xy2/100-1][xy2%100] = 10 + tet.getType();
								//playingBoard[xy3/100-1][xy3%100] = 10 + tet.getType();
								//playingBoard[xy2/100+1][xy2%100] = 10 + tet.getType();
		
								setZero();
								setValue(xy2-99, xy2-100, xy2, xy2+100);
								
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 2){
						if(playingBoard[xy1/100][xy1%100-1] == 0 && playingBoard[xy2/100][xy2%100-1] == 0 && playingBoard[xy2/100][xy2%100+1] == 0){
							//playingBoard[xy1/100][xy1%100-1] = 10 + tet.getType();
							//playingBoard[xy2/100][xy2%100-1] = 10 + tet.getType();
							//playingBoard[xy2/100][xy2%100+1] = 10 + tet.getType();
							
							setZero();
							setValue(xy2-101, xy2-1, xy2, xy2+1);
							
							tet.changeOrientation();
						}
					}
					else if(tet.getOrientation() == 3){
						if(xy1/100 != 0){
							if(playingBoard[xy3/100-1][xy3%100] == 0 && playingBoard[xy3/100-2][xy3%100] == 0){
								//playingBoard[xy3/100-1][xy3%100] = 10 + tet.getType();
								//playingBoard[xy3/100-2][xy3%100] = 10 + tet.getType();
										
								setZero();
								setValue(xy3-200,xy3-100,xy3,xy3-1);
								
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 4){
						if(playingBoard[xy3/100][xy3%100-1] == 0 && playingBoard[xy3/100][xy3%100-2] == 0){
							//playingBoard[xy3/100][xy3%100-1] = 10 + tet.getType();
							//playingBoard[xy3/100][xy3%100-2] = 10 + tet.getType();
									
							setZero();
							setValue(xy3-2,xy3-1,xy3,xy3+100);
							
							tet.changeOrientation();
						}
					}
				}
				else if(tet.getType() == 4){
					//This block is a square.
				}
				else if(tet.getType() == 5){
					if(tet.getOrientation() == 1 || tet.getOrientation() == 3){
						if(playingBoard[xy1/100][xy1%100-1] == 0 && playingBoard[xy2/100][xy2%100+1] == 0){
							//playingBoard[xy1/100][xy1%100-1] = 10 + tet.getType();
							//playingBoard[xy2/100][xy2%100+1] = 10 + tet.getType();
							
							setZero();
							setValue(xy1-1,xy1,xy1+100,xy1+101);
							
							tet.changeOrientation();
						}
					}
					else if(tet.getOrientation() == 2 || tet.getOrientation() == 4){
						if(xy2/100 != 0 && xy3%100 != 0){
							if(playingBoard[xy3/100][xy3%100-1] == 0 && playingBoard[xy2/100-1][xy2%100] == 0){
								//playingBoard[xy3/100][xy3%100-1] = 10 + tet.getType();
								//playingBoard[xy2/100-1][xy2%100] = 10 + tet.getType();
								
								setZero();
								setValue(xy1+100,xy1,xy1+1,xy1-99);
								
								tet.changeOrientation();
							}
						}
					}
				}
				else if(tet.getType() == 6){
					if(tet.getOrientation() == 1){
						if(xy2%100 != 0){
							if(playingBoard[xy2/100][xy2%100-1] == 0){
								//playingBoard[xy2/100][xy2%100 - 1] = 10 + tet.getType();
		
								setZero();
								setValue(xy1,xy1+100,xy1+101,xy1+99);
								
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 2){
						if(xy3/100 != 9){
							if(playingBoard[xy3/100+1][xy3%100] == 0){
								//playingBoard[xy3/100+1][xy3%100] = 10 + tet.getType();
								
								setZero();
								setValue(xy1,xy1+1,xy1-99,xy1+101);
								
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 3){
						if(xy3%100+1 != 24){
							if(playingBoard[xy3/100][xy3%100+1] == 0){
								//playingBoard[xy3/100][xy3%100 + 1] = 10 + tet.getType();
								
								setZero();
								setValue(xy1,xy1+1,xy1+101,xy1+2);
								
								tet.changeOrientation();
							}
						}
					}
					else if(tet.getOrientation() == 4){
						if(xy2/100 != 0){
							if(playingBoard[xy2/100-1][xy2%100] == 0){
								//playingBoard[xy2/100-1][xy2%100] = 10 + tet.getType();
								
								setZero();
								setValue(xy2+100,xy2,xy2-100,xy2+1);
								
								tet.changeOrientation();
							}
						}
					}
				}
				else if(tet.getType() == 7){
					if(tet.getOrientation() == 1 || tet.getOrientation() == 3){
						if(playingBoard[xy1/100][xy1%100+1] == 0 && playingBoard[xy2/100][xy2%100-1] == 0){
							//playingBoard[xy1/100][xy1%100+1] = 10 + tet.getType();
							//playingBoard[xy2/100][xy2%100-1] = 10 + tet.getType();
							
							setZero();
							setValue(xy1+1,xy1,xy1+100,xy1+99);
							
							tet.changeOrientation();
						}
					}
					else if(tet.getOrientation() == 2 || tet.getOrientation() == 4){
						if(xy2/100 != 9){
							if(playingBoard[xy3/100][xy3%100-1] == 0 && playingBoard[xy2/100+1][xy2%100] == 0){
								//playingBoard[xy3/100][xy3%100-1] = 10 + tet.getType();
								//playingBoard[xy2/100+1][xy2%100] = 10 + tet.getType();
								
								setZero();
								setValue(xy1-100,xy1,xy1+1,xy1+101);
								
								tet.changeOrientation();
							}
						}
					}
				}
			}
		}
		
		draw();
	}
	
	//Sets all playing coordinates to zero on the grid.
	public void setZero(){
		for(int y = 23; y > -1; y--){
			for(int x = 0; x < 10; x++){
				if(playingBoard[x][y]/10 == 1){
					playingBoard[x][y] = 0;
				}
			}
		}
	}
	
	//Sets 4 coordinates to playing with the current piece on the grid.
	public void setValue(int xy1, int xy2, int xy3, int xy4){
		playingBoard[xy1/100][xy1%100] = 10 + tet.getType();
		playingBoard[xy2/100][xy2%100] = 10 + tet.getType();
		playingBoard[xy3/100][xy3%100] = 10 + tet.getType();
		playingBoard[xy4/100][xy4%100] = 10 + tet.getType();
		
		System.out.println(xy1 + " " + xy2 + " " + xy3 + " " + xy4);
	}
	
	//Move Right
	public void moveRight(){
		if(guiBoard.getRight()){
			boolean canMove = true;
			
			for(int y = 23; y > -1; y--){
				
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
				for(int y = 23; y > -1; y--){
					for(int x = 9; x > -1; x--){
						if(playingBoard[x][y]/10 == 1){
							playingBoard[x+1][y] = 10 + tet.getType();
							playingBoard[x][y] = 0;
						}
					}
				}
			}
		}
		
		draw();
	}
	
	//Move Left
	public void moveLeft(){
		if(guiBoard.getLeft()){
			boolean canMove = true;
			
			for(int y = 23; y > -1; y--){
				
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
				for(int y = 23; y > -1; y--){
					for(int x = 1; x < 10; x++){
						if(playingBoard[x][y]/10 == 1){
							playingBoard[x-1][y] = 10 + tet.getType();
							playingBoard[x][y] = 0;
						}
					}
				}
			}
			
			draw();
		}
	}
	
	//Instant Drop
	public void instantDrop(){
		/*
		int shortestDrop = 50;
		int lowestPlayingInColumn = 0;
		int lowestPlayingUnit = 0;
		
		for(int x = 0; x < 10; x++){
			lowestPlayingInColumn = 0;
			
			for(int y = 0; y < 23; y++){
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
		
		//By now, lowestPlayingUnit is the y value of the lowest playing unit on the board.
		//ShortestDrop denotes the farthest the block can fall without breaking itself.

		if(shortestDrop != 50){
			for(int y = 23; y > -1; y--){
				for(int x = 0; x < 10; x++){
					//Checks for blocks in play and then moves them down one.
					if(playingBoard[x][y]/10 == 1 && (y+shortestDrop) > -1){
						playingBoard[x][y+shortestDrop] = 10 + tet.getType();
						playingBoard[x][y] = 0;
					}
				}
			}
		}
		else{
			for(int y = 23; y > -1; y--){
				for(int x = 0; x < 10; x++){
					//Checks for blocks in play and then moves them down one.
					if(playingBoard[x][y]/10 == 1 && (y+23-lowestPlayingUnit < 24)){
						playingBoard[x][y+(23-lowestPlayingUnit)] = 10 + tet.getType();
						playingBoard[x][y] = 0;
					}
				}
			}
		}
		*/
		
		//Calling moveDown until collides. Less efficient time and space-wise, but the code above it bugged.
		if(guiBoard.getInstaDrop()){
			checkCollision();
			
			while(!collisionOnce && blockInPlay){
				moveDown();
				
				checkCollision();
			}
		}
		
		draw();
	}
	
	public void draw(){
		int counter = 0;
		
		for(int y = 0; y < 24; y++){
			for(int x = 0; x < 10; x++){
				if(playingBoard[x][y]/10 == 1){
					if(counter == 0){
						guiBoard.setBlockPos(1, x, y);
					}
					else if(counter == 1){
						guiBoard.setBlockPos(2, x, y);
					}
					else if(counter == 2){
						guiBoard.setBlockPos(3, x, y);
					}
					else{
						guiBoard.setBlockPos(4, x, y);
					}
					counter++;
				}
			}
		}
	}
}
