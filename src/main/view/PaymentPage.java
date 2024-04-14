package main.view;

import javax.swing.*;
import java.awt.*;

public class PaymentPage extends JPanel {
    public PaymentPage(MainView parentFrame) {
        setLayout(new BorderLayout());

        // Back button
        JButton backButton = new JButton("Home");
        backButton.setBackground(new Color(208, 207, 207));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));

        // Payment form panel
        JPanel paymentForm = new JPanel(new GridLayout(0, 2, 10, 10));
        paymentForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Components for payment form
        JLabel labelAmount = new JLabel("Total Amount:");
        JTextField fieldAmount = new JTextField("0.00");

        JLabel labelPaymentMethod = new JLabel("Payment Method:");
        JComboBox<String> comboPaymentMethod = new JComboBox<>(new String[]{"Cash", "Credit Card"});
        comboPaymentMethod.setBackground(new Color(208, 207, 207));

        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(208, 207, 207));
        payButton.addActionListener(e -> processPayment());

        JButton printReceiptButton = new JButton("Print Receipt");
        printReceiptButton.setBackground(new Color(208, 207, 207));
        printReceiptButton.addActionListener(e -> printReceipt());

        // Adding components to the form
        paymentForm.add(labelAmount);
        paymentForm.add(fieldAmount);
        paymentForm.add(labelPaymentMethod);
        paymentForm.add(comboPaymentMethod);
        paymentForm.add(payButton);
        paymentForm.add(printReceiptButton);

        add(backButton, BorderLayout.NORTH);
        add(paymentForm, BorderLayout.CENTER);
    }

    private void processPayment() {
        // Logic to process payment
        JOptionPane.showMessageDialog(this, "Payment Processed Successfully!", "Payment", JOptionPane.INFORMATION_MESSAGE);
    }

    private void printReceipt() {
        // Logic to print receipt
        JOptionPane.showMessageDialog(this, "Receipt Printed Successfully!", "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}