package main.ui;

import javax.swing.*;
import java.awt.*;

public class PaymentPage extends JPanel{

    public PaymentPage(HomePage parentFrame){
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Home");
        backButton.addActionListener(e -> parentFrame.showCard("ContentPanel"));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Payment Page", SwingConstants.CENTER);
        label.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        add(label, BorderLayout.CENTER);

    }

}
