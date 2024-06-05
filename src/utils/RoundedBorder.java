package src.utils;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {
  private int radius;

  public RoundedBorder(int radius) {
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
