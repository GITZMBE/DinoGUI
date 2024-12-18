package src.components.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.MediaTracker;

public class Icon extends JLabel {
  protected int width;
  protected int height;

  public Icon(String imagePath, int width, int height) {
    this.width = width;
    this.height = height;
    loadImage(imagePath, width, height);
  };

  protected void loadImage(String imagePath, int width, int height) {
    try {
      Image image = Toolkit.getDefaultToolkit().getImage(imagePath);
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(image, 0);
      tracker.waitForAll();
      Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      if (scaledImage != null) {
        ImageIcon icon = new ImageIcon(scaledImage);
        setIcon(icon);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      revalidate();
      repaint();
    }
  }
}
