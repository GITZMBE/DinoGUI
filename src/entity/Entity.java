package src.entity;

import javax.swing.JLabel;

public abstract class Entity {
  protected JLabel entityLabel;
  protected int xPosition;
  protected int yPosition;
  protected int width;
  protected int height;

  public JLabel getEntityLabel() {
    return entityLabel;
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
