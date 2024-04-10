package main.ui;

import javax.swing.*;
import java.awt.*;

public class TablesPage extends JPanel { //This can be a GUI with the table layout, and you can click on each table to
                                         //open up its

    private HomePage parentFrame;

    public TablesPage(HomePage parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        JPanel floorPlanPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        for (int i = 1; i <= 15; i++){
            Table tableButton = new Table("Table " + i);
            floorPlanPanel.add(tableButton);
        }

        JButton backButton = new JButton("Home");
        backButton.addActionListener(e -> parentFrame.showCard("ContentPanel"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Tables Page", SwingConstants.CENTER);
        add(floorPlanPanel, BorderLayout.CENTER);
    }
}
