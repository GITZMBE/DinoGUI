package src.utils;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.managers.CardManager;
import src.obstacles.Obstacle;
import src.panels.GamePanel;
import src.player.Player;

public class CollitionChecker {
  @SuppressWarnings("unused")
  private CardManager cardManager = new CardManager();

  public CollitionChecker() {};

  public boolean hasCollided(Player player, Obstacle obstacle) {
    JLabel playerLabel = player.getLabel();
    int playerLeftBound = playerLabel.getX();
    int playerRightBound = playerLeftBound + playerLabel.getWidth();
    int playerTopBound = playerLabel.getY();
    int playerBottomBound = playerTopBound + playerLabel.getHeight();

    JLabel obstacleLabel = obstacle.getObstacleLabel();
    int obstacleLeftBound = obstacleLabel.getX();
    int obstacleRightBound = obstacleLeftBound + obstacleLabel.getWidth();
    int obstacleTopBound = obstacleLabel.getY();
    int obstacleBottomBound = obstacleTopBound + obstacleLabel.getHeight();

    boolean interfereX = obstacleLeftBound <= playerRightBound && obstacleRightBound >= playerLeftBound;
    boolean interfereY = obstacleBottomBound >= playerTopBound && obstacleTopBound <= playerBottomBound;
    boolean collided = interfereX && interfereY;
    return collided;
  }

  public void checkCollisions(List<Obstacle> obstacles, Player player, CardManager cardManager) {
    for (Obstacle obsticle : obstacles) {
      if (hasCollided(player, obsticle)) {
        // gameOver();
        JPanel currentPanel = cardManager.getCurrentPanel();
        if (currentPanel instanceof GamePanel) {
          ((GamePanel) currentPanel).gameOver();
        }
        break;
      }
    }
  };
}
