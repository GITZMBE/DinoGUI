package src.panels;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.components.Button;
import src.components.Panel;
import src.managers.CardManager;

public class GameOverPanel extends Panel {
  private CardManager cardManager = new CardManager();
  private static final String DASHBOARD_PANEL = "dashboard";

  public GameOverPanel() {
    super();

    this.setLayout(new GridBagLayout());

    Button startGameButton = new Button("Go to dashboard");
    startGameButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cardManager.showPanel(DASHBOARD_PANEL);
      }
    });

    this.add(startGameButton);
  }


}