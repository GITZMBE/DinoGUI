package src;

import src.components.Frame;
import src.managers.CardManager;

public class GUI {
  private CardManager cardManager;
  private Frame frame;
  
  public GUI() {
    cardManager = new CardManager();
    frame = new Frame(cardManager);
    cardManager.initializePanels(frame, cardManager);
    frame.getContentPane().add(cardManager.getCardPanel());
    cardManager.showPanel(CardManager.DASHBOARD_PANEL);
  };
  
  public static void main(String[] args) {
    new GUI();
  }
};