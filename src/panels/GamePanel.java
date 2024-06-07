package src.panels;

import java.awt.Graphics;
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
import src.components.ui.StageLabel;
import src.managers.CardManager;
import src.managers.ScoreManager;
import src.managers.SpeedManager;
import src.managers.StageManager;
import src.utils.CalculateGroundLevel;
import src.utils.CollitionChecker;
import src.utils.Interval;
import src.utils.RandomInt;

public class GamePanel extends Panel {
  private CollitionChecker collitionChecker = new CollitionChecker();
  private SpeedManager speedManager = new SpeedManager();
  private StageManager stageManager;
  private RandomInt randomInt = new RandomInt();
  private List<Obstacle> obstacles = new ArrayList<>();
  private Scoreboard scoreBoard;
  private Player player;
  private boolean gameHasStarted = false;
  private Interval obstacleInterval;
  private int lastStageScore = 0;

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
    player = new Player(distanceFromWall, CalculateGroundLevel.calculate(playerHeight, this.getHeight()), playerWidth, playerHeight);
    add(player);
  };

  private void startObstacleScene() {
    int obstacleIntervalDelay = 3500;
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
      obstacle = new Bird(obstacles, player, this, gameHasStarted, cardManager, this.getWidth(), CalculateGroundLevel.calculate(obstacleHeight, this.getHeight()), obstacleWidth, obstacleHeight);
    } else {
      obstacleWidth = 25;
      obstacleHeight = 60;
      obstacle = new Cactus(obstacles, player, this, gameHasStarted, cardManager, this.getWidth(), CalculateGroundLevel.calculate(obstacleHeight, this.getHeight()), obstacleWidth, obstacleHeight);
    }
    speedManager.startSpeedIncrementation();
    int ticSpeed = speedManager.speed;
    obstacle.startMoving(ticSpeed);
    this.add(obstacle);
    return obstacle;
  }

  private void addScoreBoard() {
    scoreBoard = new Scoreboard(scoreManager, this.getWidth(), this.getWidth() - 200, 10, 200, 32);
    this.add(scoreBoard);
  };

  private void initializeStageLabel() {
    StageLabel stageLabel = new StageLabel("Stage", 16, 0, this);
    stageManager = new StageManager(this, stageLabel);
    this.setLayout(null);
    this.add(stageLabel);
    repaint();
    revalidate();
  }

  private void checkStageChange() {
    int score = scoreManager.getScore();
    if (score != lastStageScore && score % 100 == 0) {
      stageManager.startAnimation(score);
      lastStageScore = score;
    }
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
    initializeStageLabel();

    revalidate();
    repaint();

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

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (gameHasStarted) {
      checkStageChange();
    }
  }

  protected void resize() {
    if (player == null) return;
    this.remove(player);
    addPlayer();
    repaint();
    revalidate();
  };
}