package main.ui;

import javax.swing.*;
import java.awt.*;

public class BookingsPage extends JPanel{

    public BookingsPage(HomePage parentFrame){
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Home");
        backButton.addActionListener(e -> parentFrame.showCard("ContentPanel"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Bookings Page", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

    }

}
