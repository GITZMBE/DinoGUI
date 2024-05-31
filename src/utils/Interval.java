package src.utils;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Interval {
  private Timer intervalTimer;

  public Interval(int delay, ActionListener task) {
    this.intervalTimer = new Timer(delay, task);
  };

  public void start() {
    intervalTimer.start();
  };

  public void stop() {
    intervalTimer.stop();
  }
}
