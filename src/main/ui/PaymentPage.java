package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PaymentPage extends JPanel {
    private JComboBox<String> discount;
    private JComboBox<Integer> split;
    private JTextField fieldAmount;

    public PaymentPage(GUI parentFrame) {
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Home");
        backButton.setBackground(new Color(208, 207, 207));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));

        JPanel paymentForm = new JPanel(new GridLayout(0, 2, 10, 10));
        paymentForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelAmount = new JLabel("Total Amount:");
        fieldAmount = new JTextField("0.00");

        JLabel labelPaymentMethod = new JLabel("Payment Method:");
        JComboBox<String> comboPaymentMethod = new JComboBox<>(new String[]{"Cash", "Credit Card"});
        comboPaymentMethod.setBackground(new Color(208, 207, 207));

        JLabel labelDiscount = new JLabel("Discount:");
        discount = new JComboBox<>(new String[]{"0%", "10%", "20%", "30%"});
        discount.setBackground(new Color(208, 207, 207));
        discount.addActionListener(this::applyDiscount);

        JLabel labelSplit = new JLabel("Split Bill:");
        split = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});
        split.setBackground(new Color(208, 207, 207));
        split.addActionListener(this::splitBill);

        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(208, 207, 207));
        payButton.addActionListener(e -> processPayment());

        JButton printReceiptButton = new JButton("Print Receipt");
        printReceiptButton.setBackground(new Color(208, 207, 207));
        printReceiptButton.addActionListener(e -> printReceipt());


        paymentForm.add(labelAmount);
        paymentForm.add(fieldAmount);
        paymentForm.add(labelPaymentMethod);
        paymentForm.add(comboPaymentMethod);
        paymentForm.add(labelDiscount);
        paymentForm.add(discount);
        paymentForm.add(labelSplit);
        paymentForm.add(split);
        paymentForm.add(payButton);
        paymentForm.add(printReceiptButton);

        add(backButton, BorderLayout.NORTH);
        add(paymentForm, BorderLayout.CENTER);
    }

    private void applyDiscount(ActionEvent e){
        updateAmount();
    }

    private void splitBill(ActionEvent e){
        updateAmount();
    }

    private void updateAmount(){
        double originalAmount = Double.parseDouble(fieldAmount.getText().trim());
        int splitWays = (Integer) split.getSelectedItem();
        double discountRate = Integer.parseInt(((String) discount.getSelectedItem()).replace("%", "").trim()) / 100.0;
        double discountedAmount = originalAmount * (1 - discountRate);
        double splitAmount = discountedAmount / splitWays;

        fieldAmount.setText(String.format("%.2f", splitAmount));

    }

    private void processPayment() {
        JOptionPane.showMessageDialog(this, "Payment Processed Successfully!", "Payment", JOptionPane.INFORMATION_MESSAGE);
    }

    private void printReceipt() {
        JOptionPane.showMessageDialog(this, "Receipt Printed Successfully!", "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}