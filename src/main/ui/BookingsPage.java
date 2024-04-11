package main.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class BookingsPage extends JPanel{

    private JList<Booking> bookingsList;
    private DefaultListModel<Booking> bookingsModel;

    public BookingsPage(GUI parentFrame){
        setLayout(new BorderLayout());
        bookingsModel = new DefaultListModel<>();
        bookingsList = new JList<>(bookingsModel);
        add(new JScrollPane(bookingsList), BorderLayout.CENTER);

        JButton addButton = new JButton("Add Booking");
        addButton.addActionListener(e -> addBooking());
        add(addButton, BorderLayout.SOUTH);

        JButton backButton = new JButton("Home");
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Bookings Page", SwingConstants.CENTER);
        label.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        add(label, BorderLayout.CENTER);

    }

    private void addBooking(){
        String name = JOptionPane.showInputDialog("Enter Name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter Phone Number:");
        int tableSize = Integer.parseInt(JOptionPane.showInputDialog("Enter Table Size:"));
        LocalDateTime bookingTime = LocalDateTime.now();

        Booking booking = new Booking(name, phoneNumber, tableSize, bookingTime);
        bookingsModel.addElement(booking);
    }

}
