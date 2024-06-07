package src.components.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.utils.FontLoader;

public class StageLabel extends JLabel {
  private FontLoader fontLoader = new FontLoader();
  @SuppressWarnings("unused")
  private Font font;
  private int opacity;

  public StageLabel(String text, int fontsize, int opacity, JPanel panel) {
    this.opacity = opacity;
    loadFont(fontsize);
    styleLabel(text, panel);
  }

  private void loadFont(int fontsize) {
    fontLoader.loadFont("arc.ttf", fontsize);
    font = fontLoader.getFont();
  }

  private void styleLabel(String text, JPanel panel) {
    setText("Stage 1");
    setFont(font);
    setHorizontalAlignment(JLabel.CENTER);
    setVerticalAlignment(JLabel.CENTER);
    setForeground(new Color(255, 255, 255, opacity));
    setBounds(panel.getWidth() / 2 - 100, panel.getHeight() / 2 - 25, 200, 50);
    repaint();
  }
}
