public class P4Arcade {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		TetrisBoard tetB = new TetrisBoard();
		tetB.createForm();

		//tetB.tick();
		
		while(!tetB.gameFinished()){
			tetB.tick();
		}
	}

}
