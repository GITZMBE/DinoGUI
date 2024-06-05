package src.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import src.components.ui.Label;
import src.managers.CardManager;
import src.managers.ScoreManager;

public class ScoreboardPanel extends Panel {
  

  public ScoreboardPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(new GridBagLayout(), cardManager, scoreManager);
  };

  protected void initializePanel() {
    Label scoreboardLabel = new Label("Scoreboard", 64);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(20, 10, 20, 10);

    add(scoreboardLabel, gbc);
    gbc.insets = new Insets(5, 10, 5, 10);
    ArrayList<String> scoreHistory = scoreManager.getScoreHistory();
    Collections.sort(scoreHistory, new Comparator<String>() {
      @Override
      public int compare(String score1, String score2) {
        int intScore1 = Integer.parseInt(score1);
        int intScore2 = Integer.parseInt(score2);
        return Integer.compare(intScore2, intScore1);
      }
    });
    int maxIterations = Math.min(5, scoreHistory.size());
    for (int i = 0; i < maxIterations; i++) {
      String score = scoreHistory.get(i);
      gbc.gridy++;
      Label label = new Label((i + 1) + ". " + score, 16);
      add(label, gbc);
    }

    revalidate();
    repaint();
  };
}
