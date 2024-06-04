package src.components;

import java.awt.Toolkit;

import javax.swing.JFrame;

import src.managers.CardManager;

public class Frame extends JFrame {
  private CardManager cardManager = new CardManager();

  public Frame() {
    styleFrame();
  }

  private void styleFrame() {
    this.setLayout(cardManager.getLayout());
    this.setLocationByPlatform(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Dino - GUI");
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(".//res//images//dino.png"));
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  };
}
