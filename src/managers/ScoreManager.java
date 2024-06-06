package src.managers;

import src.utils.FormatNumber;

public class ScoreManager {
  private FormatNumber formatter = new FormatNumber();
  private int score = 0;

  public String getFormatScore() {
    return formatter.formatToScore(score);
  };

  public int getScore() {
    return score;
  }

  public void setScore(int newScore) {
    score = newScore;
  };

  public void increaseScore(int inc) {
    score += inc;
  };

  public void clearScore() {
    score = 0;
  };
}
