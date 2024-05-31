package src;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
  public GUI() {
    JFrame frame = new JFrame();

    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
    panel.setLayout(new GroupLayout(0, 1));

    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloaseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Dino - GUI");
    frame.pack();
    frame.setVisible(true);
  };

  public static void main(String[] args) {
    new GUI();
  }
};
