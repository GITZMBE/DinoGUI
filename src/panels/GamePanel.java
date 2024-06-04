package src.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.components.ScoreBoard;
import src.managers.CardManager;
import src.obstacles.Bird;
import src.obstacles.Cactus;
import src.obstacles.Obstacle;
import src.player.Player;
import src.utils.CollitionChecker;
import src.utils.Interval;
import src.utils.RandomInt;

public class GamePanel extends JPanel {
  private JFrame frame;
  private CardManager cardManager = new CardManager();
  private CollitionChecker collitionChecker = new CollitionChecker();
  private RandomInt randomInt = new RandomInt();
  private List<Obstacle> obstacles = new ArrayList<>();
  private ScoreBoard scoreBoard;
  private Player player;
  private int groundLevel = 100;
  private boolean gameHasStarted = false;
  private Interval obstacleInterval;

  public GamePanel(JFrame frame) {
    this.frame = frame;
    startGame();
  };

  private void addPlayer() {
    int playerWidth = 50;
    int playerHeight = 50;
    int distanceFromWall = 100;
    player = new Player(".//res//images//dino.png", distanceFromWall, this.getHeight() - groundLevel - playerHeight, playerWidth, playerHeight);
    this.add(player.getPlayerLabel());
    this.repaint();
  };

  private void startObsticleScene() {
    int obstacleIntervalDelay = 5000;
    obstacleInterval = new Interval(obstacleIntervalDelay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Obstacle obsticle = initiateObsticle();
        obstacles.add(obsticle);
        collitionChecker.checkCollisions(obstacles, player);
      }
    });
    obstacleInterval.start();
  };

  private Obstacle initiateObsticle() {
    int obsticleWidth;
    int obsticleHeight;
    int objInt = randomInt.generate(0, 2);
    Obstacle obsticle;
    if (objInt == 1) {
      obsticleWidth = 60;
      obsticleHeight = 45;
      obsticle = new Bird(obstacles, player, this, gameHasStarted, this.getWidth(), this.getHeight() - groundLevel - 100 - obsticleHeight, obsticleWidth, obsticleHeight);
    } else {
      obsticleWidth = 25;
      obsticleHeight = 60;
      obsticle = new Cactus(obstacles, player, this, gameHasStarted, this.getWidth(), this.getHeight() - groundLevel - obsticleHeight, obsticleWidth, obsticleHeight);
    }
    obsticle.startMoving();
    this.add(obsticle.getObstacleLabel());
    this.repaint();
    return obsticle;
  }

  private void addScoreBoard() {
    scoreBoard = new ScoreBoard(this.getWidth(), this.getWidth() - 200, 10, 200, 32);
    this.add(scoreBoard.getScoreLabel());
    this.repaint();
  };

  public void gameOver() {
    gameHasStarted = false;
    obstacleInterval.stop();
    for (Obstacle obstacle : obstacles) {
      this.remove(obstacle.getObstacleLabel());
    }
    obstacles.clear();
    this.repaint();
    cardManager.showPanel("game over");
  };

  private void startGame() {
    gameHasStarted = true;
    this.removeAll();
    addScoreBoard();
    addPlayer();
    startObsticleScene();

    frame.addKeyListener(
      new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          if (gameHasStarted && e.getKeyCode() == KeyEvent.VK_UP) {
            player.startJump();
          }
        }
      }
    );

    frame.requestFocus();
  };
}