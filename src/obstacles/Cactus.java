package src.obstacles;

import javax.swing.JPanel;

import src.GUI;

public class Cactus extends Obstacle {
  public static String[] imagePaths = {".//res//images//cactus.png"};

  public Cactus(GUI gui, JPanel panel, boolean gameHasStarted, int initialX, int initialY, int width, int height) {
    super(gui, panel, imagePaths, gameHasStarted, initialX, initialY, width, height);
  }
}
