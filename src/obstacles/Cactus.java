package src.obstacles;

import java.util.List;

import javax.swing.JPanel;

import src.player.Player;

public class Cactus extends Obstacle {
  public static String[] imagePaths = {".//res//images//cactus.png"};

  public Cactus(List<Obstacle> obstacles, Player player, JPanel panel, boolean gameHasStarted, int initialX, int initialY, int width, int height) {
    super(obstacles, player, panel, imagePaths, gameHasStarted, initialX, initialY, width, height);
  }
}
