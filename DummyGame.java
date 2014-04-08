/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KLZ de Panama
 */
public class DummyGame {
    
    public static void main(String args[]){
    	
        TetrisForm tBoard = new TetrisForm();
        tBoard.makeFrame();
        //tBoard.setShape(1);
        for (int i = 0; i <= 10; i++){
        	tBoard.tickDown();
        }
        tBoard.placeBlock();
        //tBoard.setShape(1);
        tBoard.placeBlock();
        //tBoard.tickDown();
        //tBoard.placeBlock();
        //tBoard.tickDown();
        //System.out.println(user.dir);
        //String projPath = System.getProperty("user.dir");
       // System.out.println(projPath);
        
    }
    
}
