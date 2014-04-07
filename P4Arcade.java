
public class P4Arcade {


	public static void main(String[] args) {
		TetrisBoard tetB = new TetrisBoard();
		int i = 0;
		while(i < 100){
			tetB.tick();
			
			i++;
		}
	}

}
