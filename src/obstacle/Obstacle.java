package src.obstacle;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.GUI;
import src.utils.Interval;
import src.utils.RandomInt;

public class Obstacle {
  private GUI gui;
  private RandomInt randomInt = new RandomInt();
  private ImageIcon obsticleIcon;
  private JLabel obsticleLabel;
  private int xPosition;
  private int width;
  private static final int SPEED = 1000;
  private JPanel panel;
  private Interval interval;
  public boolean isOffPage = false;
  private boolean gameHasStarted;

  public Obstacle(GUI gui, JPanel panel, boolean gameHasStarted, String imagePath, int initialX, int initialY, int width, int height) {
    this.gui = gui;
    this.panel = panel;
    this.gameHasStarted = gameHasStarted;
    Image obsticleImage = Toolkit.getDefaultToolkit().getImage(imagePath);
    Image scaledImage = obsticleImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    obsticleIcon = new ImageIcon(scaledImage);

    obsticleLabel = new JLabel(obsticleIcon);
    obsticleLabel.setBounds(initialX, initialY, width, height);
    xPosition = initialX;
    this.width = width;
  };

  public JLabel getObstacleLabel() {
    return obsticleLabel;
  }

  private void checkCollisions() {
    if (gui != null && gui.hasCollided(gui.player, this)) {
        gui.gameOver();
    }
  }

  public void startMoving() {
    int ticSpeed = randomInt.generate(2, 5);
    interval = new Interval(1000 / SPEED, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        xPosition -= ticSpeed;
        obsticleLabel.setBounds(xPosition, obsticleLabel.getY(), width, obsticleLabel.getHeight());
        if (xPosition + width < 0 || !gameHasStarted) {
          panel.remove(obsticleLabel);
          isOffPage = true;
          interval.stop();
        }
        checkCollisions();
      }
    });
    interval.start();
  }
}
