package src.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.components.ui.Icon;
import src.utils.Interval;

public class IconAnimation extends Icon {
  private String[] imagePaths;
  private int imageIndex = 0;

  public IconAnimation(String[] imagePaths, int animationInterval, int width, int height) {
    super(imagePaths[0], width, height);
    this.imagePaths = imagePaths;
    animate(animationInterval);
  };

  private void animate(int animationInterval) {
    Interval interval = new Interval(animationInterval, new ActionListener() {
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
