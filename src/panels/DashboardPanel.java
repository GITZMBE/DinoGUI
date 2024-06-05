package src.panels;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.components.ui.Button;
import src.managers.CardManager;
import src.managers.ScoreManager;

public class DashboardPanel extends Panel {
  public DashboardPanel(CardManager cardManager, ScoreManager scoreManager) {
    super(cardManager, scoreManager);
    this.setLayout(new GridBagLayout());

    Button startGameButton = new Button("Start Game");
    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        startGame();
        return;
      }
    });

    this.add(startGameButton);
  }

  public void startGame() {
    cardManager.showPanel(CardManager.GAME_PANEL);
  };
};
