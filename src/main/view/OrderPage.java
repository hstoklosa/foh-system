package main.view;

import main.controller.OrderController;
import main.entity.Course;
import main.entity.Dish;
import main.entity.Order;
import main.entity.Table;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class OrderPage extends JPanel {
    private MainView parentFrame;

    private JList<Dish> menuList; // list for available dishes
    private DefaultListModel<Dish> menuModel;
    private JList<String> orderList;
    private DefaultListModel<String> orderModel;
    private JList<String> course1List, course2List, course3List;
    private DefaultListModel<String> course1Model, course2Model, course3Model;

    private JComboBox<String> courseSelector; // dropdown for selecting the course
    private JLabel course1StatusLabel, course2StatusLabel, course3StatusLabel;
    private JLabel totalPrice1Label, totalPrice2Label, totalPrice3Label;
    private JButton addToOrderButton, removeFromOrderButton, sendToKitchenButton;

    private Order currentOrder; // the order being manipulated (the whole thing. all 3 courses)
    private OrderController controller;

    public OrderPage(MainView parentFrame, OrderController controller) {
        this.parentFrame = parentFrame;
        this.controller = controller;

        this.currentOrder = new Order(1, 0);

        setLayout(new BorderLayout());
        createUIElements(); // creates UI
        UILayout(); // lays the UI out
    }

    private void createUIElements(){
        courseSelector = new JComboBox<>(new String[]{ "Course 1", "Course 2", "Course 3" });
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

        course1StatusLabel = new JLabel("Pending");
        course2StatusLabel = new JLabel("Pending");
        course3StatusLabel = new JLabel("Pending");

        totalPrice1Label = new JLabel("Total: £0.00");
        totalPrice2Label = new JLabel("Total: £0.00");
        totalPrice3Label = new JLabel("Total: £0.00");

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

    private void UILayout() {
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("Course"));
        northPanel.add(courseSelector);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(menuList));
        centerPanel.add(new JScrollPane(orderList));

        JPanel coursesPanel = new JPanel(new GridLayout(1, 3));
        coursesPanel.add(createCoursePanel(course1List, course1Model, course1StatusLabel, totalPrice1Label, addToOrderButton));
        coursesPanel.add(createCoursePanel(course2List, course2Model, course2StatusLabel, totalPrice2Label, removeFromOrderButton));
        coursesPanel.add(createCoursePanel(course3List, course3Model, course3StatusLabel, totalPrice3Label, sendToKitchenButton));

        setLayout(new BorderLayout());

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(coursesPanel, BorderLayout.SOUTH);
    }

    /**
    * This method creates the 3 boxes, along with the labels and buttons. If you touch this, I think everything explodes
    */
    private JPanel createCoursePanel(JList<String> list, DefaultListModel<String> model, JLabel statusLabel, JLabel totalPriceLabel, JButton button) {
        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.add(button);
        headerPanel.add(statusLabel);
        headerPanel.add(totalPriceLabel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        return panel;
    }

    /**
    * This updates the list of dishes in the ORDER based on which course is selected. This is called every time
    * something is added to the order, removed from the order or if the order is sent to the kitchen.
    */
    private void updateOrderListForSelectedCourse() {
        int courseIndex = courseSelector.getSelectedIndex();
        Course course = currentOrder.getCourses().get(courseIndex);

        orderModel.clear();
        for (Dish dish : course.getDishes()) {
            orderModel.addElement(dish.getName());
        }
    }

    /**
    * Adds the selected dish in the menu list to the current course that is selected.
    */
    private void addSelectedDishToOrder() {
        Dish selectedDish = menuList.getSelectedValue();

        if (selectedDish != null) {
            int courseIndex = courseSelector.getSelectedIndex();
            Course course = currentOrder.getCourses().get(courseIndex);
            course.getDishes().add(selectedDish);
            updateOrderListForSelectedCourse();
        } else {
            JOptionPane.showMessageDialog(this, "No dish selected");
        }
    }

    /**
    * Removes whatever selected dish is in the order list
    */
    private void removeSelectedDishFromOrder() {
        int courseIndex = courseSelector.getSelectedIndex();
        int selectedIndex = orderList.getSelectedIndex();

        if (selectedIndex != -1){
            Course course = currentOrder.getCourses().get(courseIndex);
            course.getDishes().remove(selectedIndex);
            updateOrderListForSelectedCourse();
        }
    }

    /**
    * Depending on which course is selected, all the dishes will be moved to the respective box
    */
    private void sendToKitchen() {
        DefaultListModel<String> targetModel = null;
        int courseIndex = courseSelector.getSelectedIndex(); //This gets the current course that's selected

        // chooses which box it needs to put the dishes into
        switch (courseIndex) {
            case 0 -> targetModel = course1Model;
            case 1 -> targetModel = course2Model;
            case 2 -> targetModel = course3Model;
            default -> {}
        }

        // This transfers the dishes from the order list to the course box
        if (targetModel != null) {
            Course course = currentOrder.getCourses().get(courseIndex);

            // This for loop  transfers each dish object of a list into its name,
            // and then puts them into a new list.
            for (String dishName : course.getDishes().stream().map(Dish::getName).collect(Collectors.toList())){
                targetModel.addElement(dishName);
            }

            course.getDishes().clear();
            updateOrderListForSelectedCourse();
        }
    }

    /**
    * This method changes the course status labels to whatever the actual course object's CourseStatus state is.
    * See the Course class.
    */
    private void updateStatusLabels() {
        course1StatusLabel.setText(currentOrder.getCourses().get(0).getState().toString());
        course2StatusLabel.setText(currentOrder.getCourses().get(1).getState().toString());
        course3StatusLabel.setText(currentOrder.getCourses().get(2).getState().toString());
    }
}
