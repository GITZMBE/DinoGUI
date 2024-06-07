package src.managers;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.utils.Interval;

public class StageManager {
  private JPanel panel;
  private JLabel stageLabel;
  private static final int fadeStep = 5;
  private int alpha = 0;
  private Interval fadeInInterval;
  private Interval fadeOutInterval;

  public StageManager(JPanel panel, JLabel stageLabel) {
    this.panel = panel;
    this.stageLabel = stageLabel;
  }

  public void startAnimation(int score) {
    if (score % 100 == 0 && score != 0) {
      int stage = score / 100;
      stageLabel.setText("Stage " + stage);
      alpha = 0;
      fadeIn();
    }
  }

  private void fadeIn() {
    fadeInInterval = new Interval(10, e -> {
      if (alpha + fadeStep > 255) {
        alpha = 255;
        fadeInInterval.stop();
        fadeOut();
        return;
      }
      alpha += fadeStep;
      stageLabel.setForeground(new Color(255, 255, 255, alpha));
      stageLabel.repaint();
      panel.repaint();
    });
    fadeInInterval.start();
  }

  private void fadeOut() {
    fadeOutInterval = new Interval(10, e -> {
      if (alpha - fadeStep < 0) {
        alpha = 0;
        fadeOutInterval.stop();
        return;
      }
      alpha -= fadeStep;
      stageLabel.setForeground(new Color(255, 255, 255, alpha));
      stageLabel.repaint();
      panel.repaint();
    });
    fadeOutInterval.start();
  }
}
