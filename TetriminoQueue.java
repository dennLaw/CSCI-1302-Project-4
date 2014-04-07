import java.util.*;

public class TetriminoQueue{

		private ArrayList<Tetrimino> queue;
		
		public TetriminoQueue(){
			queue = new ArrayList<Tetrimino>(200);
		}
		
		public Tetrimino element(int e){
			return queue.get(e);
		}
		
		public void enqueue(Tetrimino T){
			queue.add(T);
		}
		
		public Tetrimino dequeue(){
			Tetrimino tet = queue.get(0);
			
			queue.remove(0);
			queue.trimToSize();
			
			return tet;
		}
		
		public Tetrimino first(){
			return queue.get(0);
		}
		
		public boolean isEmpty(){
			return queue.isEmpty();
		}
		
		public int size(){
			return queue.size();
		}
}
