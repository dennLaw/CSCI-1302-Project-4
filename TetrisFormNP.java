/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KLZ de Panama
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TetrisFormNP extends javax.swing.JFrame {

    /**
     * Creates new form TetrisForm
     */
    
	int finalBlockType = 0;
	int startingX = 0;
	int startingY = 0;
	int currentX = 0;
	int currentX2 = 0;
	int currentX3 = 0;
	int currentX4 = 0;
	int currentY = 0;
	int currentY2 = 0;
	int currentY3 = 0;
	int currentY4 = 0;
	int width = 0;
	int height = 0;
	boolean leftKeyPress = false;
	boolean rightKeyPress = false;
	boolean instaDrop = false;
	boolean twist = false;
	JLabel[][] placedLabels = new JLabel[10][24];
	
	
	String projPath = System.getProperty("user.dir");
        JLabel blockLabel = new JLabel();
        JLabel block2Label = new JLabel();
        JLabel block3Label = new JLabel();
        JLabel block4Label = new JLabel();
	
        
	public TetrisFormNP() {
		addKeyListener(new MainListener());
        initComponents();
    }
	
    
	
    public void addBlock(int pieceNum, String fileLocation, int xCoord, int yCoord)
    {
    	String addedBlock = fileLocation;
        ImageIcon blockPic = new ImageIcon(addedBlock);
        
        if (pieceNum == 1){
    	blockLabel.setIcon(blockPic);
        LayeredPane.moveToFront(blockLabel);
        
        startingX = 31*xCoord;
        startingY = 31*yCoord;
        currentX = startingX;
        currentY = startingY;
        width = blockPic.getIconWidth();
        height = blockPic.getIconHeight();
        setPosition(blockLabel, startingX, startingY);
        
        blockLabel.setVisible(true);
        }
        
        else if (pieceNum == 2){
    	block2Label.setIcon(blockPic);
        LayeredPane.moveToFront(block2Label);
        
        startingX = 31*xCoord;
        startingY = 31*yCoord;
        currentX2 = startingX;
        currentY2 = startingY;
        width = blockPic.getIconWidth();
        height = blockPic.getIconHeight();
        setPosition(block2Label, startingX, startingY);
        
        block2Label.setVisible(true);
        }
        
        else if (pieceNum == 3){
        	block3Label.setIcon(blockPic);
            LayeredPane.moveToFront(block3Label);
            
            startingX = 31*xCoord;
            startingY = 31*yCoord;
            currentX3 = startingX;
            currentY3 = startingY;
            width = blockPic.getIconWidth();
            height = blockPic.getIconHeight();
            setPosition(block3Label, startingX, startingY);
            
            block3Label.setVisible(true);
            }
        
        else if (pieceNum == 4){
        	block4Label.setIcon(blockPic);
            LayeredPane.moveToFront(block4Label);
            
            startingX = 31*xCoord;
            startingY = 31*yCoord;
            currentX4 = startingX;
            currentY4 = startingY;
            width = blockPic.getIconWidth();
            height = blockPic.getIconHeight();
            setPosition(block4Label, startingX, startingY);
            
            block4Label.setVisible(true);
            }
        
        
    }
    
    
    public void setPosition(JLabel blok, int x, int y){
    	blok.setBounds(x, y, width, height);
    }
    
    
    public void resetPosition(){
    
    blockLabel.setBounds(startingX, startingY, width, height);
    block2Label.setBounds(startingX, startingY, width, height);
    block3Label.setBounds(startingX, startingY, width, height);
    block4Label.setBounds(startingX, startingY, width, height);
    
    }
    
    
    public void moveLeft(){
        
    	
    	System.out.println("move left");
    	
    }   
    
    public void moveRight(){
        
    }
    
    public void drop(){
        
    }
    
    
    public void moveDown(int blockID){
    	
    	if (blockID == 1){
        	currentY = currentY + 31;
        	blockLabel.setBounds(currentX, currentY, width, height);
    	}
    	
    	else if(blockID == 2){
        	currentY2 = currentY2 + 31;
        	block2Label.setBounds(currentX2, currentY2, width, height);
    	}
    	
    	else if(blockID == 3){
        	currentY3 = currentY3 + 31;
        	block3Label.setBounds(currentX3, currentY3, width, height);
    	}
    	
    	else if(blockID == 4){
        	currentY4 = currentY4 + 31;
        	block4Label.setBounds(currentX4, currentY4, width, height);
    	}

    }
    
    public void setBlockPos(int blockID, int xPos, int yPos){
    	
    	if (blockID == 1){
    		currentX = 31*xPos;
        	currentY = 31*yPos;
        	blockLabel.setBounds(currentX, currentY, width, height);
    	}
    	
    	else if(blockID == 2){
    		currentX2 = 31*xPos;
        	currentY2 = 31*yPos;
        	block2Label.setBounds(currentX2, currentY2, width, height);
    	}
    	
    	else if(blockID == 3){
    		currentX3 = 31*xPos;
        	currentY3 = 31*yPos;
        	block3Label.setBounds(currentX3, currentY3, width, height);
    	}
    	
    	else if(blockID == 4){
    		currentX4 = 31*xPos;
        	currentY4 = 31*yPos;
        	block4Label.setBounds(currentX4, currentY4, width, height);
    	}

    }
    
    public void clearBlockType(){
    	
    	finalBlockType = 0;
    	
    }
    
    public boolean checkForBlock(int xCo, int yCo){
    	
    	boolean blockThere = false;
    	String isThere = String.valueOf(placedLabels[xCo][yCo]);
    	if (isThere != null){
    		blockThere = true;
    		System.out.println("block is here");
    	}
    	return blockThere;
    	
    }
    
    public boolean checkPos(int xCo, int yCo){
    	boolean somethingThere = false;
    	
    	String placedBlocks = String.valueOf(placedLabels[xCo][yCo]);
    	
    	if(placedBlocks != "null"){
    		somethingThere = true;
    	}
    	
    	return somethingThere;
    }
    
    

    
    public void wipeBlock(int xCo, int yCo){
    	
    	placedLabels[xCo][yCo].setVisible(false);
    	LayeredPane.remove(placedLabels[xCo][yCo]);
    	placedLabels[xCo][yCo] = null;
    }
    
    
    public void setBlockRow(JLabel block, int xCol, int yRow){
    	placedLabels[xCol][yRow] = block;
    	
    	boolean blockThere = checkPos(xCol, yRow);
    	
    	if (blockThere == true){
    		System.out.println("block is here");
    	block.setBounds(xCol*31, yRow*31, 31, 31);
    	}
    	
    	
    }
    
    public JLabel getBlockRow (int xPos, int yPos){
    	return placedLabels[xPos][yPos];
    }
    
    
    public void placeBlock(int xPos, int yPos){
    
    	resetPosition();
    	JLabel finalBlock = new JLabel();
    	int blockW = 0;
    	int blockH = 0;
    	
    	if (finalBlockType == 1)
    	{
    		ImageIcon blockPic = new ImageIcon(projPath + "\\art\\1.png");
    		finalBlock.setIcon(blockPic);
    		blockW = blockPic.getIconWidth();
    		blockH = blockPic.getIconHeight();
    	}
    	
    	else if (finalBlockType == 2)
    	{
    		ImageIcon blockPic = new ImageIcon(projPath + "\\art\\2.png");
    		finalBlock.setIcon(blockPic);
    		blockW = blockPic.getIconWidth();
    		blockH = blockPic.getIconHeight();
    	}
    	
    	else if (finalBlockType == 3)
    	{
    		ImageIcon blockPic = new ImageIcon(projPath + "\\art\\3.png");
    		finalBlock.setIcon(blockPic);
    		blockW = blockPic.getIconWidth();
    		blockH = blockPic.getIconHeight();
    	}
    	
    	else if (finalBlockType == 4)
    	{
    		ImageIcon blockPic = new ImageIcon(projPath + "\\art\\4.png");
    		finalBlock.setIcon(blockPic);
    		blockW = blockPic.getIconWidth();
    		blockH = blockPic.getIconHeight();
    	}
    	
    	else if (finalBlockType == 5)
    	{
    		ImageIcon blockPic = new ImageIcon(projPath + "\\art\\5.png");
    		finalBlock.setIcon(blockPic);
    		blockW = blockPic.getIconWidth();
    		blockH = blockPic.getIconHeight();
    	}
    	
    	else if (finalBlockType == 6)
    	{
    		ImageIcon blockPic = new ImageIcon(projPath + "\\art\\6.png");
    		finalBlock.setIcon(blockPic);
    		blockW = blockPic.getIconWidth();
    		blockH = blockPic.getIconHeight();
    	}
    	
    	else if (finalBlockType == 7)
    	{
    		ImageIcon blockPic = new ImageIcon(projPath + "\\art\\7.png");
    		finalBlock.setIcon(blockPic);
    		blockW = blockPic.getIconWidth();
    		blockH = blockPic.getIconHeight();
    	}
        
    	LayeredPane.add(finalBlock);
        LayeredPane.moveToFront(finalBlock);
        
        
        finalBlock.setBounds(xPos * 31, yPos * 31, blockW, blockH);
        placedLabels[xPos][yPos] = finalBlock;
        blockLabel.setVisible(false);
        block2Label.setVisible(false);
        block3Label.setVisible(false);
        block4Label.setVisible(false);
    	
    }
    
    
    public void previewQueue(int blockType){
    	
		if (blockType == 1){
			ImageIcon piecePic = new ImageIcon(projPath + "\\art\\1IbeamG.png");
			nextQueue.setIcon(piecePic);
		}
		
		else if (blockType == 2){
			ImageIcon piecePic = new ImageIcon(projPath + "\\art\\2JbeamG.png");
			nextQueue.setIcon(piecePic);
		}
		
		else if (blockType == 3){
			ImageIcon piecePic = new ImageIcon(projPath + "\\art\\3LbeamG.png");
			nextQueue.setIcon(piecePic);
		}
		
		else if (blockType == 4){
			ImageIcon piecePic = new ImageIcon(projPath + "\\art\\4SquareG.png");
			nextQueue.setIcon(piecePic);
		}
		
		else if (blockType == 5){
			ImageIcon piecePic = new ImageIcon(projPath + "\\art\\5SbeamG.png");
			nextQueue.setIcon(piecePic);
		}
		
		else if (blockType == 6){
			ImageIcon piecePic = new ImageIcon(projPath + "\\art\\6TbeamG.png");
			nextQueue.setIcon(piecePic);
		}

		else if (blockType == 7){
			ImageIcon piecePic = new ImageIcon(projPath + "\\art\\7ZbeamG.png");
			nextQueue.setIcon(piecePic);
		}
    	
    }
    
    
    public void setShape(int blockNumber, int blockType, int startX, int startY){
    	
    	if (blockType == 1)
    	{
    		finalBlockType = 1;
    		String block = projPath + "\\art\\1.png";
    			
    			if (blockNumber == 1){
    				addBlock(1, block, startX, startY);
    			}
    			
    			else if (blockNumber == 2){
    				addBlock(2, block, startX, startY);
    			}
    			
    			else if (blockNumber == 3){
    				addBlock(3, block, startX, startY);
    			}
    			
    			else if (blockNumber == 4){
    				addBlock(4, block, startX, startY);
    			}
    	}
    	
    	
    	else if (blockType == 2){
    		finalBlockType = 2;
    		String block = projPath + "\\art\\2.png";
			
    		if (blockNumber == 1){
				addBlock(1, block, startX, startY);
			}
			
			else if (blockNumber == 2){
				addBlock(2, block, startX, startY);
			}
			
			else if (blockNumber == 3){
				addBlock(3, block, startX, startY);
			}
			
			else if (blockNumber == 4){
				addBlock(4, block, startX, startY);
			}
    	}
    	
    	
    	else if (blockType == 3){
    		finalBlockType = 3;
    		String block = projPath + "\\art\\3.png";
			
    		if (blockNumber == 1){
				addBlock(1, block, startX, startY);
			}
			
			else if (blockNumber == 2){
				addBlock(2, block, startX, startY);
			}
			
			else if (blockNumber == 3){
				addBlock(3, block, startX, startY);
			}
			
			else if (blockNumber == 4){
				addBlock(4, block, startX, startY);
			}
    	}
    	
    	
    	else if (blockType == 4){
    		finalBlockType = 4;
    		String block = projPath + "\\art\\4.png";
			
    		if (blockNumber == 1){
				addBlock(1, block, startX, startY);
			}
			
			else if (blockNumber == 2){
				addBlock(2, block, startX, startY);
			}
			
			else if (blockNumber == 3){
				addBlock(3, block, startX, startY);
			}
			
			else if (blockNumber == 4){
				addBlock(4, block, startX, startY);
			}
    	}
    	
    	
    	else if (blockType == 5){
    		finalBlockType = 5;
    		String block = projPath + "\\art\\5.png";
			
    		if (blockNumber == 1){
				addBlock(1, block, startX, startY);
			}
			
			else if (blockNumber == 2){
				addBlock(2, block, startX, startY);
			}
			
			else if (blockNumber == 3){
				addBlock(3, block, startX, startY);
			}
			
			else if (blockNumber == 4){
				addBlock(4, block, startX, startY);
			}    		
    	}
    	
    	
    	else if (blockType == 6){
    		finalBlockType = 6;
    		String block = projPath + "\\art\\6.png";
			
    		if (blockNumber == 1){
				addBlock(1, block, startX, startY);
			}
			
			else if (blockNumber == 2){
				addBlock(2, block, startX, startY);
			}
			
			else if (blockNumber == 3){
				addBlock(3, block, startX, startY);
			}
			
			else if (blockNumber == 4){
				addBlock(4, block, startX, startY);
			}
    	}
    	
    	
    	else if (blockType == 7){
    		finalBlockType = 7;
    		String block = projPath + "\\art\\7.png";
			
    		if (blockNumber == 1){
				addBlock(1, block, startX, startY);
			}
			
			else if (blockNumber == 2){
				addBlock(2, block, startX, startY);
			}
			
			else if (blockNumber == 3){
				addBlock(3, block, startX, startY);
			}
			
			else if (blockNumber == 4){
				addBlock(4, block, startX, startY);
			}
    	}
    	    
    }
    
    public void makeFrame(){
    setSize(980, 930);
    setVisible(true);
    nextQueue.setIcon(null);
    
    LayeredPane.add(blockLabel);
    LayeredPane.add(block2Label);
    LayeredPane.add(block3Label);
    LayeredPane.add(block4Label);
   
    blockLabel.setVisible(false);
    block2Label.setVisible(false);
    block3Label.setVisible(false);
    block4Label.setVisible(false);
    
}    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LayeredPane = new javax.swing.JLayeredPane();
        nextQueue = new javax.swing.JLabel();
        scoreBoard = new javax.swing.JLabel();
        backgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        javax.swing.GroupLayout LayeredPaneLayout = new javax.swing.GroupLayout(LayeredPane);
        LayeredPane.setLayout(LayeredPaneLayout);
        LayeredPaneLayout.setHorizontalGroup(
            LayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        LayeredPaneLayout.setVerticalGroup(
            LayeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );

        getContentPane().add(LayeredPane);
        LayeredPane.setBounds(281, 70, 310, 744);
        getContentPane().add(nextQueue);
        nextQueue.setBounds(624, 70, 110, 140);

        scoreBoard.setForeground(new java.awt.Color(102, 153, 255));
        scoreBoard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(scoreBoard);
        scoreBoard.setBounds(40, 70, 210, 110);

        backgroundImage.setIcon(new javax.swing.ImageIcon(projPath + "\\art\\background_1Piece.png"));
        getContentPane().add(backgroundImage);
        backgroundImage.setBounds(0, 0, 800, 850);

        pack();
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPane;
    private javax.swing.JLabel backgroundImage;
    private javax.swing.JLabel nextQueue;
    private javax.swing.JLabel scoreBoard;
    // End of variables declaration//GEN-END:variables
    
    private class MainListener implements KeyListener{

        public void keyPressed(KeyEvent e){

        	if(e.getKeyCode() == KeyEvent.VK_LEFT){

            	leftKeyPress = true;
            }
            
        	if(e.getKeyCode() == (KeyEvent.VK_RIGHT)){
            	rightKeyPress = true;
            }
            
        	if(e.getKeyCode() == (KeyEvent.VK_UP)){
            	instaDrop = true;
            }
        	if(e.getKeyCode() == (KeyEvent.VK_DOWN)){
        		twist = true;
        	}
        }
        
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == (KeyEvent.VK_LEFT)){
            	leftKeyPress = false;
            }
            
        	if(e.getKeyCode() == (KeyEvent.VK_RIGHT)){
            	rightKeyPress = false;
            }
            
        	if(e.getKeyCode() == (KeyEvent.VK_UP)){
            	instaDrop = false;
            }
        	if(e.getKeyCode() == (KeyEvent.VK_DOWN)){
        		twist = false;
        	}
		}
		public void keyTyped(KeyEvent e){}
	}
    
    public boolean getRight(){
    	if(rightKeyPress){
    		rightKeyPress = false;
    		return true;
    	}
    	return false;
    }
    
    public boolean getLeft(){
    	if(leftKeyPress){
    		leftKeyPress = false;
    		return true;
    	}
    	return false;
    }
    
    public boolean getInstaDrop(){
    	if(instaDrop){
    		instaDrop = false;
    		return true;
    	}
    	return false;
    }
    
    public boolean getTwist(){
    	if(twist){
    		twist = false;
    		return true;
    	}
    	return false;
    }
}
