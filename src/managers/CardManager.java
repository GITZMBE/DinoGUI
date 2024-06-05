package src.managers;

import javax.swing.*;

import src.panels.DashboardPanel;
import src.panels.GameOverPanel;
import src.panels.GamePanel;
import src.components.Frame;

import java.awt.*;
import java.util.HashMap;

public class CardManager {
  private CardLayout cardLayout;
  private JPanel cardPanel;
  public HashMap<String, JPanel> panels;
  public static final String DASHBOARD_PANEL = "dashboard";
  public static final String GAME_PANEL = "game";
  public static final String GAME_OVER_PANEL = "game over";

  public CardManager() {
    cardLayout = new CardLayout();
    cardPanel = new JPanel(cardLayout);
    panels = new HashMap<String, JPanel>();
  }

  public CardLayout getLayout() {
    return this.cardLayout;
  };

  public JPanel getCardPanel() {
    return this.cardPanel;
  }

  public JPanel getCurrentPanel() {
    for (Component comp : cardPanel.getComponents()) {
      if (comp.isVisible()) {
        return (JPanel) comp;
      }
    }
    return null;
  }

  public void showPanel(String name) {
    JPanel panel = panels.get(name);
    if (panel != null && panel.getParent() != null) {
      cardLayout.show(panel.getParent(), name);
    } else {
      System.err.println("Panel or parent is null: " + name);
    }
  }

  private void addPanel(String name, JPanel panel) {
    panels.put(name, panel);
    cardPanel.add(panel, name);
  }

  public void initializePanels(Frame frame, CardManager cardManager, ScoreManager scoreManager) { // Create stats page for score history
    DashboardPanel dashboard = new DashboardPanel(cardManager, scoreManager); // add button to redirect to score page
    GamePanel game = new GamePanel(cardManager, scoreManager);
    GameOverPanel gameOver = new GameOverPanel(cardManager, scoreManager); // maybe show scorehistory

    this.addPanel(DASHBOARD_PANEL, dashboard);
    this.addPanel(GAME_PANEL, game);
    this.addPanel(GAME_OVER_PANEL, gameOver);
  };
};
