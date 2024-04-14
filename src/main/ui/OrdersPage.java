package main.ui;

import main.entity.*;
import main.enums.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class OrdersPage extends JPanel{

    private JComboBox<String> tableSelector;
    private JList<Dish> menuList;
    private DefaultListModel<Dish> menuModel;
    private JList<String> orderList;
    private DefaultListModel<String> orderModel;
    private JButton addToOrderButton, removeFromOrderButton, sendToKitchenButton, nextCourseButton;

    private Map<String, DefaultListModel<String>> tableOrders;

    public OrdersPage(GUI parentFrame){
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Home");
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        tableOrders = new HashMap<>();

        initializeComponents();

        configureLayout();
    }


    private void initializeComponents(){
        String[] tables = {"Table 1", "Table 2", "Table 3", "Table 4"};
        tableSelector = new JComboBox<>(tables);
        tableSelector.addActionListener(e -> updateOrderListForSelectedTable());

        for (String table : tables){
            tableOrders.put(table, new DefaultListModel<>());
        }

        menuModel = new DefaultListModel<>();
        menuList = new JList<>(menuModel);
        menuModel.addElement(new Dish(1, "Pizza", 12.00, "Tomato, Cheese"));
        menuModel.addElement(new Dish(2, "Burger", 10.00, "Beef, Lettuce, Tomato"));

        orderModel = new DefaultListModel<>();
        orderList = new JList<>(orderModel);

        addToOrderButton = new JButton("Add to Order");
        removeFromOrderButton = new JButton("Remove from Order");
        sendToKitchenButton = new JButton("Send to Kitchen");
        nextCourseButton = new JButton("Next Course");

        addToOrderButton.addActionListener(e -> addSelectedDishToOrder());
        removeFromOrderButton.addActionListener(e -> removeSelectedDishFromOrder());
//        sendToKitchenButton.addActionListener(e -> sendOrderToKitchen());
//        nextCourseButton.addActionListener(e -> notifyNextCourse());
    }

    private void configureLayout(){
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(menuList));
        centerPanel.add(new JScrollPane(orderList));

        JPanel southPanel = new JPanel();
        southPanel.add(addToOrderButton);
        southPanel.add(removeFromOrderButton);
        southPanel.add(sendToKitchenButton);
        southPanel.add(nextCourseButton);

        add(tableSelector, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void updateOrderListForSelectedTable(){
        String selectedTable = (String) tableSelector.getSelectedItem();
        DefaultListModel<String> orders = tableOrders.getOrDefault(selectedTable, new DefaultListModel<>());
        orderList.setModel(orders);
    }

    private void addSelectedDishToOrder(){
        Dish selectedDish = menuList.getSelectedValue();
        if(selectedDish != null){
            String selectedTable = (String) tableSelector.getSelectedItem();
            tableOrders.get(selectedTable).addElement(selectedDish.getName());
            updateOrderListForSelectedTable();
        }
    }

    private void removeSelectedDishFromOrder(){
        String selectedTable = (String) tableSelector.getSelectedItem();
        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex != -1){
            tableOrders.get(selectedTable).remove(selectedIndex);
            updateOrderListForSelectedTable();
        }
        orderModel.remove(selectedIndex);
    }
}
