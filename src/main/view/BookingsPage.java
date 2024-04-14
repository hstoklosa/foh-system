package main.view;

import main.controller.FOHController;
import main.entity.Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingsPage extends JPanel {
    private MainView parentFrame;
    private FOHController mainControl;

    private JList<Booking> bookingsList;
    private DefaultListModel<Booking> bookingsModel;

    private JButton addButton;
    private JButton removeButton;

    public BookingsPage(MainView parentFrame, FOHController mainControl) {
        this.parentFrame = parentFrame;
        this.mainControl = mainControl;
        this.bookingsModel = new DefaultListModel<>();
        this.bookingsList = new JList<>(bookingsModel);

        this.bookingsList.setFont(new Font("Open Sans", Font.PLAIN, 24).deriveFont(16f));
        this.bookingsList.setFixedCellHeight(50);
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Home");
        backButton.setBackground(new Color(208, 207, 207));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        JPanel footerPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add Booking");
        addButton.addActionListener(e -> addBooking());
        footerPanel.add(addButton);

        add(new JScrollPane(bookingsList), BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove Booking");
        removeButton.addActionListener(e -> showRemoveDialog());
        footerPanel.add(removeButton);
        add(footerPanel, BorderLayout.SOUTH);

        bookingsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    updateBooking();
                }
            }
        });

        for (Booking booking : mainControl.getCurrentBookings()) {
            System.out.println(booking);
            bookingsModel.addElement(booking);
        }
    }

    public void addBooking() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField nameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField tableSizeField = new JTextField();
        JTextField bookingTimeField = new JTextField();

        JLabel firstNameLabel = new JLabel("First Name: ");
        panel.add(firstNameLabel);
        panel.add(nameField);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        panel.add(lastNameLabel);
        panel.add(lastNameField);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        panel.add(phoneNumberLabel);
        panel.add(phoneField);

        JLabel tableSizeLabel = new JLabel("Table Size: ");
        panel.add(tableSizeLabel);
        panel.add(tableSizeField);

        JLabel bookingTimeLabel = new JLabel("Booking Time: ");
        panel.add(bookingTimeLabel);
        panel.add(bookingTimeField);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Add Booking",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                mainControl.addBooking(
                    nameField.getText(),
                    lastNameField.getText(),
                    phoneField.getText(),
                    Integer.parseInt(tableSizeField.getText())
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

        updateBookingList(mainControl.getCurrentBookings());
    }

    private void updateBookingList(List<Booking> bookings) {
        bookingsModel.clear();

        for (Booking bk : bookings) {
            bookingsModel.addElement(bk);
        }
    }

    private void updateBooking() {
        Booking selectedBooking = bookingsList.getSelectedValue();

        if (selectedBooking != null) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField firstNameField = new JTextField(selectedBooking.getFirstName());
            JTextField lastNameField = new JTextField(selectedBooking.getLastName());
            JTextField phoneField = new JTextField(selectedBooking.getPhoneNumber());
            JTextField tableSizeField = new JTextField(String.valueOf(selectedBooking.getTableSize()));
            JTextField bookingTimeField = new JTextField(selectedBooking.getBookingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            panel.add(new JLabel("First Name: "));
            panel.add(firstNameField);

            panel.add(new JLabel("Last Name: "));
            panel.add(lastNameField);

            panel.add(new JLabel("Phone Number: "));
            panel.add(phoneField);

            panel.add(new JLabel("Table Size: "));
            panel.add(tableSizeField);

            panel.add(new JLabel("Booking Time: "));
            panel.add(bookingTimeField);

            int result = JOptionPane.showConfirmDialog(
                this, panel, "Manage Booking",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    selectedBooking.setFirstName(firstNameField.getText());
                    selectedBooking.setLastName(lastNameField.getText());
                    selectedBooking.setPhoneNumber(phoneField.getText());
                    selectedBooking.setTableSize(Integer.parseInt(tableSizeField.getText()));
                    selectedBooking.setBookingTime(LocalDateTime.parse(bookingTimeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    mainControl.updateBooking(selectedBooking);
                    bookingsList.repaint(); // Refresh the JList to show updated information
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void showRemoveDialog() {
        Booking selectedBooking = bookingsList.getSelectedValue();

        if (selectedBooking != null) {
            int input = JOptionPane.showConfirmDialog(this,
                    "The booking will be permanently removed. Proceed?", "Select an Option...", JOptionPane.YES_NO_OPTION);

            if (input == 1 || input == 2) {
                return;
            }

            mainControl.removeBooking(selectedBooking);
            bookingsModel.removeElement(selectedBooking);
        } else {
            JOptionPane.showMessageDialog(this, "No booking selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultListModel<Booking> getBookingsModel() {
        return bookingsModel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }
}
