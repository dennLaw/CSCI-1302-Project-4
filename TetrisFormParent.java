import javax.swing.JLabel;

public class TetrisFormParent extends javax.swing.JFrame  {

	public void makeFrame(){}
	
	public void previewQueue(int x, int y){}
	
	public void setShape(int x, int y, int w, int z){}
	
	public void placeBlock(int x, int y){}
	
	public void clearBlockType(){}
	
	public boolean checkPos(int x, int y){ return true; }
	
	public JLabel getBlockRow(int x, int y){ return null; }
	
	public void setBlockRow(JLabel label, int x, int y){}
	
	public void wipeBlock(int x, int y){}
	
	public void setScore(int x){}
	
	public boolean getTwist(){ return true; }
	
	public boolean getRight(){ return true; }
	
	public boolean getLeft(){ return true; }
	
	public boolean getInstaDrop(){ return true; }
	
	public void setBlockPos(int x, int y, int w){}
}
