package src.managers;

import src.utils.Interval;

public class SpeedManager {
  public static final int maxSpeed = 8;
  public static final int incrementSpeed = 20000;
  public int speed = 4;
  private Interval interval;

  public SpeedManager() {}

  public void startSpeedIncrementation() {
    interval = new Interval(incrementSpeed, e -> {
      if (speed >= maxSpeed) {
        stopSpeedIncrementation();
        return;
      }
      speed++;
    });
    interval.start();
  }

  private void stopSpeedIncrementation() {
    interval.stop();
  }
}
