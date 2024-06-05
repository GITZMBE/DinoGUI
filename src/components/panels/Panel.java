package src.components.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import src.managers.CardManager;
import src.managers.ScoreManager;

public class Panel extends JPanel {
  protected CardManager cardManager;
  protected ScoreManager scoreManager;

  public Panel(CardManager cardManager, ScoreManager scoreManager) {
    this.cardManager = cardManager;
    this.scoreManager = scoreManager;
    this.removeAll();
    this.revalidate();
    this.repaint();
    this.setPreferredSize(new Dimension(750, 500));
    this.setBackground(Color.decode("#484A47"));
    this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.setDoubleBuffered(true);
  };
}
