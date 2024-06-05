package src.utils;

import java.util.List;

import javax.swing.JPanel;

import src.components.Player;
import src.components.obstacles.Obstacle;
import src.components.panels.GamePanel;
import src.managers.CardManager;

public class CollitionChecker {
  @SuppressWarnings("unused")
  private CardManager cardManager = new CardManager();

  public CollitionChecker() {};

  public boolean hasCollided(Player player, Obstacle obstacle) {
    int playerLeftBound = player.getX();
    int playerRightBound = playerLeftBound + player.getWidth();
    int playerTopBound = player.getY();
    int playerBottomBound = playerTopBound + player.getHeight();

    int obstacleLeftBound = obstacle.getX();
    int obstacleRightBound = obstacleLeftBound + obstacle.getWidth();
    int obstacleTopBound = obstacle.getY();
    int obstacleBottomBound = obstacleTopBound + obstacle.getHeight();

    boolean interfereX = obstacleLeftBound <= playerRightBound && obstacleRightBound >= playerLeftBound;
    boolean interfereY = obstacleBottomBound >= playerTopBound && obstacleTopBound <= playerBottomBound;
    boolean collided = interfereX && interfereY;
    return collided;
  }

  public void checkCollisions(List<Obstacle> obstacles, Player player, CardManager cardManager) {
    for (Obstacle obsticle : obstacles) {
      if (hasCollided(player, obsticle)) {
        JPanel currentPanel = cardManager.getCurrentPanel();
        if (currentPanel instanceof GamePanel) {
          ((GamePanel) currentPanel).gameOver();
        }
        break;
      }
    }
  };
}
