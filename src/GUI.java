package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
  private JFrame frame = new JFrame();
  private JPanel panel = new JPanel();

  public GUI() {
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
    panel.setLayout(new GridLayout(0, 1));
    panel.setPreferredSize(new Dimension(500, 350));

    frame.setLocationByPlatform(true);
    
    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Dino - GUI");
    Image logo = Toolkit.getDefaultToolkit().getImage(".//res//dino.png");
    frame.setIconImage(logo);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  };

  public static void main(String[] args) {
    new GUI();
  }
};