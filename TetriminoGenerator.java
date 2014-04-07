
public class TetriminoGenerator {

		public TetriminoGenerator(){
			
		}
		
		public TetriminoQueue generateQueue(int x){
			TetriminoQueue tetQ = new TetriminoQueue();
			
			for(int i = 0; i < x; i++){
				tetQ.enqueue(new Tetrimino((int)(Math.random()*6) + 1));
			}
			
			return tetQ;
		}
}
