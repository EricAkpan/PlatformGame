package myPong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//this class deals with everything that has to do with the ball
public class Ball {
  private int ballXpos;
  private int ballYpos;
  private int acc;
  //private boolean pressSpace = false;
  private int speed;
  //private boolean reSpeed = true;
  public boolean canJump = true;
  private int ground;
  public boolean touchLeft = false;
  public boolean touchTop = false;
  public boolean touchRight = false;
  private boolean touchingTop = false;
  private boolean touchFloor = false;
  private boolean space = false;
  public boolean jumpUp = false;
  public boolean touchGround = false;
  private boolean above = true ;
  public int size;
  private boolean always = false;
  public Ball() {
    ballYpos = 100;
    ballXpos = 5;
    acc = 1;
    speed = -15;
    ground = 765;
    size = 15;
  }
  
  //ball jumps up
  //can only jump when canJump is true
  public void jump(Blocks block) {
    if(canJump) {
      ballYpos += speed;   
      speed += acc;
    }  
    //hitGround(); 
  }
  /*
  public void doubleJump() {
    if(canJump) {
      ballYpos += speed;   
      speed += acc;
    }
    if(jumpAgain) {
      speed = -10;
      jumpAgain = false;
      canJump = true;
    }
    hitGround();
  }
  */
  
  //lets the ball be able to jump
  public void letJump(boolean hop) {
    canJump = hop;
    if(hop) {
      speed = -15;
      touchGround = false;
    }
    //pressSpace = true;
  }
  
  // if the ball is touching the top  the object then this method will let the ball jump again
  public void jumpAgain(Blocks block) {
    if(touchingTop) {
      speed = -15;
      canJump = true;
      jump(block);
      touchingTop = false;
    }
  }
  
  // if the ball hits the ground then ball won't move further down 
  public void hitGround() {
    if(ballYpos > ground) {
      ballYpos = ground; 
      canJump = false;
      speed = 0;
      touchGround  = true;
    }
    if(touchGround) {
      canJump = false;
      above = false;
    }
    //System.out.println("Touch Ground = " + touchGround);
  }
  
  
  // check if the ball hit the enemy brick or not
  public boolean hitEnemy(Brick enemy) {
    if(always) {
      return true;
    }
    if(new Rectangle(ballXpos,ballYpos,size,size).intersects(new Rectangle(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getLength()))) {
    return true;
    }else {
    return false; 
    }
  }
  
  
  public void hitBlock1(Blocks block) {
    int leftWall = block.getX() - size;
    int rightWall = block.getX() + block.getWidth();
    int ceil = block.getY() - size;
    int floor = block.getY() + block.getLength();
    
    touchLeft = ballXpos >= leftWall && ballYpos >= ceil + 2 && ballYpos <= floor - 2 && ballXpos < leftWall + size;
    touchRight = ballXpos <= rightWall && ballYpos >= ceil + 2 && ballYpos <= floor - 2 && ballXpos > rightWall - size;
    touchTop = ballXpos <= rightWall - 10 && ballXpos >= leftWall + 10 && ballYpos >= ceil && ballYpos <= ceil + size;
    touchFloor = ballXpos <= rightWall - 10 && ballXpos >= leftWall + 10 && ballYpos >= floor - size && ballYpos <= floor;
    System.out.println("------------------------------------------------");
    System.out.println("Ball Y position =  " + (ballYpos));
    System.out.println("Prev Ball position = " + (ballYpos - (speed - 1)));
    if(touchTop) {
       canJump = false;
       ballYpos = ceil;
       speed = 0;
       touchGround = false;
       above = false;
    } 
    if(touchLeft) {
      ballXpos = leftWall;
    }
    if(touchRight) {
      ballXpos = rightWall;
    }
    if(touchFloor) {
      ballYpos = floor;
      speed = 1;
    }
    
    //if the player is not touching the block then let it fall off
    if(!touchTop) {
      canJump = true;
    }
    hitGround();
    System.out.println("Can Jump = " + canJump);
    System.out.println("Above = " + above);
    System.out.println("Touch Top = " + touchTop);
    System.out.println("Speed: " + speed);
    System.out.println();
      
  }
  
  
  
