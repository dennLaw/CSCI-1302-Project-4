//Blocks to put into queue and play with.
public class Tetrimino {
	/*	Block types from 1-7
	 * 		1.	I
	 * 		2.	J
	 * 		3.	L
	 * 		4.	O
	 * 		5.	S
	 * 		6.	T
	 * 		7.	Z
	 */
	private int blockType;
	
	public Tetrimino(int type){
		blockType = type;
	}
	
	public int getType(){
		return blockType;
	}
	
}
