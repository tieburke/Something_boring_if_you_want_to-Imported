/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import java.util.ArrayList;

import frc.robot.util.Pixy2CCC.Block;

public class Pixy2Obj {

    private Pixy2 pixy;

    private int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
    public final static int TARGROT = 7;

    public Pixy2Obj() {
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init();
    }

    public Pixy2 getPixy() {
		  return pixy;
    }


    private int x, y, area, sig, width, height;
    private int xCount, yCount, areaCount, sigCount, widthCount, heightCount;

    public void updateValues() {
        Block block = getLargestBlock();
        if(block == null) {
            xCount++; yCount++; areaCount++; sigCount++; widthCount++; heightCount++;
            cleanValues();
            return;
        }

        if(block.getX() == 0) {xCount++;} else {x = block.getX(); xCount = 0;}
        if(block.getY() == 0) {yCount++;} else {y = block.getY(); yCount = 0;}
        if(block.getHeight() == 0 || 
           block.getWidth() == 0) {areaCount++;} else {area = block.getHeight() * block.getWidth(); areaCount = 0;}
        if(block.getSignature() == 0) {sigCount++;} else {sig = block.getSignature(); sigCount = 0;}
        if(block.getWidth() == 0) {widthCount++;} else {width = block.getWidth(); widthCount = 0;}
        if(block.getHeight() == 0) {heightCount++;} else {height = block.getHeight(); heightCount = 0;}
        
        cleanValues();
    }

    private void cleanValues() {
        if(xCount > 9)    {x = 0;}
        if(yCount > 9)    {y = 0;}
        if(areaCount > 9) {area = 0;}
        if(sigCount > 9)  {sig = 0;}
        if(widthCount > 9) {width = 0;}
        if(heightCount > 9) {height = 0;}
    }

    public int getCount(int sig) {
        switch (sig){
            case 1:
                return count1;
            case 2:  
                return count2;
            case 3:
                return count3;
            case 4:
                return count4;
            default:
                return 0;
       }    
    }

    private Block getLargestBlock() {
        int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG_ALL, 25);
        ArrayList<Block> blocks = pixy.getCCC().getBlocks();
        Block largestBlock = null;

        if (blockCount <= 0 || blocks == null) {
            return null;
        }

        for (Block block : blocks) {
            if (largestBlock == null) {
                largestBlock = block;
            } else if (block.getWidth() * block.getHeight() > largestBlock.getWidth() * largestBlock.getHeight()) {
                largestBlock = block;
            }
        }

        return largestBlock;
    }
    
    public int getPixyX() {
        return x;
    }

    public int getPixyY() {
        return y;
    }

    public int getPixyArea() {
        return area;
    }

    public boolean pixySig(int targSig){
        return sig == targSig;
    }

    public int getSig() {
        return sig;
    }

    public int getWidth(){
      return width;
    }

    public int getHeight(){
      return height;
    }

   public void updateCounts(int sig){
        switch (sig){
            case 1:
                count1 += 1;
                break;
            case 2:  
                count2 += 1;
                break;
            case 3:
                count3 += 1;
                break;
            case 4:
                count4 += 1;
                break;
            default:
                break;
       }    
   }

   public void resetCounts(){
       count1 = 0;
       count2 = 0;
       count3 = 0;
       count4 = 0;
   }

   /*if(latch == 0){
      pixy.updateCounts(OGSIG);
      latch++;
    }
    if(latch == 1){
      if(OGSIG == 1){
        if((pixy.getSig() == OGSIG + 1) && (pixy.getCount(OGSIG + 1) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG + 2) && (pixy.getCount(OGSIG + 2) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG + 3) && (pixy.getCount(OGSIG + 3) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG) && (pixy.getCount(OGSIG + 1) == pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if(pixy.getCount(OGSIG) >= Pixy2Obj.TARGROT)
          latch = 2;
      }
      else if(OGSIG == 2){
        if((pixy.getSig() == OGSIG + 2) && (pixy.getCount(OGSIG + 2) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG + 1) && (pixy.getCount(OGSIG + 1) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG - 1) && (pixy.getCount(OGSIG - 1) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG) && (pixy.getCount(OGSIG + 1) == pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if(pixy.getCount(OGSIG) >= Pixy2Obj.TARGROT)
          latch = 2;
      }
      else if(OGSIG == 3){
        if((pixy.getSig() == OGSIG + 1) && (pixy.getCount(OGSIG + 1) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG - 2) && (pixy.getCount(OGSIG - 2) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG - 1) && (pixy.getCount(OGSIG - 1) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG) && (pixy.getCount(OGSIG + 1) == pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if(pixy.getCount(OGSIG) >= Pixy2Obj.TARGROT)
          latch = 2;
      }
      else if(OGSIG == 4){
        if((pixy.getSig() == OGSIG - 3) && (pixy.getCount(OGSIG - 3) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG - 2) && (pixy.getCount(OGSIG - 2) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG - 1) && (pixy.getCount(OGSIG - 1) < pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if((pixy.getSig() == OGSIG) && (pixy.getCount(OGSIG - 3) == pixy.getCount(OGSIG)) && (pixy.getCount(OGSIG) < Pixy2Obj.TARGROT))
          pixy.updateCounts(pixy.getSig());
        else if(pixy.getCount(OGSIG) >= Pixy2Obj.TARGROT)
          latch = 2;
      }
    }*/

}