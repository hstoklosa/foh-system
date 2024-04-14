package main.ui;

import main.entity.Course;
import main.entity.Dish;
import main.entity.Order;
import main.enums.OrderState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class OrdersPage extends JPanel{

    private GUI parentFrame;
    private JComboBox<String> tableSelector, courseSelector;
    private JList<Dish> menuList;
    private DefaultListModel<Dish> menuModel;
    private JList<String> orderList;
    private DefaultListModel<String> orderModel;
    private JButton addToOrderButton, removeFromOrderButton, sendToKitchenButton;
    private Map<String, Order> tableOrders;

    public OrdersPage(GUI parentFrame){
        this.parentFrame = parentFrame;
        parentFrame.setBackground(new Color(208, 207, 207));
        setLayout(new BorderLayout());
        initializeComponents();
        configureLayout();
    }

    private void initializeComponents(){
        tableSelector = new JComboBox<>(new String[]{"Table 1", "Table 2", "Table 3", "Table 4"});
        tableSelector.addActionListener(e -> updateOrderListForSelectedTable());

        courseSelector = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        courseSelector.addActionListener(e -> updateOrderListForSelectedCourse());

        tableSelector.setBackground(new Color(208, 207, 207));
        courseSelector.setBackground(new Color(208, 207, 207));

        tableOrders = new HashMap<>();

        menuModel = new DefaultListModel<>();
        menuList = new JList<>(menuModel);
        menuModel.addElement(new Dish(1, "Pizza", 12.00, "Tomato, Cheese"));
        menuModel.addElement(new Dish(2, "Burger", 10.00, "Beef, Lettuce, Tomato"));

        orderModel = new DefaultListModel<>();
        orderList = new JList<>(orderModel);

        addToOrderButton = new JButton("Add to Order");
        addToOrderButton.setBackground(new Color(208, 207, 207));
        removeFromOrderButton = new JButton("Remove from Order");
        removeFromOrderButton.setBackground(new Color(208, 207, 207));
        sendToKitchenButton = new JButton("Send to Kitchen");
        sendToKitchenButton.setBackground(new Color(208, 207, 207));

        addToOrderButton.addActionListener(e -> addSelectedDishToOrder());
        removeFromOrderButton.addActionListener(e -> removeSelectedDishFromOrder());
    }

    private void configureLayout(){
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("Table:"));
        northPanel.add(tableSelector);
        northPanel.add(new JLabel("Course"));
        northPanel.add(courseSelector);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(menuList), BorderLayout.WEST);
        centerPanel.add(new JScrollPane(orderList), BorderLayout.EAST);

        JPanel southPanel = new JPanel();
        southPanel.add(addToOrderButton);
        southPanel.add(removeFromOrderButton);
        southPanel.add(sendToKitchenButton);

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void updateOrderListForSelectedTable(){
        String selectedTable = normalizeKey((String) tableSelector.getSelectedItem());
        if(selectedTable != null){
            Order order = tableOrders.computeIfAbsent(selectedTable, k -> {
                System.out.println("Creating a new order for table: " + k);
                return new Order(generateOrderId(), Integer.parseInt(k.substring(6).trim()));
            });
            updateOrderListForSelectedCourse();
        }
        else{
            System.out.println("no Table Selected");
        }
    }

    private void updateOrderListForSelectedCourse(){
        orderModel.clear();
        String selectedTable = normalizeKey((String) tableSelector.getSelectedItem());
        int courseIndex = courseSelector.getSelectedIndex();
        Order order = tableOrders.get(selectedTable);
        Course course = order.getCourses().get(courseIndex);

        for(Dish dish : course.getDishes()){
            orderModel.addElement(dish.getName());
        }
    }

    private void addSelectedDishToOrder() {
        Dish selectedDish = menuList.getSelectedValue();
        if (selectedDish != null) {
            String selectedTable = normalizeKey((String) tableSelector.getSelectedItem());
            Order order = tableOrders.get(selectedTable);
            if (order != null) {
                int courseIndex = courseSelector.getSelectedIndex();
                if (courseIndex >= 0 && courseIndex < order.getCourses().size()) {
                    Course course = order.getCourses().get(courseIndex);
                    course.getDishes().add(selectedDish);
                    updateOrderListForSelectedCourse();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid course selection. Course index: " + courseIndex);
                }
            } else {
                System.out.println("No order found for the selected table: " + selectedTable);
                JOptionPane.showMessageDialog(this, "Order is not available for the selected table.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No dish selected.");
        }
    }

    private void removeSelectedDishFromOrder() {
        String selectedTable = normalizeKey((String) tableSelector.getSelectedItem());
        int courseIndex = courseSelector.getSelectedIndex();
        Order order = tableOrders.get(selectedTable);
        Course course = order.getCourses().get(courseIndex);

        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex != -1) {
            course.getDishes().remove(selectedIndex);
            updateOrderListForSelectedCourse();
        }
    }

    private int generateOrderId() {
        return tableOrders.size() + 1;
    }

    private String normalizeKey(String key){
        return key.trim().toLowerCase();
    }
}
