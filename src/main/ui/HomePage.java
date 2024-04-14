package main.ui;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    public HomePage() {
        setLayout(new BorderLayout());
        add(createOverviewPanel(), BorderLayout.CENTER);
    }

    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));  // Grid layout for simple organization

        // Add components to the overview panel
        panel.add(new JLabel("thing 1"));
        panel.add(new JLabel("thing 2"));

        // You can replace these JLabels with more complex components as needed
        return panel;
    }
}