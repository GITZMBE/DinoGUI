package src.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.utils.Interval;

public class IconAnimation extends Icon {
  protected String[] imagePaths;
  protected int imageIndex = 0;

  public IconAnimation(String[] imagePaths, int width, int height) {
    super(imagePaths[0], width, height);
    animate();
  };

  protected void animate() {
    int intervalDelay = 2000;
    Interval interval = new Interval(intervalDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        imageIndex++;
        String imagePath = imagePaths[imageIndex % imagePaths.length];
        loadImage(imagePath, width, height);
      }
    });
    interval.start();
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
}
