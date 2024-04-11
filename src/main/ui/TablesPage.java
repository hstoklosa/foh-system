package main.ui;

import javax.swing.*;
import java.awt.*;

public class TablesPage extends JPanel { //This can be a GUI with the table layout, and you can click on each table to
                                         //open up its

    private GUI parentFrame;

    public TablesPage(GUI parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        JPanel floorPlanPanel = new JPanel();
        for (int i = 1; i <= 15; i++){
            Table tableButton = new Table("Table " + i);
            tableButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
            floorPlanPanel.add(tableButton);
        }

        JButton backButton = new JButton("Home");
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Tables Page", SwingConstants.CENTER);
        label.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        add(floorPlanPanel, BorderLayout.CENTER);
    }
}
