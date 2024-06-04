package src.panels;

import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.components.Panel;
import src.managers.CardManager;

public class DashboardPanel extends Panel {
  CardManager cardManager = new CardManager();
  private static final String GAME_PANEL = "game";

  public DashboardPanel() {
    super();
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
    cardManager.showPanel(GAME_PANEL);
  };
}




