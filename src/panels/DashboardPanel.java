package src.panels;

import java.awt.GridBagLayout;
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
    Button startGameButton = new Button("Start Game");
    add(startGameButton);

    revalidate();
    repaint();

    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startGame();
        return;
      }
    });
  }

  private void startGame() {
    cardManager.showPanel(CardManager.GAME_PANEL);
  };
};
