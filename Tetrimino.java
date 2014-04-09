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
	private int orientation;
	
	public Tetrimino(int type){
		blockType = type;
		orientation = 1;
	}
	
	public int getType(){
		return blockType;
	}
	
	public void changeOrientation(){
		if(orientation != 4){
			orientation++;
		}
		else{
			orientation = 1;
		}
	}
	
	public int getOrientation(){
		return orientation;
	}
}
