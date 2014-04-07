import java.util.*;

public class TicTacToeStack {

	private ArrayList<TicTacToeBoard> stack;
	
	public TicTacToeStack(){
		stack = new ArrayList<TicTacToeBoard>(10);
	}
	
	public void push(TicTacToeBoard board){
		stack.add(board);
	}
	
	public TicTacToeBoard pop(){
		TicTacToeBoard board = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		
		return board;
	}
	
	public TicTacToeBoard peek(){
		return stack.get(stack.size() - 1);
	}
	
	public boolean isEmpty(){
		return stack.isEmpty();
	}
	
	public int size(){
		return stack.size();
	}
}
