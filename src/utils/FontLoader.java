package src.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontLoader {
  private Font font;

  public FontLoader() {}
  
  public void loadFont(String fontname, float size) {
    try {
      font = Font.createFont(Font.TRUETYPE_FONT, new File("public/fonts/" + fontname)).deriveFont(size);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(font);
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }
  }

  public Font getFont() {
    return this.font;
  }
}
