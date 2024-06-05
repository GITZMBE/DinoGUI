package src.panels;

import java.awt.GridBagLayout;

import src.managers.CardManager;
import src.managers.ScoreManager;

public class ScoreboardPanel extends Panel {
  

  public ScoreboardPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(new GridBagLayout(), cardManager, scoreManager);
  };

  protected void initializePanel() {

    revalidate();
  };
}
