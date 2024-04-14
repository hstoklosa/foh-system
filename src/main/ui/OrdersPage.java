package main.ui;

import main.entity.Course;
import main.entity.Dish;
import main.entity.Order;

import main.enums.CourseStatus;
import main.enums.OrderState;
import main.enums.CourseType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdersPage extends JPanel{

    private GUI parentFrame;
    private JComboBox<String> courseSelector;
    private JList<Dish> menuList;
    private DefaultListModel<Dish> menuModel;
    private JList<String> orderList;
    private DefaultListModel<String> orderModel;
    private JList<String> course1List, course2List, course3List;
    private DefaultListModel<String> course1Model, course2Model, course3Model;
    private JLabel course1StatusLabel, course2StatusLabel, course3StatusLabel;
    private JButton addToOrderButton, removeFromOrderButton, sendToKitchenButton;
    private Order currentOrder;

    public OrdersPage(GUI parentFrame){
        this.parentFrame = parentFrame;
        this.currentOrder = new Order(1, 0);
        parentFrame.setBackground(new Color(208, 207, 207));
        setLayout(new BorderLayout());
        createUIElements();
        UILayout();
    }

    private void createUIElements(){
        courseSelector = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        courseSelector.addActionListener(e -> updateOrderListForSelectedCourse());

        courseSelector.setBackground(new Color(208, 207, 207));

        menuModel = new DefaultListModel<>();
        menuList = new JList<>(menuModel);
        menuModel.addElement(new Dish(1, "Pizza", 12.00, "Tomato, Cheese"));
        menuModel.addElement(new Dish(2, "Burger", 10.00, "Beef, Lettuce, Tomato"));

        orderModel = new DefaultListModel<>();
        orderList = new JList<>(orderModel);

        course1Model = new DefaultListModel<>();
        course2Model = new DefaultListModel<>();
        course3Model = new DefaultListModel<>();

        course1List = new JList<>(course1Model);
        course2List = new JList<>(course2Model);
        course3List = new JList<>(course3Model);

        addToOrderButton = new JButton("Add to Order");
        addToOrderButton.setBackground(new Color(208, 207, 207));
        removeFromOrderButton = new JButton("Remove from Order");
        removeFromOrderButton.setBackground(new Color(208, 207, 207));
        sendToKitchenButton = new JButton("Send to Kitchen");
        sendToKitchenButton.setBackground(new Color(208, 207, 207));

        addToOrderButton.addActionListener(e -> addSelectedDishToOrder());
        removeFromOrderButton.addActionListener(e -> removeSelectedDishFromOrder());
        sendToKitchenButton.addActionListener(e -> sendToKitchen());
    }

    private void UILayout(){
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("Course"));
        northPanel.add(courseSelector);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        centerPanel.add(new JScrollPane(menuList), BorderLayout.WEST);
        centerPanel.add(new JScrollPane(orderList), BorderLayout.EAST);

        JPanel coursesPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        JPanel actionPanel1 = new JPanel(new BorderLayout());
        JPanel actionPanel2 = new JPanel(new BorderLayout());
        JPanel actionPanel3 = new JPanel(new BorderLayout());

        actionPanel1.add(addToOrderButton, BorderLayout.NORTH);
        actionPanel1.add(new JScrollPane(course1List), BorderLayout.CENTER);

        actionPanel2.add(removeFromOrderButton, BorderLayout.NORTH);
        actionPanel2.add(new JScrollPane(course2List), BorderLayout.CENTER);

        actionPanel3.add(sendToKitchenButton, BorderLayout.NORTH);
        actionPanel3.add(new JScrollPane(course3List), BorderLayout.CENTER);

        coursesPanel.add(actionPanel1);
        coursesPanel.add(actionPanel2);
        coursesPanel.add(actionPanel3);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(coursesPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout(5, 5));
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void updateOrderListForSelectedCourse(){
        orderModel.clear();
        int courseIndex = courseSelector.getSelectedIndex();
        Course course = currentOrder.getCourses().get(courseIndex);
        for(Dish dish : course.getDishes()){
            orderModel.addElement(dish.getName());
        }
    }

    private void addSelectedDishToOrder() {
        Dish selectedDish = menuList.getSelectedValue();
        if (selectedDish != null) {
            int courseIndex = courseSelector.getSelectedIndex();
            Course course = currentOrder.getCourses().get(courseIndex);
            course.getDishes().add(selectedDish);
            updateOrderListForSelectedCourse();
        }
        else{
            JOptionPane.showMessageDialog(this, "No dish selected");
        }
    }

    private void removeSelectedDishFromOrder() {
        int courseIndex = courseSelector.getSelectedIndex();
        Course course = currentOrder.getCourses().get(courseIndex);
        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex != -1){
            course.getDishes().remove(selectedIndex);
            updateOrderListForSelectedCourse();
        }
    }

    private void sendToKitchen(){
        int courseIndex = courseSelector.getSelectedIndex();
        DefaultListModel<String> targetModel = null;
        switch(courseIndex){
            case 0:
                targetModel = course1Model;
                break;
            case 1:
                targetModel = course2Model;
                break;
            case 2:
                targetModel = course3Model;
                break;
        }

        if (targetModel != null) {
            Course course = currentOrder.getCourses().get(courseIndex);
            for(String dishName : course.getDishes().stream().map(Dish::getName).collect(Collectors.toList())){
                targetModel.addElement(dishName);
            }
            course.getDishes().clear();
            updateOrderListForSelectedCourse();
        }
    }
}
