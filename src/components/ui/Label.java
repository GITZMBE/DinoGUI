package src.components.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import src.utils.FontLoader;
import src.utils.Interval;

public class Label extends JLabel {
  private FontLoader fontLoader = new FontLoader();
  private Font font;
  private Interval strobeTimer;
  private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
  private int colorIndex = 0;
  private int transitionDuration = 1000;
  private long startTime;

  public Label(String text, float fontsize, boolean strobe) {
    loadFont(fontsize);
    styleLabel(this, text);
    if (strobe) {
      startStrobeAnimation();
    }
  }

  private void loadFont(float fontsize) {
    fontLoader.loadFont("arc.ttf", fontsize);
    font = fontLoader.getFont();
  }

  private void styleLabel(JLabel label, String text) {
    label.setText(text);
    label.setBackground(Color.decode("#484A47"));
    label.setForeground(Color.WHITE);
    label.setFont(font);
    label.setOpaque(true);
    label.setBorder(null);
  }

  private void startStrobeAnimation() {
    startTime = System.currentTimeMillis();
    strobeTimer = new Interval(100, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= transitionDuration) {
          colorIndex = (colorIndex + 1) % colors.length;
          startTime = System.currentTimeMillis();
        } else {
          float fraction = (float) elapsedTime / transitionDuration;
          int nextColorIndex = (colorIndex + 1) % colors.length;
          int red = (int) (colors[colorIndex].getRed() + fraction * (colors[nextColorIndex].getRed() - colors[colorIndex].getRed()));
          int green = (int) (colors[colorIndex].getGreen() + fraction * (colors[nextColorIndex].getGreen() - colors[colorIndex].getGreen()));
          int blue = (int) (colors[colorIndex].getBlue() + fraction * (colors[nextColorIndex].getBlue() - colors[colorIndex].getBlue()));
          setForeground(new Color(red, green, blue));
        }
      }
    });
    strobeTimer.start();
}
}
