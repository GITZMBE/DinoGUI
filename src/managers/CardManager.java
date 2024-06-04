package src.managers;

import javax.swing.*;

import src.panels.DashboardPanel;
import src.panels.GameOverPanel;
import src.panels.GamePanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CardManager {
  private CardManager cardManager = new CardManager();
  private CardLayout cardLayout;
  private Map<String, JPanel> panels;

  public CardManager() {
    cardLayout = new CardLayout();
    panels = new HashMap<>();
  }

  public CardLayout getLayout() {
    return this.cardLayout;
  };

  public void showPanel(String name) {
    cardLayout.show(panels.get(name).getParent(), name);
  }

  public void addPanel(String name, JPanel panel) {
    panels.put(name, panel);
  }

  public void initializePanels(JFrame frame) {
    DashboardPanel dashboard = new DashboardPanel();
    GamePanel game = new GamePanel(frame);
    GameOverPanel gameOver = new GameOverPanel();

    cardManager.addPanel("dashboard", dashboard);
    cardManager.addPanel("game", game);
    cardManager.addPanel("gameOver", gameOver);
  };
}