  /*
  // this method makes sure that the ball won't pass through the object
  public void hitBlock(Blocks floor) {
    
    // this method will check if the ball is touching the object from the left, right or top
    // if the ball is touching the object at all then the ball will not pass the through the object
    
    Rectangle recFloor = new Rectangle(floor.getX(),floor.getY(),floor.getWidth(),floor.getLength());
    Rectangle recBall = new Rectangle(ballXpos,ballYpos,15,15);
    int ceil = floor.getY() - size;
    int leftWall = floor.getX() - size;
    int rightWall = floor.getX() + floor.getWidth();
    boolean touch = recBall.intersects(recFloor);
    if(!space && touch && ((ballXpos <= (leftWall + size)))) {
      touchLeft = true;
    }
    if(touchLeft) {
      ballXpos = leftWall;
    }
    if(touchLeft && ballYpos < ceil) {
      touchLeft = false;
      jumpUp = true;
    }
    if(jumpUp && ballYpos > ceil && ballXpos < floor.getX() && ballXpos > leftWall) {
      touchLeft = true;
      jumpUp = false;
    }
    if(!space && (touch)&& (ballXpos >= (floor.getX())) && (ballXpos < (rightWall - size))) {
      touchTop = true;
    }
    if(touchTop) {
      ballYpos = ceil;
      canJump = false;
    }
    if((touchTop) && ((ballXpos < floor.getX()) || (ballXpos > rightWall))) {
      touchTop = false;
      speed = 1;
      canJump = true;
    }
    if(!space && !touchLeft && !touchTop && touch) {
      touchRight = true;
    }
    if(touchRight && ballYpos < ceil) {
      touchRight = false;
      jumpUp = true;
    }
    if(jumpUp && ballYpos > ceil && ballXpos >= (rightWall - size) &&
        ballXpos <  (rightWall + size)) {
      
      touchRight = true;
      jumpUp = false;
    }
    if(touchRight) {
      ballXpos = rightWall;
    }
    
    jumpAgain(floor);
    //System.out.println("Touching: " + recBall.intersects(recFloor));
    //System.out.println("Touch Top: " + touchTop + "  " + touchingTop);
   // System.out.println("Touch Right: " + touchRight);
   // System.out.println("Touch Left: " + touchLeft);
    
   /*if((ballYpos < 680) &&(ballYpos > 660) && (ballXpos > (floor.getX())) && (ballXpos < (floor.getX() + 100))) {
     ballYpos = 660;
     canJump = false;
     reSpeed = true;
     if(pressSpace) {
       canJump = true;
       speed = -15;
       pressSpace = false;
     }
   }
   if((ballXpos < (floor.getX())) || (ballXpos > (floor.getX() + 100))) {
     if(reSpeed) {
       canJump = true;
       speed = 1;
       reSpeed = false;
     }
   }
   
  }
*/
  
  
  public void moveRight(Blocks block) {
    if(ballXpos >= (1000 - size)) {
      ballXpos = 1000;
    }else if (!touchLeft){
      ballXpos += 10;
    }
    
    
  }
  public void moveLeft(Blocks block) {
    if(ballXpos > 0 && !touchRight) {
      ballXpos -= 10;
    }
    //if(!touchTop) {
    //  canJump = true;
    //}
   //hitBlock1(block);
  }
  public void leftJump(int spe, int times) {
    if(times == 0) {
      speed = spe;
    }
    if(canJump) {
      ballYpos += speed;   
      speed += acc;
    }
    hitGround();
    times++;
  }
  public void setTouchLeft(boolean toc) {
    touchLeft = toc;
  }
  
  public void setTouchRight(boolean toc) {
    touchRight = toc;
  }
  
  public void setTouchTop(boolean toc) {
    if(touchTop) {
      touchingTop = true;
    }
    touchTop = toc;
  }
  public void setSpace(boolean space) {
    this.space = space;
  }
  public int getSpeed() {
    return speed;
  }
  public int getX() {
    return ballXpos;
  }
  public int getY() {
    return ballYpos;
  }
  public void paintBall(Graphics g) {
    g.setColor(Color.black);
    g.fillOval(ballXpos, ballYpos, size, size);   
  }
  
  /*public void hitObj() {
  if(ballYpos > objYpos) {
    ballYpos = objYpos; 
    canJump = false;
    speed = -25;
  }
}*/

}
