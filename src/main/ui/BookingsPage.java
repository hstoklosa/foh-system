package main.ui;

import main.entity.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingsPage extends JPanel {

    private JList<Booking> bookingsList;
    private DefaultListModel<Booking> bookingsModel;

    private JDialog form;
    private JButton submitForm;
    public JTextArea name = new JTextArea();
    public JTextArea phone = new JTextArea();
    public JTextArea seats = new JTextArea();

    private JButton addButton;
    private JButton removeButton;

    public BookingsPage(GUI parentFrame) {
        setLayout(new BorderLayout());

        bookingsModel = new DefaultListModel<>();
        bookingsList = new JList<>(bookingsModel);
        add(new JScrollPane(bookingsList), BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Booking");
        addButton.setBackground(new Color(208, 207, 207));
        addButton.addActionListener(e -> addBooking());
        footerPanel.add(addButton);

        JButton removeButton = new JButton("Remove Booking");
        removeButton.setBackground(new Color(208, 207, 207));
        removeButton.addActionListener(e -> removeBooking());
        footerPanel.add(removeButton);

        add(footerPanel, BorderLayout.SOUTH);

        JButton backButton = new JButton("Home");
        backButton.setBackground(new Color(208, 207, 207));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        bookingsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    updateBooking();
                }
            }
        });

//        this.form = new JDialog(parentFrame);
//        this.form.setSize(200, 300);
//        this.form.setLayout(new BorderLayout());
//
//        JPanel inputContainer = new JPanel();
//        inputContainer.setLayout(new BoxLayout(inputContainer, BoxLayout.Y_AXIS));
//        inputContainer.add(name);
//        inputContainer.add(phone);
//        inputContainer.add(seats);
//        this.form.add(inputContainer, BorderLayout.CENTER);
//
//        this.submitForm = new JButton("Save");
//        this.form.add(submitForm, BorderLayout.SOUTH);
//
//        this.form.setVisible(true);
    }

    private void addBooking() {
        String name = JOptionPane.showInputDialog(this, "Enter Name:");
        String phoneNumber = JOptionPane.showInputDialog(this, "Enter Phone Number:");
        int tableSize = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Table Size:"));
        LocalDateTime bookingTime = LocalDateTime.now();

        Booking booking = new Booking(name, phoneNumber, tableSize, bookingTime);
        bookingsModel.addElement(booking);
    }

    private void updateBooking() {
        Booking selectedBooking = bookingsList.getSelectedValue();
        if (selectedBooking != null) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField nameField = new JTextField(selectedBooking.getName());
            JTextField phoneField = new JTextField(selectedBooking.getPhoneNumber());
            JTextField tableSizeField = new JTextField(String.valueOf(selectedBooking.getTableSize()));
            JTextField bookingTimeField = new JTextField(selectedBooking.getBookingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Phone Number:"));
            panel.add(phoneField);
            panel.add(new JLabel("Table Size:"));
            panel.add(tableSizeField);
            panel.add(new JLabel("Booking Time:"));
            panel.add(bookingTimeField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Manage Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    selectedBooking.setName(nameField.getText());
                    selectedBooking.setPhoneNumber(phoneField.getText());
                    selectedBooking.setTableSize(Integer.parseInt(tableSizeField.getText()));
                    selectedBooking.setBookingTime(LocalDateTime.parse(bookingTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    bookingsList.repaint(); // Refresh the JList to show updated information
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void removeBooking() {
        Booking selectedBooking = bookingsList.getSelectedValue();
        if (selectedBooking != null) {
            bookingsModel.removeElement(selectedBooking);
        } else {
            JOptionPane.showMessageDialog(this, "No booking selected.", "Error: No booking selected", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultListModel<Booking> getBookingsModel() {
        return bookingsModel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubmitForm() {
        return submitForm;
    }
}
