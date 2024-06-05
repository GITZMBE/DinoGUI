package src.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import src.components.Player;
import src.components.Scoreboard;
import src.components.obstacles.Bird;
import src.components.obstacles.Cactus;
import src.components.obstacles.Obstacle;
import src.managers.CardManager;
import src.managers.ScoreManager;
import src.utils.CollitionChecker;
import src.utils.Interval;
import src.utils.RandomInt;

public class GamePanel extends Panel {
  private CollitionChecker collitionChecker = new CollitionChecker();
  private RandomInt randomInt = new RandomInt();
  private List<Obstacle> obstacles = new ArrayList<>();
  private Scoreboard scoreBoard;
  private Player player;
  private int groundLevel = 100;
  private boolean gameHasStarted = false;
  private Interval obstacleInterval;

  public GamePanel(CardManager cardManager, ScoreManager scoreManager) {
    super(null, cardManager, scoreManager);
  };

  protected void initializePanel() {
    startGame();
  };

  private void addPlayer() {
    int playerWidth = 50;
    int playerHeight = 50;
    int distanceFromWall = 100;
    player = new Player(distanceFromWall, this.getHeight() - groundLevel - playerHeight, playerWidth, playerHeight);
    add(player);
    repaint();
  };

  private void startObstacleScene() {
    int obstacleIntervalDelay = 5000;
    obstacleInterval = new Interval(obstacleIntervalDelay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Obstacle obsticle = initiateObstacle();
        obstacles.add(obsticle);
        collitionChecker.checkCollisions(obstacles, player, cardManager);
      }
    });
    obstacleInterval.start();
  };

  private Obstacle initiateObstacle() {
    int obstacleWidth;
    int obstacleHeight;
    int objInt = randomInt.generate(0, 2);
    Obstacle obstacle;
    if (objInt == 1) {
      obstacleWidth = 60;
      obstacleHeight = 45;
      obstacle = new Bird(obstacles, player, this, gameHasStarted, cardManager, this.getWidth(), this.getHeight() - groundLevel - 100 - obstacleHeight, obstacleWidth, obstacleHeight);
    } else {
      obstacleWidth = 25;
      obstacleHeight = 60;
      obstacle = new Cactus(obstacles, player, this, gameHasStarted, cardManager, this.getWidth(), this.getHeight() - groundLevel - obstacleHeight, obstacleWidth, obstacleHeight);
    }
    obstacle.startMoving();
    this.add(obstacle);
    this.repaint();
    return obstacle;
  }

  private void addScoreBoard() {
    scoreBoard = new Scoreboard(scoreManager, this.getWidth(), this.getWidth() - 200, 10, 200, 32);
    this.add(scoreBoard);
    this.repaint();
  };

  public void gameOver() {
    gameHasStarted = false;
    scoreBoard.stop();
    obstacleInterval.stop();
    for (Obstacle obstacle : obstacles) {
      this.remove(obstacle);
    }
    obstacles.clear();
    cardManager.showPanel(CardManager.GAME_OVER_PANEL);
    this.repaint();
  };

  private void startGame() {
    gameHasStarted = true;
    removeAll();
    addScoreBoard();
    addPlayer();
    startObstacleScene();

    revalidate();

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (gameHasStarted && e.getKeyCode() == KeyEvent.VK_UP) {
          player.startJump();
          repaint();
        }
      }
    });
    
    requestFocus();
  };

  protected void resize() {
    if (player == null) return;
    this.remove(player);
    addPlayer();
    repaint();
    revalidate();
  };
}