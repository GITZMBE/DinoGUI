package src.obstacles;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.entity.Entity;
import src.player.Player;
import src.utils.CollitionChecker;
import src.utils.Interval;
import src.utils.RandomInt;

public class Obstacle extends Entity {
  private Player player;
  private CollitionChecker collitionChecker = new CollitionChecker();
  private RandomInt randomInt = new RandomInt();
  private List<Obstacle> obstacles;
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

  public Obstacle(List<Obstacle> obstacles, Player player, JPanel panel, String[] imagePaths, boolean gameHasStarted, int initialX, int initialY, int width, int height) {
    super(imagePaths[0]);
    this.obstacles = obstacles;
    this.player = player;
    this.panel = panel;
    this.gameHasStarted = gameHasStarted;
    this.imagePaths = imagePaths;
    this.imagePath = imagePaths[imageIndex % imagePaths.length];
  };

  public JLabel getObstacleLabel() {
    return label;
  }

  public void startMoving() {
    int ticSpeed = randomInt.generate(3, 8);
    interval = new Interval(1000 / SPEED, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        xPosition -= ticSpeed;
        label.setBounds(xPosition, label.getY(), width, label.getHeight());

        if (xPosition + width < 0 || !gameHasStarted) {
          panel.remove(label);
          isOffPage = true;
          interval.stop();
        }
        collitionChecker.checkCollisions(obstacles, player);
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
        Image scaledImage = newImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(scaledImage);

        label.setIcon(newIcon);

        panel.repaint();
      }
    });
    interval.start();
  }
}
