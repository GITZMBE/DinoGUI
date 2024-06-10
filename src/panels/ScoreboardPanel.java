package src.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import src.components.ui.Button;
import src.components.ui.Label;
import src.managers.CardManager;
import src.managers.DataManager;
import src.managers.ScoreManager;

public class ScoreboardPanel extends Panel {
  public ScoreboardPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(new GridBagLayout(), cardManager, scoreManager);
  };

  protected void initializePanel() {
    removeAll();
    Label scoreboardLabel = new Label("Scoreboard", 48);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(20, 10, 20, 10);

    add(scoreboardLabel, gbc);
    gbc.insets = new Insets(5, 10, 5, 10);

    ArrayList<String> scoreHistory = DataManager.loadData("src/data/data.txt");
    
    int maxIterations = Math.min(5, scoreHistory.size());
    ArrayList<String> filteredHistory = new ArrayList<>();
    for (int i = 0; i < maxIterations; i++) {
      String score = scoreHistory.get(i);
      filteredHistory.add(score);
      gbc.gridy++;
      Label label = new Label((i + 1) + ". " + score, 16);
      add(label, gbc);
    }
    DataManager.resaveTopScores(filteredHistory, "src/data/data.txt");

    gbc.gridy++;
    Button dashboardButton = new Button("Dashboard", 16, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cardManager.showPanel(CardManager.DASHBOARD_PANEL);
      }
    });
    
    add(dashboardButton, gbc);

    revalidate();
    repaint();
  };
}
