package main.ui;

import javax.swing.*;
import java.awt.*;

public class OrdersPage extends JPanel{

    public OrdersPage(GUI parentFrame){
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Home");
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Orders Page", SwingConstants.CENTER);
        label.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        add(label, BorderLayout.CENTER);

    }

}
