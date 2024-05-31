package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.player.Player;

public class GUI {
  private JFrame frame = new JFrame();
  private JPanel panel = new JPanel();
  private Player player;

  public GUI() {
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
    panel.setLayout(null);
    panel.setPreferredSize(new Dimension(750, 500));
    panel.setBackground(Color.decode("#123123"));

    player = new Player(".//res//dino.png", 100, 200, 100, 100);
    panel.add(player.getPlayerLabel());
    
    frame.setLocationByPlatform(true);
    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Dino - GUI");
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(".//res//dino.png"));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    frame.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_UP) {
              player.startJump();
          }
      }
    });
  };

  public static void main(String[] args) {
    new GUI();
  }
};