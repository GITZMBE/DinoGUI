package src.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import src.managers.CardManager;
import src.managers.ScoreManager;

public abstract class Panel extends JPanel {
  protected CardManager cardManager;
  protected ScoreManager scoreManager;

  public Panel(LayoutManager layout, CardManager cardManager, ScoreManager scoreManager) {
    this.cardManager = cardManager;
    this.scoreManager = scoreManager;
    
    stylePanel();
    setLayout(layout);
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent e) {
        initializePanel();
      }
      @Override
      public void componentResized(ComponentEvent e) {
        resize();
      }
    });
  };

  private void stylePanel() {
    removeAll();
    setPreferredSize(new Dimension(750, 500));
    setBackground(Color.decode("#484A47"));
    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    setDoubleBuffered(true);
    setLayout(null);
    revalidate();
    repaint();
  };

  protected abstract void initializePanel();

  protected void resize() {};
}
