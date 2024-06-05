package src.entity;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.utils.Interval;

public abstract class Entity extends JLabel {
  protected String[] imagePaths;
  protected int imageIndex = 0;
  protected int xPosition;
  protected int yPosition;
  protected int width;
  protected int height;

  protected Entity(String[] imagePaths, int initialX, int initialY, int width, int height) {
    this.imagePaths = imagePaths;
    this.xPosition = initialX;
    this.yPosition = initialY;
    this.width = width;
    this.height = height;
    loadImage(imagePaths[imageIndex]);
    setEntityBounds(xPosition, yPosition, width, height);
    animate();
  }

  protected void loadImage(String imagePath) {
    try {
      Image image = Toolkit.getDefaultToolkit().getImage(imagePath);
      Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      if (scaledImage != null) {
        ImageIcon icon = new ImageIcon(scaledImage);
        setIcon(icon);
        System.out.println(imagePath);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      repaint();
    }
  }

  private void setEntityBounds(int initialX, int initialY, int width, int height) {
    setBounds(initialX, initialY, width, height);
  }

  public int getXPosition() {
    return xPosition;
  }

  public void setXPosition(int xPosition) {
    this.xPosition = xPosition;
    setLocation(xPosition, yPosition);
    repaint();
  }

  public int getYPosition() {
    return yPosition;
  }

  public void setYPosition(int yPosition) {
    this.yPosition = yPosition;
    setLocation(xPosition, yPosition);
    repaint();
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
    setSize(width, height);
    repaint();
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
    setSize(width, height);
    repaint();
  }

  protected void animate() {
    int intervalDelay = 2000;
    Interval interval = new Interval(intervalDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        imageIndex++;
        String imagePath = imagePaths[imageIndex % imagePaths.length];
        loadImage(imagePath);
      }
    });
    interval.start();
  }
}
