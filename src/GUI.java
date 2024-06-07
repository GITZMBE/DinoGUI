package src;

import src.components.Frame;
import src.managers.CardManager;
import src.managers.ScoreManager;

public class GUI {
  public static int GROUND_LEVEL = 100;
  private CardManager cardManager;
  private ScoreManager scoreManager;
  private Frame frame;
  
  public GUI() {
    cardManager = new CardManager();
    scoreManager = new ScoreManager();
    frame = new Frame(cardManager);
    cardManager.initializePanels(frame, cardManager, scoreManager);
    frame.getContentPane().add(cardManager.getCardPanel());
    cardManager.showPanel(CardManager.DASHBOARD_PANEL);
  };
  
  public static void main(String[] args) {
    new GUI();
  }
};