package src.player;

import javax.swing.JLabel;
import javax.swing.Timer;

import src.entity.Entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Entity {
  private static String[] imagePaths = {".//res//images//dino.png"};
  private JLabel playerLabel;
  private Timer jumpTimer;
  private int yPosition;
  private boolean isJumping;
  private static final int JUMP_HEIGHT = 150;
  private static final int JUMP_SPEED = 1;
  private static final double GRAVITY = 0.5;
  private double initialVelocity;
  private double velocity;
  private int initialYPosition;

  public Player(int initialX, int initialY, int width, int height) {
    super(imagePaths[0]);
    
    yPosition = initialY;
    initialYPosition = initialY;
    initialVelocity = Math.sqrt(2 * GRAVITY * JUMP_HEIGHT);
  }

  public JLabel getPlayerLabel() {
    return playerLabel;
  }

  public void startJump() {
    if (isJumping) return;
    isJumping = true;

    velocity = initialVelocity;

    jumpTimer = new Timer(JUMP_SPEED, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            yPosition -= velocity;
            velocity -= GRAVITY;

            if (yPosition >= initialYPosition) {
                yPosition = initialYPosition;
                isJumping = false;
                jumpTimer.stop();
            }

            playerLabel.setLocation(playerLabel.getX(), yPosition);
        }
    });
    jumpTimer.start();
  }
}
