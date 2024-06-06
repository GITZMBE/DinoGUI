package src.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {
  public static ArrayList<String> loadData(String fileName) {
    File file = new File(fileName);
    ArrayList<String> data = new ArrayList<>();

    if (!file.exists()) {
        return data;
    //   try {
    //     file.getParentFile().mkdirs();
    //     file.createNewFile();
    //   } catch (IOException e) {
    //     e.printStackTrace();
    //     return data;
    //   }
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        data.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return data;
  }
}
