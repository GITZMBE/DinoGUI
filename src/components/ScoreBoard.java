package src.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import src.utils.FontLoader;
import src.utils.FormatNumber;
import src.utils.Interval;

public class ScoreBoard {
  private int frameWidth;
  private JLabel scoreLabel;
  private int score = 0;
  private static final int SCORE_SPEED = 20;
  private Interval interval;
  private FormatNumber formatter;
  private FontLoader fontLoader = new FontLoader();
  private Font font;

  public ScoreBoard(int frameWidth, int x, int y, int width, int height) {
    this.frameWidth = frameWidth;
    this.scoreLabel = new JLabel();
    this.formatter = new FormatNumber();
    scoreLabel.setBounds(x, y, width, height);
    styleScoreLabel();
    start();
  };

  public JLabel getScoreLabel() {
    return this.scoreLabel;
  };

  public int getScore() {
    return this.score;
  }

  public void start() {
    interval = new Interval(1000 / SCORE_SPEED, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        score++;
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
    String formattedScore = formatter.formatToScore(score);
    scoreLabel.setText(formattedScore);
  };

  private void styleScoreLabel() {
    scoreLabel.setOpaque(true);
    scoreLabel.setBackground(Color.decode("#484A47"));
    scoreLabel.setForeground(Color.WHITE);
    fontLoader.loadFont("arcade.ttf", 32);
    font = fontLoader.getFont();
    scoreLabel.setFont(font);
    scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
    scoreLabel.setVerticalAlignment(JLabel.TOP);
    
    int scoreLabelWidth = 200;
    int scoreLabelHeight = 50;
    int padding = 10;
    int xPosition = frameWidth - scoreLabelWidth - (2 * padding);
    int yPosition = padding;
    scoreLabel.setBounds(xPosition, yPosition, scoreLabelWidth, scoreLabelHeight);
  }
};
