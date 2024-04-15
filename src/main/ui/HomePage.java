package main.ui;

import main.controller.FOHController;
import main.entity.Booking;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    private FOHController controller;

    public HomePage(FOHController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        add(createOverviewPanel(), BorderLayout.CENTER);
        add(createBookingsPanel(), BorderLayout.EAST);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateTodaysBookings();
            }
        });
    }

    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));  // Grid layout for simple organization
        // Add components to the overview panel
        panel.add(new JLabel("thing 1"));
        panel.add(new JLabel("thing 2"));

        // You can replace these JLabels with more complex components as needed
        return panel;
    }

    private JPanel createBookingsPanel(){
        JPanel bookingsPanel = new JPanel(new BorderLayout());
        bookingsPanel.setBorder(BorderFactory.createTitledBorder("Today's Bookings"));

        DefaultListModel<Booking> model = new DefaultListModel<>();
        JList<Booking> bookingJList = new JList<>(model);
        bookingJList.setFixedCellHeight(30);
        bookingJList.setFont(new Font("Open Sans", Font.PLAIN, 14));

        List<Booking> todaysBookings = controller.getTodaysBookings();
        todaysBookings.forEach(model::addElement);

        bookingsPanel.add(new JScrollPane(bookingJList), BorderLayout.CENTER);
        return bookingsPanel;
    }

    private void updateTodaysBookings(){
        removeAll();
        add(createOverviewPanel(), BorderLayout.CENTER);
        add(createBookingsPanel(), BorderLayout.EAST);
        revalidate();;
        repaint();
    }
}