package myPong;

import java.awt.Color;
import java.awt.Graphics;

public class Blocks {
  private int blockX;
  private int blockY;
  private int length;
  private int width;
  public Blocks(int x, int y) {
    blockX = x;
    blockY = y;
    length = 50;
    width = 150;
  }
  
  public Blocks(int x, int length, int width) {
    blockX = x;
    blockY = 780 - length;
    this.length = length;
    this.width = width;
  }
  public Blocks(int x, int y, int length, int width) {
    blockX = x;
    blockY = y;
    this.length = length;
    this.width = width;
  }
  
  
    
  
  public void moveLeft() {
    blockX -= 2;
    if(blockX < -100) {
      blockX = 1000;
    }
  }
  public void moveRight() {
    blockX += 1;
  }
  public int getX() {
    return blockX;
  }
  public int getY() {
    return blockY;
  }
  public int getLength() {
    return length;
  }
  public int getWidth() {
    return width;
  }
  public void paintBlock(Graphics g) {
    g.setColor(Color.GRAY);
    g.fillRect(blockX, blockY, width, length);
  }
  public void painting(Graphics g) {
    g.fillRect(blockX, blockY, width, length);
  }

}
