package myPong;

import java.awt.Color;
import java.awt.Graphics;

public class Brick {
  private int brickXpos;
  private int brickYpos;
  private int length;
  private int width;
  private int speed;
  public Brick(int x, int y) {
    brickXpos = x;
    brickYpos = y;
    length = 70;
    width = 20;
    speed = -8;
  }
  public void move(Blocks obj) {
    if(brickXpos <= (obj.getX() + obj.getWidth())) {
      speed = -speed;
    }
    
    brickXpos += speed;
    if (speed > 0 && brickXpos >= (1000 - width)) {
      speed = -speed;
    }
    
  }
  public int getX() {
    return brickXpos;
  }
  public int getY() {
    return brickYpos;
  }
  public int getLength() {
    return length;
  }
  public int getWidth() {
    return width;
  }
  
  public void paintBrick(Graphics g) {
    g.setColor(Color.yellow);
    g.fillRect(brickXpos,brickYpos, width, length);
  }
  
}
