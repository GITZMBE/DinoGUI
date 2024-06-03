package src.obstacles;

import javax.swing.JPanel;

import src.GUI;

public class Bird extends Obstacle {
  private int imageIndex = 0;
  public static String[] imagePaths = {".//res//images//bird_wing_up.png", ".//res//images//bird_wing_down.png"};
  public String imagePath = imagePaths[imageIndex & imagePaths.length];

  public Bird(GUI gui, JPanel panel, boolean gameHasStarted, int initialX, int initialY, int width, int height) {
    super(gui, panel, imagePaths, gameHasStarted, initialX, initialY, width, height);
  }
};
