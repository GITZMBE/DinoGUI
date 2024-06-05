package src.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.components.ui.Button;
import src.managers.CardManager;
import src.managers.ScoreManager;

public class DashboardPanel extends Panel {
  public DashboardPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(new GridBagLayout(), cardManager, scoreManager);
    initializePanel();
  }

  protected void initializePanel() {
    removeAll();
    Button startGameButton = new Button("Start Game", 24);
    Button scoreboardButton = new Button("Scoreboard", 16);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10);

    add(startGameButton, gbc);
    gbc.gridy++;
    add(scoreboardButton, gbc);

    revalidate();
    repaint();

    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startGame();
        return;
      }
    });
    scoreboardButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cardManager.showPanel(CardManager.SCOREBOARD_PANEL);
        return;
      }
    });
  }

  private void startGame() {
    cardManager.showPanel(CardManager.GAME_PANEL);
  };
};
