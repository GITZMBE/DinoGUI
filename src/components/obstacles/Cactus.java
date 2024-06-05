package src.components.obstacles;

import java.util.List;

import javax.swing.JPanel;

import src.components.Player;
import src.managers.CardManager;

public class Cactus extends Obstacle {
  public static String[] imagePaths = {".//public//images//cactus//cactus1.png"};

  public Cactus(List<Obstacle> obstacles, Player player, JPanel panel, boolean gameHasStarted, CardManager cardManager, int initialX, int initialY, int width, int height) {
    super(obstacles, player, panel, imagePaths, gameHasStarted, cardManager, initialX, initialY, width, height);
  }
}