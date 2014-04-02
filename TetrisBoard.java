public class TetrisBoard {
	
	//Make a Queue for Tetriminos here. Call the queue tetQ.
	private Tetrimino tet; // Holder for the current paying block.
	private int[][] playingBoard; //Keeps track of placed blocks. The integer value inside will determine what "color" block was placed there.
	
	public TetrisBoard(){
		/*Construct the Tetrimino queue here.
		 * 
		 * 
		 */
		
		/*Construct the playingBoard here.
		 * 
		 * 
		 */
	}
	
	//Takes a block from the Tetrimino queue and makes it the new playing block.
	public Tetrimino getBlock(){
		if(tetQ.isEmpty()){
			return null;
		}
		else{
			tet = tetQ.first();
			tetQ.dequeue();
			return tet;
		}
	}
	
	//Takes 4 xy-values and places the blocks into TetrisBlock[][] accordingly. First digit is x-value, second digit is y-value, and third digit is blockValue.
	public void blockPlace(int xyv1, int xyv2, int xyv3, int xyv4){
		playingBoard[xyv1/100][(xyv1/10)%10] = xyv1%100;
		playingBoard[xyv2/100][(xyv2/10)%10] = xyv2%100;
		playingBoard[xyv3/100][(xyv3/10)%10] = xyv3%100;
		playingBoard[xyv4/100][(xyv4/10)%10] = xyv4%100;
	}
}
