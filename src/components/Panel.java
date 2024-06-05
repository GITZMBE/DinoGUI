package src.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import src.managers.CardManager;

public class Panel extends JPanel {
  protected CardManager cardManager;

  public Panel(CardManager cardManager) {
    this.cardManager = cardManager;
    this.removeAll();
    this.revalidate();
    this.repaint();
    this.setPreferredSize(new Dimension(750, 500));
    this.setBackground(Color.decode("#484A47"));
    this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.setDoubleBuffered(true);
  };
}