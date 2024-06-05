package src.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import src.utils.FontLoader;

public class Button extends JButton {
  private FontLoader fontLoader = new FontLoader();
  private Font font;

  public Button(String text) {
    loadFont();
    styleButton(this, text);
  }

  private void loadFont() {
    fontLoader.loadFont("arcade.ttf", 24);
    font = fontLoader.getFont();
  }

  private void styleButton(JButton button, String text) {
    button.setText(text);
    button.setBackground(Color.decode("#484A47"));
    button.setForeground(Color.WHITE);
    button.setFont(font);
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.decode("#30332F"), 2),
      BorderFactory.createEmptyBorder(10, 25, 10, 25)
    ));
    button.setContentAreaFilled(false);
    button.setOpaque(true);
    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    button.setBorder(new RoundedBorder(10));
  }
}
