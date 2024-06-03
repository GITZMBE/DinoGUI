package src.utils;

import java.util.Random;

public class RandomInt {
  Random random = new Random();

  public int generate(int min, int max) {
    return random.nextInt(max - min) + min;
  };
};
