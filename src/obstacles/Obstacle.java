package src.obstacles;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GUI;
import src.entity.Entity;
import src.utils.Interval;
import src.utils.RandomInt;

public class Obstacle extends Entity {
  private GUI gui;
  private RandomInt randomInt = new RandomInt();
  private ImageIcon obstacleIcon;
  private JLabel obstacleLabel;
  private int xPosition;
  private int width;
  private static final int SPEED = 1000;
  private JPanel panel;
  private Interval interval;
  public boolean isOffPage = false;
  private boolean gameHasStarted;
  private String[] imagePaths;
  private int imageIndex = 0;
  private String imagePath;

  public Obstacle(GUI gui, JPanel panel, String[] imagePaths, boolean gameHasStarted, int initialX, int initialY, int width, int height) {
    this.gui = gui;
    this.panel = panel;
    this.gameHasStarted = gameHasStarted;
    this.imagePaths = imagePaths;
    this.imagePath = imagePaths[imageIndex % imagePaths.length];

    loadImage(imagePath, width, height);
    setPosition(initialX, initialY, width, height);
  };

  private void loadImage(String imagePath, int width, int height) {
    Image obstacleImage = Toolkit.getDefaultToolkit().getImage(imagePath);
    Image scaledImage = obstacleImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    obstacleIcon = new ImageIcon(scaledImage);
  }

  private void setPosition(int initialX, int initialY, int width, int height) {
    obstacleLabel = new JLabel(obstacleIcon);
    obstacleLabel.setBounds(initialX, initialY, width, height);
    xPosition = initialX;
    this.width = width;
  }

  public JLabel getObstacleLabel() {
    return obstacleLabel;
  }

  private void checkCollisions() {
    if (gui != null && gui.hasCollided(gui.player, this)) {
        gui.gameOver();
    }
  }

  public void startMoving() {
    int ticSpeed = randomInt.generate(3, 8);
    interval = new Interval(1000 / SPEED, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        xPosition -= ticSpeed;
        obstacleLabel.setBounds(xPosition, obstacleLabel.getY(), width, obstacleLabel.getHeight());

        if (xPosition + width < 0 || !gameHasStarted) {
          panel.remove(obstacleLabel);
          isOffPage = true;
          interval.stop();
        }
        checkCollisions();
      }
    });
    interval.start();
    animate();
  }

  public void animate() {
    int intervalDelay = 2000;
    Interval interval = new Interval(intervalDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        imageIndex++;
        imagePath = imagePaths[imageIndex % imagePaths.length];

        Image newImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        Image scaledImage = newImage.getScaledInstance(obstacleLabel.getWidth(), obstacleLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(scaledImage);

        obstacleLabel.setIcon(newIcon);

        panel.repaint();
      }
    });
    interval.start();
  }
}
