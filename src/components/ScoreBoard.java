package src.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import src.components.ui.Label;
import src.managers.ScoreManager;
import src.utils.FontLoader;
import src.utils.Interval;

public class Scoreboard extends Label {
  private ScoreManager scoreManager;
  private int frameWidth;
  public static int score = 0;
  private static final int SCORE_SPEED = 10;
  private Interval interval;
  private FontLoader fontLoader = new FontLoader();
  private Font font;

  public Scoreboard(ScoreManager scoreManager, int frameWidth, int x, int y, int width, int height) {
    super(scoreManager.getFormatScore(), 24, false);
    this.scoreManager = scoreManager;
    this.frameWidth = frameWidth;
    this.setBounds(x, y, width, height);
    styleScoreLabel(this);
    start();
  };

  public int getScore() {
    return Scoreboard.score;
  }

  public void start() {
    interval = new Interval(1000 / SCORE_SPEED, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        scoreManager.increaseScore(1);
        updateScoreLabel();
      }
    });
    interval.start();
  };

  public void stop() {
    if (interval != null) {
      interval.stop();
    }
  };

  private void updateScoreLabel() {
    String newScore = scoreManager.getFormatScore();
    this.setText(newScore);
  };

  private void styleScoreLabel(JLabel label) {
    label.setOpaque(true);
    label.setBackground(Color.decode("#484A47"));
    label.setForeground(Color.WHITE);
    fontLoader.loadFont("arcade.ttf", 32);
    font = fontLoader.getFont();
    label.setFont(font);
    label.setHorizontalAlignment(JLabel.RIGHT);
    label.setVerticalAlignment(JLabel.TOP);
    
    int scoreLabelWidth = 200;
    int scoreLabelHeight = 50;
    int padding = 10;
    int xPosition = frameWidth - scoreLabelWidth - (2 * padding);
    int yPosition = padding;
    this.setBounds(xPosition, yPosition, scoreLabelWidth, scoreLabelHeight);
  }
};
