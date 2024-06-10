package src.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataSaver {
  public static void saveData(ArrayList<String> data, String fileName) {
    File file = new File(fileName);
    File parentDir = file.getParentFile();
    try {
      if (parentDir != null && !parentDir.exists()) {
        parentDir.mkdirs();
      }
      if (!file.exists()) {
        file.createNewFile();
      }

      ArrayList<String> totalData = DataLoader.loadData("src/data/data.txt");
      totalData.addAll(data);

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (String line : totalData) {
          writer.write(line);
          writer.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void resaveTopScores(ArrayList<String> data, String fileName) {
    File file = new File(fileName);
    File parentDir = file.getParentFile();
    try {
      if (parentDir != null && !parentDir.exists()) {
        parentDir.mkdirs();
      }
      if (!file.exists()) {
        file.createNewFile();
      }

      ArrayList<String> totalData = DataLoader.loadData("src/data/data.txt");
      totalData.addAll(data);

      sortData(totalData);

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (String line : totalData) {
          writer.write(line);
          writer.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void sortData(ArrayList<String> data) {
    Collections.sort(data, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return Integer.compare(Integer.parseInt(o2), Integer.parseInt(o1));
      }
    });
  }
}
