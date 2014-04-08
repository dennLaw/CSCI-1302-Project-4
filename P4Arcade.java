
public class P4Arcade {

	
	public static void main(String[] args) {
		String projPath = System.getProperty("user.dir");
		TetrisBoard tetB = new TetrisBoard();
		tetB.createForm();
		tetB.tick();
		tetB.tick();
		//int i = 0;
		
		/*while(i < 100){
			tetB.tick();
			
			i++;
		}*/
	}

}
