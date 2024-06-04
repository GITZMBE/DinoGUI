package src;

import src.components.Frame;
import src.managers.CardManager;

public class GUI {
  private CardManager cardManager = new CardManager();
  private Frame frame = new Frame();
  
  public GUI() {
    cardManager.initializePanels(frame);
  };
  
  public static void main(String[] args) {
    new GUI();
  }
};