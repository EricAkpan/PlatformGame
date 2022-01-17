package myPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameMaker extends JPanel implements KeyListener, ActionListener{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Ball player = new Ball();
  public Timer time;
  private Brick bad = new Brick(1000, 715);
  private Blocks block = new Blocks(300, 660, 40 ,200);
  private KeyEvent b;
  
  private boolean began = false;
  private boolean hit = false;
  private boolean play = false;
  private boolean won = false;
  private boolean start = false;
  
  
  int leftWall = block.getX() - player.size;
  int rightWall = block.getX() + block.getWidth();
  int ceil = block.getY() - player.size;
  int floor = block.getY() + player.size;
  
  
  private Blocks block1 = new Blocks(50,660,100,200);
  
  
  
  
  public int delay = 20;
  

  
  public GameMaker() {
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    time = new Timer(delay, this);
    time.start();
  }
  public void paint(Graphics g) {
    //make background
    
    g.setColor(Color.white);
    g.fillRect(0, 0, 1000, 1000 );
    if(!start) {
      g.setColor(Color.BLACK);
      g.setFont(new Font("seif", Font.BOLD, 60));
      g.drawString("MY GAME ", 310, 370);
      g.setFont(new Font("seif", Font.BOLD, 20));
      g.drawString("Press Enter to Restart ", 340, 410);
      g.setFont(new Font("seif", Font.ITALIC, 15));
      g.drawString("Goal: move ball to the right side of the screen" + 
      " without getting hit by the yellow brick ", 150, 440);
      g.setFont(new Font("seif", Font.ITALIC, 12));
      g.drawString("(Use arrow and space key to move)", 350, 460);

    }
   
    //Game Over
    hit = player.hitEnemy(bad);

    if(hit) {
      play = false;
      began = false;
      g.setColor(Color.BLACK);
      g.setFont(new Font("seif", Font.BOLD, 60));
      g.drawString("GAME OVER!!! ", 300, 370);
      g.setFont(new Font("seif", Font.BOLD, 20));
      g.drawString("Press Enter to Restart ", 390, 410);
    }
    
  //Winning
    if(won) {
      play = false;
      began = false;
      g.setColor(Color.BLACK);
      g.setFont(new Font("seif", Font.BOLD, 60));
      g.drawString("YOU WON!! ", 300, 370);
      g.setFont(new Font("seif", Font.BOLD, 20));
      g.drawString("Press Enter to Restart ", 360, 410);
    }
        
    //enemy
    bad.paintBrick(g);
    
    //block
    block.paintBlock(g);
    
    g.setColor(Color.BLACK);
    block1.painting(g);
    
  //make ball
    player.paintBall(g);
    g.setFont(new Font("seif", Font.ITALIC, 15));
    g.drawString("Goal: move ball to the right side of the screen" + 
    " without getting hit by the yellow brick ", 150, 440);
    g.dispose();  
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // when you press certain type then do X
    
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    //time.start();
    b = e;
    // The if statement check if player hits enter when game isn't playing the game running
    // if so then starts the game 
    if(!began && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
      play = true;
      hit = false;
      won = false;
      start = true;
      bad = new Brick(1000, 715);
      player = new Ball();
    }
    
    //checks if the time in between clicks of space and left button AND space and right button
    // if both of the times in between are long enough and player hits space then runs code
    //!(betweenLeft < 50) && !(betweenRight < 50) &&
   if( (e.getKeyCode() == KeyEvent.VK_SPACE))
    {
       //if player isn't touching the top of the block then player should be allowed to jump
      if(player.touchTop || player.touchGround) {
        player.letJump(true);
      }
      // if player hit space button then ball shouldn't be touch the top of anything
      player.setTouchTop(false);
      player.setSpace(true);
    }
  }
  
  //when the user to stops holding to any key to b's key code should be 0
  @Override
  public void keyReleased(KeyEvent e) {
    
    // nothing should be should be counted for because user isn't holding it anymore
    b.setKeyCode(0);
    //player.setSpace(false);
  }
  
//this method runs every delay time (in milliseconds)
  @Override
  public void actionPerformed(ActionEvent e) {
    time.start();
    
    if(play) {
      began = true;
      
    //player jump when user hits space button
      player.jump(block);
      
      // enemy move left from the right
      //when it hit the block then it goes to the right 
      // side of the screen then move left again (repeat until game is over)
      bad.move(block);

       
     // when player clicks right then ball moves right unless object or wall to the right of it
      
    if(b.getKeyCode() == KeyEvent.VK_RIGHT)
    {     
   // sets touch right to false because it can't be touching the right wall of the object
      // if the ball is moving right
     // player.setTouchRight(false);
      player.moveRight(block);
       
    }
    
    if (player.getX() >= 1000) {
      play = false;
      won = true;
    }
    //when player clicks left then ball moves left unless object or wall to the left of it
    if(b.getKeyCode() == KeyEvent.VK_LEFT )
    { 
      // sets touch right to false because it can't be touching the right wall of the object
      // if the ball is moving right
      player.setTouchLeft(false);
      player.moveLeft(block);
      
    }
    // check if player hit enemy
    // if player hits enemy then the game is over
    player.hitEnemy(bad);
    // checks if player hits block
    // if player hits block then it won't pass through block 
    player.hitBlock1(block);
    if(!player.touchTop) {
      player.hitBlock1(block1);
      }
    }
    
    repaint();
  }

}
