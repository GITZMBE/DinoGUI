package src.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import src.components.ui.Button;
import src.components.ui.Label;
import src.managers.CardManager;
import src.managers.DataManager;
import src.managers.ScoreManager;

public class GameOverPanel extends Panel {
  public GameOverPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(new GridBagLayout(), cardManager, scoreManager);
  }

  protected void initializePanel() {
    removeAll();
    ArrayList<String> scoreArray = new ArrayList<>();
    scoreArray.add(scoreManager.getFormatScore());
    DataManager.saveData(scoreArray, "src/data/data.txt");

    Label gameOverLabel = new Label("Game Over!", 64);
    Label endScoreLabel = new Label("Your Score: " + scoreManager.getFormatScore(), 24);
    Button playButton = new Button("Play Again", 24, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startGame();
        return;
      }
    });
    Button dashboardButton = new Button("Dashboard", 24, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cardManager.showPanel(CardManager.DASHBOARD_PANEL);
        scoreManager.clearScore();
      }
    });
    
    ArrayList<JComponent> btnComps = new ArrayList<>();
    btnComps.add(dashboardButton);
    btnComps.add(playButton);
    FlexPanel buttonPanel = new FlexPanel(btnComps, 20);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(20, 10, 20, 10);
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridwidth = GridBagConstraints.REMAINDER;

    add(gameOverLabel, gbc);
    gbc.gridy++;
    add(endScoreLabel, gbc);
    gbc.gridy++;
    add(buttonPanel, gbc);

    revalidate();
    repaint();
  }

  private void startGame() {
    cardManager.showPanel(CardManager.GAME_PANEL);
    scoreManager.clearScore();
  };
}
