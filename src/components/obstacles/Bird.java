package src.components.obstacles;

import java.util.List;

import javax.swing.JPanel;

import src.components.Player;
import src.managers.CardManager;

public class Bird extends Obstacle {
  private static int heightOverGround = 100;
  private int imageIndex = 0;
  public static String[] imagePaths = {".//public//images//bird//bird_wing_up.png", ".//public//images//bird//bird_wing_down.png"};
  public String imagePath = imagePaths[imageIndex % imagePaths.length];

  public Bird(List<Obstacle> obstacles, Player player, JPanel panel, boolean gameHasStarted, CardManager cardManager, int animationInterval, int initialX, int initialY, int width, int height) {
    super(obstacles, player, panel, imagePaths, gameHasStarted, cardManager, animationInterval, initialX, initialY - heightOverGround, width, height);
  }
};
