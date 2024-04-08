package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PaymentPage extends JPanel{

    public PaymentPage(HomePage parentFrame){
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Home");
        backButton.addActionListener(e -> parentFrame.showCard("ContentPanel"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Payment Page", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

    }

}
