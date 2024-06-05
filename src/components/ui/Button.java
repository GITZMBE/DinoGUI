package src.components.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import src.utils.FontLoader;
import src.utils.RoundedBorder;

public class Button extends JButton {
  private FontLoader fontLoader = new FontLoader();
  private Font font;

  public Button(String text) {
    loadFont();
    styleButton(text);
  }

  private void loadFont() {
    fontLoader.loadFont("arc.ttf", 24);
    font = fontLoader.getFont();
  }

  private void styleButton(String text) {
    setText(text);
    setBackground(Color.decode("#484A47"));
    setForeground(Color.WHITE);
    setFont(font);
    setFocusPainted(false);
    setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.decode("#30332F"), 2),
      BorderFactory.createEmptyBorder(10, 25, 10, 25)
    ));
    setContentAreaFilled(false);
    setOpaque(true);
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    setBorder(new RoundedBorder(10));

    revalidate();
    repaint();
  }
}
