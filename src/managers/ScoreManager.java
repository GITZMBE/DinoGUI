package src.managers;

import java.util.ArrayList;

import src.utils.FormatNumber;

public class ScoreManager {
  private FormatNumber formatter = new FormatNumber();
  private ArrayList<String> scoreHistory = new ArrayList<>();
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

  public ArrayList<String> getScoreHistory() {
    return scoreHistory;
  }

  public void addScoreHistory(String newFinalScore) {
    scoreHistory.add(newFinalScore);
  };

  public void increaseScore(int inc) {
    score += inc;
  };

  public void clearScore() {
    score = 0;
  };
}
