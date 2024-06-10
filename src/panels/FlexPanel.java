package src.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class FlexPanel extends JPanel {
  private GridBagConstraints gbc;

  public FlexPanel(ArrayList<JComponent> components, int gap) {
    initializePanel(gap);
    addComponents(components);
  };

  private void initializePanel(int gap) {
    setBackground(Color.decode("#484A47"));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(20, 10, 20, 10);
    gbc.ipadx = gap;
    gbc.ipady = gap;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
  }

  private void addComponents(ArrayList<JComponent> components) {
    for (JComponent comp : components) {
      GridBagConstraints constraints = (GridBagConstraints) gbc.clone();
      add(comp, constraints);
      gbc.gridx++;
    }

    repaint();
    revalidate();
  }
}
