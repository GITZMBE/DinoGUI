package src.components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

import src.utils.FontLoader;

public class Label extends JLabel {
  private FontLoader fontLoader = new FontLoader();
  private Font font;

  public Label(String text, float fontSize) {
    loadFont(fontSize);
    styleLabel(this, text);
  }

  private void loadFont(float fontSize) {
    fontLoader.loadFont("arc.ttf", fontSize);
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
}
