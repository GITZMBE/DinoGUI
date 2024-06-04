package src.entity;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Entity {
  protected String imagePath;
  protected ImageIcon icon;
  protected JLabel label;
  protected int xPosition;
  protected int yPosition;
  protected int width;
  protected int height;

  protected Entity(String imagePath) {
    this.imagePath = imagePath;
    loadImage(imagePath, width, height);
    initiateLabel(yPosition, xPosition, width, height);
  }

  private void loadImage(String imagePath, int width, int height) {
    Image obstacleImage = Toolkit.getDefaultToolkit().getImage(imagePath);
    Image scaledImage = obstacleImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    icon = new ImageIcon(scaledImage);
  }

  private void initiateLabel(int initialX, int initialY, int width, int height) {
    label = new JLabel(icon);
    label.setBounds(initialX, initialY, width, height);
    xPosition = initialX;
    this.width = width;
  }

  public JLabel getEntityLabel() {
    return label;
  }

  public int getXPosition() {
    return xPosition;
  }

  public int getYPosition() {
    return yPosition;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
