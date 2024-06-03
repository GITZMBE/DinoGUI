package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import src.obstacle.Obstacle;
import src.player.Player;
import src.utils.Interval;
import src.utils.RandomInt;

public class GUI {
  private RandomInt randomInt = new RandomInt();
  private JFrame frame = new JFrame();
  private JPanel panel = new JPanel();
  public  Player player;
  private int groundLevel = 100;
  private boolean gameHasStarted = false;
  private List<Obstacle> obstacles = new ArrayList<>();
  private Interval obstacleInterval;
  private Font customFont;

  public GUI() {
    loadCustomFont("arcade.ttf");
    createPanel();
    createFrame();
  };

  public void loadCustomFont(String font) {
    try {
      customFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/" + font)).deriveFont(32f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(customFont);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }

  public void createPanel() {
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    if (gameHasStarted) {
      panel.setLayout(null);
    } else {
      panel.setLayout(new GridBagLayout());

      JButton startGameButton = new JButton("Start Game");
      styleButton(startGameButton);
      startGameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          startGame();
          return;
        }
      });

      panel.add(startGameButton);
    }

    panel.setPreferredSize(new Dimension(750, 500));
    panel.setBackground(Color.decode("#484A47"));
    panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    panel.setDoubleBuffered(true);
  };

  private void styleButton(JButton button) {
    button.setBackground(Color.decode("#484A47"));
    button.setForeground(Color.WHITE);
    button.setFont(customFont);
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.decode("#30332F"), 2),
      BorderFactory.createEmptyBorder(10, 25, 10, 25)
    ));
    button.setContentAreaFilled(false);
    button.setOpaque(true);
    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    button.setBorder(new RoundedBorder(10));
  }

  class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
      this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
      return new Insets(radius + 1, radius + 1, radius + 2, radius);
    }

    public boolean isBorderOpaque() {
      return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      g.setColor(c.getForeground());
      g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
  }

  public void createFrame() {
    frame.setLocationByPlatform(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Dino - GUI");
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(".//res//images//dino.png"));
    frame.add(panel, BorderLayout.CENTER);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  };

  public void addPlayer() {
    int playerWidth = 50;
    int playerHeight = 50;
    int distanceFromWall = 100;
    player = new Player(".//res//images//dino.png", distanceFromWall, frame.getHeight() - groundLevel - playerHeight, playerWidth, playerHeight);
    panel.add(player.getPlayerLabel());
    panel.repaint();
  };

  public void startObsticleScene() {
    int obstacleIntervalDelay = 5000;
    obstacleInterval = new Interval(obstacleIntervalDelay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Obstacle obsticle = initiateObsticle();
        obstacles.add(obsticle);
        checkCollisions();
      }
    });
    obstacleInterval.start();
  };

  public Obstacle initiateObsticle() {
    int obsticleWidth = 50;
    int obsticleHeight = 50;
    int objInt = randomInt.generate(0, 2);
    Obstacle obsticle;
    if (objInt == 1) {
      obsticle = new Obstacle(this, panel, gameHasStarted, ".//res//images//bird.png", frame.getWidth(), frame.getHeight() - groundLevel - 100 - obsticleHeight, obsticleWidth, obsticleHeight);
    } else {
      obsticle = new Obstacle(this, panel, gameHasStarted, ".//res//images//cactus.png", frame.getWidth(), frame.getHeight() - groundLevel - obsticleHeight, obsticleWidth, obsticleHeight);
    }
    obsticle.startMoving();
    panel.add(obsticle.getObstacleLabel());
    panel.repaint();
    return obsticle;
  }

  public boolean hasCollided(Player player, Obstacle obstacle) {
    JLabel playerLabel = player.getPlayerLabel();
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

  public void checkCollisions() {
    for (Obstacle obsticle : obstacles) {
      if (hasCollided(player, obsticle)) {
        gameOver();
        break;
      }
    }
  };

  public void gameOver() {
    gameHasStarted = false;
    obstacleInterval.stop();
    for (Obstacle obstacle : obstacles) {
      panel.remove(obstacle.getObstacleLabel());
    }
    obstacles.clear();
    panel.repaint();
    createPanel();
  };

  public void startGame() {
    gameHasStarted = true;
    createPanel();
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

  public static void main(String[] args) {
    new GUI();
  }
};