
public class P4Arcade {

	
	public static void main(String[] args) throws InterruptedException {
		String projPath = System.getProperty("user.dir");
		TetrisBoard tetB = new TetrisBoard();
		tetB.createForm();
		//tetB.tick();
		
		while(!tetB.gameFinished()){
			tetB.tick();
		}
	}

}
