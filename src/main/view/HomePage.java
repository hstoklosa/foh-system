package main.view;

import main.controller.FOHController;
import main.entity.Booking;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import main.util.FakeAPI;
import main.entity.Dish;
import main.entity.Menu;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    private FOHController controller;

    public HomePage(FOHController controller) {
        this.controller = controller;

        setLayout(new BorderLayout());

        JPanel jp = new JPanel(new BorderLayout());
        jp.add(createMenuPanel(), BorderLayout.CENTER);
        jp.add(createBookingsPanel(), BorderLayout.EAST);
        jp.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        add(jp);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateTodaysBookings();
            }
        });
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new BorderLayout());

        JLabel menuPanelLabel = new JLabel("Today's Menu");
        menuPanelLabel.setFont(new Font("Open Sans", Font.BOLD, 21));
        menuPanel.add(menuPanelLabel, BorderLayout.NORTH);

        DefaultListModel<Dish> menuModel = new DefaultListModel<>();
        JList<Dish> menuList = new JList<>(menuModel);
        menuList.setFixedCellHeight(30);
        menuList.setFont(new Font("Open Sans", Font.PLAIN, 14));

        Menu menu = FakeAPI.getMenu();
        menu.getDishes().forEach(menuModel::addElement);

        JScrollPane menuScrollPane = new JScrollPane(menuList);

        menuList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    Dish selectedDish = menuList.getSelectedValue();

                    if(selectedDish != null)
                        showDishDetails(selectedDish);
                }
            }
        });

        menuScrollPane.setPreferredSize(new Dimension(300, 0));
        menuPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);

        return menuPanel;
    }

    private JPanel createBookingsPanel(){
        JPanel bookingsPanel = new JPanel(new BorderLayout());

        JLabel menuPanelLabel = new JLabel("Today's Bookings");
        menuPanelLabel.setFont(new Font("Open Sans", Font.BOLD, 21));
        bookingsPanel.add(menuPanelLabel, BorderLayout.NORTH);

        DefaultListModel<Booking> model = new DefaultListModel<>();
        JList<Booking> bookingJList = new JList<>(model);
        bookingJList.setFixedCellHeight(50);
        bookingJList.setFont(new Font("Open Sans", Font.PLAIN, 14));

        List<Booking> todayBookings = controller.getTodaysBookings();
        todayBookings.forEach(model::addElement);

        JScrollPane bookingsScrollPane = new JScrollPane(bookingJList);
        bookingsScrollPane.setPreferredSize(new Dimension(500, 0));
        bookingsPanel.add(bookingsScrollPane, BorderLayout.CENTER);
        return bookingsPanel;
    }

    private void updateTodaysBookings(){
        removeAll();
        add(createMenuPanel(), BorderLayout.CENTER);
        add(createBookingsPanel(), BorderLayout.EAST);
        revalidate();;
        repaint();
    }

    private void showDishDetails(Dish dish){
        String message = String.format("Price: $%.2f\nDescription: %s", dish.getPrice(), dish.getDescription());
        JOptionPane.showMessageDialog(this, message, "Details of " + dish.getName(), JOptionPane.INFORMATION_MESSAGE);
    }
}