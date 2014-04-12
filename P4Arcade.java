
public class P4Arcade {

	
	public static void main(String[] args) throws InterruptedException {
		String projPath = System.getProperty("user.dir");
		TetrisBoard tetB = new TetrisBoard(4);
		tetB.createForm();
		//tetB.tick();
		
		double timer = System.currentTimeMillis();
		
		while(!tetB.gameFinished()){
			tetB.instantDrop();
			tetB.moveLeft();
			tetB.moveRight();
			tetB.twist();
			
			if(System.currentTimeMillis() - timer > 400){
				tetB.tick();
				
				timer = System.currentTimeMillis();
			}
		}
	}

}
