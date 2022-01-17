package myPong;

import javax.swing.JFrame;

public class Main {
  public static void main(String []args) {
    JFrame game = new JFrame();
    game.setBounds(0, 0, 1000, 800 );
    game.setTitle("My Game");   
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GameMaker make = new GameMaker();
    game.setResizable(false);
    game.add(make);
    game.setVisible(true);
  }

}
