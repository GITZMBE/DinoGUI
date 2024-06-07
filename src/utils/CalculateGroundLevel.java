package src.utils;

import src.GUI;

public final class CalculateGroundLevel {
  public static int calculate(int labelHeight, int panelHeight) {
    int groundLevel = panelHeight - labelHeight - GUI.GROUND_LEVEL;
    return groundLevel;
  };
}
