package src.panels;

import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import src.managers.CardManager;
import src.managers.ScoreManager;

public class ScoreboardPanel extends Panel {
  

  public ScoreboardPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(cardManager, scoreManager);
    setLayout(new GridBagLayout());
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent e) {
        initializePanel();
      }
    });
  };

  protected void initializePanel() {

    revalidate();
  };
}
