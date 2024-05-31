package src.player;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player {
  private ImageIcon playerIcon;
  private JLabel playerLabel;
  private Timer jumpTimer;
  private int yPosition;
  private boolean isJumping;
  private static final int JUMP_HEIGHT = 150;
  private static final int JUMP_SPEED = 1;

  public Player(String imagePath, int initialX, int initialY, int width, int height) {
    Image playerImage = Toolkit.getDefaultToolkit().getImage(imagePath);
    Image scaledImage = playerImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    playerIcon = new ImageIcon(scaledImage);

    playerLabel = new JLabel(playerIcon);
    playerLabel.setBounds(initialX, initialY, width, height);
    yPosition = initialY;
  }

  public JLabel getPlayerLabel() {
    return playerLabel;
  }

  public void startJump() {
    if (isJumping) return;
    isJumping = true;

    jumpTimer = new Timer(JUMP_SPEED, new ActionListener() {
      private int direction = -1;
      private int jumpHeight = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        if (jumpHeight <= JUMP_HEIGHT && direction == -1) {
          yPosition += direction * 3;
          jumpHeight += 3;
        } else if (jumpHeight > 0) {
          direction = 1;
          yPosition += direction * 3;
          jumpHeight -= 3;
        } else {
          isJumping = false;
          jumpTimer.stop();
        }
        playerLabel.setLocation(playerLabel.getX(), yPosition);
      }
    });
    jumpTimer.start();
  }
}
