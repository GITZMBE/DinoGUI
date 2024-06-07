package src.components;

import src.utils.Interval;

public class Player extends Entity {
  private static String[] imagePaths = {".//public//images//dino//dino.png", ".//public//images//dino//dino_left_step.png", ".//public//images//dino//dino_right_step.png"};
  private boolean isJumping;
  private Interval jumpInterval;
  private static final int JUMP_HEIGHT = 150;
  private static final int JUMP_SPEED = 1000;
  private static final double GRAVITY = 0.5;
  private double initialVelocity;
  private double velocity;
  private int initialYPosition;

  public Player(int animationInterval, int initialX, int initialY, int width, int height) {
    super(imagePaths, animationInterval, initialX, initialY, width, height);
    initialYPosition = initialY;
    initialVelocity = Math.sqrt(2 * GRAVITY * JUMP_HEIGHT);
  }

  public void startJump() {
    if (isJumping) return;
    isJumping = true;

    velocity = initialVelocity;

    jumpInterval = new Interval(1000 / JUMP_SPEED, e -> {
      int newYPosition = (int) (getYPosition() - velocity);
      velocity -= GRAVITY;

      if (newYPosition >= initialYPosition) {
        newYPosition = initialYPosition;
        isJumping = false;
        jumpInterval.stop();
      }

      setYPosition(newYPosition);
      
      repaint();
    });
    jumpInterval.start();
  }
}
