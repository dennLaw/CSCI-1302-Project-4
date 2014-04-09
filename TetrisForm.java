/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KLZ de Panama
 */
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TetrisForm extends javax.swing.JFrame {

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
	JLabel[][] placedLabels = new JLabel[10][24];
	
	
	String projPath = System.getProperty("user.dir");
        JLabel blockLabel = new JLabel();
        JLabel block2Label = new JLabel();
        JLabel block3Label = new JLabel();
        JLabel block4Label = new JLabel();
	
        
	public TetrisForm() {
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
    		currentX = currentX*xPos;
        	currentY = currentY*yPos;
        	blockLabel.setBounds(currentX, currentY, width, height);
    	}
    	
    	else if(blockID == 2){
    		currentX2 = currentX2*xPos;
        	currentY2 = currentY2*yPos;
        	block2Label.setBounds(currentX2, currentY2, width, height);
    	}
    	
    	else if(blockID == 3){
    		currentX = currentX3*xPos;
        	currentY = currentY3*yPos;
        	block3Label.setBounds(currentX3, currentY3, width, height);
    	}
    	
    	else if(blockID == 4){
    		currentX = currentX4*xPos;
        	currentY = currentY4*yPos;
        	block4Label.setBounds(currentX4, currentY4, width, height);
    	}

    }
    
    public void clearBlockType(){
    	
    	finalBlockType = 0;
    	
    }
    
    
    public void wipeBlock(int xCo, int yCo){
    	
    	placedLabels[xCo][yCo].setVisible(false);
    	LayeredPane.remove(placedLabels[xCo][yCo]);
    	
    }
    
    
    public void setBlockRow(JLabel block, int xCol, int yRow){
    	placedLabels[xCol][yRow] = block;
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
    		finalBlockType = 2;
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
    		finalBlockType = 2;
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
    setSize(984, 930);
    setVisible(true);
    
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

        backgroundImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\KLZ de Panama\\Documents\\GitHub\\CSCI-1302-Project-4\\art\\background_0Piece.png")); // NOI18N
        getContentPane().add(backgroundImage);
        backgroundImage.setBounds(0, 0, 850, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TetrisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TetrisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TetrisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TetrisForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TetrisForm().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPane;
    private javax.swing.JLabel backgroundImage;
    // End of variables declaration//GEN-END:variables
}
