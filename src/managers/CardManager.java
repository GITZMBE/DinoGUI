package src.managers;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CardManager {
    private CardLayout cardLayout;
    private Map<String, JPanel> panels;

    public CardManager() {
      cardLayout = new CardLayout();
      panels = new HashMap<>();
    }

    public void showPanel(String name) {
      cardLayout.show(panels.get(name).getParent(), name);
    }

    public void addPanel(String name, JPanel panel) {
      panels.put(name, panel);
    }
}
