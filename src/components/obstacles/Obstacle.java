package src.components.obstacles;

import java.util.List;

import javax.swing.JPanel;

import src.components.Entity;
import src.components.Player;
import src.managers.CardManager;
import src.utils.CollitionChecker;
import src.utils.Interval;

public class Obstacle extends Entity {
  private CardManager cardManager;
  private Player player;
  private CollitionChecker collitionChecker = new CollitionChecker();
  private List<Obstacle> obstacles;
  private static final int SPEED = 1000;
  private JPanel panel;
  private Interval interval;
  public boolean isOffPage = false;
  private boolean gameHasStarted;

  public Obstacle(List<Obstacle> obstacles, Player player, JPanel panel, String[] imagePaths, boolean gameHasStarted, CardManager cardManager, int animationInterval, int initialX, int initialY, int width, int height) {
    super(imagePaths, animationInterval, initialX, initialY, width, height);
    this.cardManager = cardManager;
    this.obstacles = obstacles;
    this.player = player;
    this.panel = panel;
    this.gameHasStarted = gameHasStarted;
  };

  public void startMoving(int ticSpeed) {
    interval = new Interval(1000 / SPEED, e -> {
      setXPosition(getXPosition() - ticSpeed);
       
      if (xPosition + width < 0 || !gameHasStarted) {
        panel.remove(this);
        isOffPage = true;
        interval.stop();
      }
      collitionChecker.checkCollisions(obstacles, player, cardManager);
    });
    interval.start();
  }
}
