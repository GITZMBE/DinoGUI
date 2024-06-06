package src.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import src.components.ui.Button;
import src.components.ui.Label;
import src.data.DataSaver;
import src.managers.CardManager;
import src.managers.ScoreManager;

public class GameOverPanel extends Panel {
  public GameOverPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(new GridBagLayout(), cardManager, scoreManager);
  }

  protected void initializePanel() {
    removeAll();
    ArrayList<String> scoreArray = new ArrayList<>();
    scoreArray.add(scoreManager.getFormatScore());
    DataSaver.saveData(scoreArray, "src/data/data.txt");

    Label gameOverLabel = new Label("Game Over!", 64);
    Label endScoreLabel = new Label("Your Score: " + scoreManager.getFormatScore(), 24);
    Button dashboardButton = new Button("Dashboard", 24);
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(20, 10, 20, 10);

    add(gameOverLabel, gbc);
    gbc.gridy++;
    add(endScoreLabel, gbc);
    gbc.gridy++;
    add(dashboardButton, gbc);

    revalidate();
    repaint();

    dashboardButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cardManager.showPanel(CardManager.DASHBOARD_PANEL);
        scoreManager.clearScore();
      }
    });
  }
}
