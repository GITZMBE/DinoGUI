package src.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Panel extends JPanel {
  public Panel() {
    this.removeAll();
    this.revalidate();
    this.repaint();
    this.setPreferredSize(new Dimension(750, 500));
    this.setBackground(Color.decode("#484A47"));
    this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.setDoubleBuffered(true);
  };
}
