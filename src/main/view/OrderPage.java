package main.view;

import main.controller.OrderController;
import main.controller.PaymentController;
import main.entity.Course;
import main.entity.Dish;
import main.entity.Order;
import main.entity.Table;
import main.enums.CourseType;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class OrderPage extends JPanel {
    private MainView parentFrame;

    private JList<Dish> menuList; // list for available dishes
    private DefaultListModel<Dish> menuModel;
    private JList<String> orderList;
    private DefaultListModel<String> orderModel;
    private JList<Dish> course1List, course2List, course3List;
    private DefaultListModel<Dish> course1Model, course2Model, course3Model;

    private JComboBox<String> courseSelector; // dropdown for selecting the course
    private JLabel currentCourseLabel;

    private JPanel headerPanel;
    private JLabel course1StatusLabel, course2StatusLabel, course3StatusLabel;
    private JLabel totalPrice1Label, totalPrice2Label, totalPrice3Label;
    private JButton addToOrderButton, removeFromOrderButton, sendToKitchenButton;

    private OrderController controller;

    public OrderPage(MainView parentFrame, OrderController controller) {
        this.parentFrame = parentFrame;
        this.controller = controller;

        setLayout(new BorderLayout());
        createUIElements(); // creates UI
        UILayout(); // lays the UI out
    }

    private void createUIElements() {
        headerPanel = new JPanel(new BorderLayout()); // Align, HGap, VGap

        JPanel headerInnerPanel = new JPanel();
        headerInnerPanel.add(new JLabel(controller.getTable().getOrder().getCurrentCourseString()));

        JPanel headerInnerPanel2 = new JPanel();

        JButton paymentBtn = new JButton("Go to Payment");
        paymentBtn.addActionListener(e -> {
            PaymentPage pp = new PaymentPage(parentFrame, new PaymentController(controller.getDb()));
            parentFrame.addCard(pp, "PaymentPage");
            parentFrame.showCard("PaymentPage");
        });

        headerInnerPanel2.add(paymentBtn);
        headerInnerPanel2.add(new JButton("Deallocate"));
        headerPanel.add(headerInnerPanel);
        headerPanel.add(headerInnerPanel2);

        menuModel = new DefaultListModel<>();
        menuList = new JList<>(menuModel);
        menuModel.addElement(new Dish(1, "Pizza", 12.00, "Tomato, Cheese"));
        menuModel.addElement(new Dish(2, "Burger", 10.00, "Beef, Lettuce, Tomato"));

        orderModel = new DefaultListModel<>();
        orderList = new JList<>(orderModel);

        Order order = controller.getTable().getOrder();

        course1Model = new DefaultListModel<>();
        course1List = new JList<>(course1Model);

        Course c1 = order.getCourseByType(CourseType.COURSE_AWAY_1);
        for (Dish dish : c1.getDishes()) {
            course1Model.addElement(dish);
        }

        Course c2 = order.getCourseByType(CourseType.COURSE_AWAY_2);
        course2Model = new DefaultListModel<>();
        course2List = new JList<>(course2Model);

        for (Dish dish : c2.getDishes()) {
            course2Model.addElement(dish);
        }

        Course c3 = order.getCourseByType(CourseType.COURSE_AWAY_3);
        course3Model = new DefaultListModel<>();
        course3List = new JList<>(course3Model);

        for (Dish dish : c3.getDishes()) {
            course3Model.addElement(dish);
        }

        course1StatusLabel = new JLabel("Course 1: Pending");
        course2StatusLabel = new JLabel("Course 2: Pending");
        course3StatusLabel = new JLabel("Course 3: Pending");

        totalPrice1Label = new JLabel("Total: £0.0");
        totalPrice2Label = new JLabel("Total: £0.0");
        totalPrice3Label = new JLabel("Total: £0.0");

        addToOrderButton = new JButton("Add");
        addToOrderButton.setBackground(new Color(208, 207, 207));
        removeFromOrderButton = new JButton("Remove");
        removeFromOrderButton.setBackground(new Color(208, 207, 207));
        sendToKitchenButton = new JButton("Submit Order");
        sendToKitchenButton.setBackground(new Color(208, 207, 207));

        addToOrderButton.addActionListener(e -> addSelectedDishToOrder());
        removeFromOrderButton.addActionListener(e -> removeSelectedDishFromOrder());
        sendToKitchenButton.addActionListener(e -> sendToKitchen());
    }

    private void UILayout() {
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("Course"));
//        northPanel.add(courseSelector);
        northPanel.add(headerPanel);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(menuList));
        centerPanel.add(new JScrollPane(orderList));

        JPanel coursesPanel = new JPanel(new GridLayout(1, 3));
        coursesPanel.add(createCoursePanel(course1List, course1StatusLabel, totalPrice1Label, addToOrderButton));
        coursesPanel.add(createCoursePanel(course2List, course2StatusLabel, totalPrice2Label, removeFromOrderButton));
        coursesPanel.add(createCoursePanel(course3List, course3StatusLabel, totalPrice3Label, sendToKitchenButton));

        setLayout(new BorderLayout());

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(coursesPanel, BorderLayout.SOUTH);
    }

    /**
    * This method creates the 3 boxes, along with the labels and buttons. If you touch this, I think everything explodes
    */
    private JPanel createCoursePanel(JList<Dish> list, JLabel statusLabel, JLabel totalPriceLabel, JButton button) {
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
        Order order = controller.getTable().getOrder();
        Course course = order.getCurrentCourse();

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
            Order order = controller.getTable().getOrder();
            Course course = order.getCurrentCourse();

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
        int selectedIndex = orderList.getSelectedIndex();

        if (selectedIndex != -1) {
            Order order = controller.getTable().getOrder();
            Course course = order.getCurrentCourse();
            course.getDishes().remove(selectedIndex);
            updateOrderListForSelectedCourse();
        }
    }

    /**
    * Depending on which course is selected, all the dishes will be moved to the respective box
    */
    private void sendToKitchen() {
        DefaultListModel<Dish> targetModel = null;
        Order order = controller.getTable().getOrder();

        if (order.isComplete()) {
            JOptionPane.showMessageDialog(this, "Order is completed!", "Invalid input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // chooses which box it needs to put the dishes into
        switch (order.getCurrentCourseType()) {
            case COURSE_AWAY_1 -> targetModel = course1Model;
            case COURSE_AWAY_2 -> targetModel = course2Model;
            case COURSE_AWAY_3 -> targetModel = course3Model;
            default -> {}
        }

        // transfers the dishes from the order list to the course box
        if (targetModel != null) {
            Course course = order.getCurrentCourse();
            float price = 0;

            if (course.getDishes().size() == 0) {
                JOptionPane.showMessageDialog(this, "Invalid input", "Select some dishes.", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (Dish dish : course.getDishes()) {
                price += dish.getPrice();
                targetModel.addElement(dish);
            }

            switch (order.getCurrentCourseType()) {
                case COURSE_AWAY_1 -> totalPrice1Label.setText("£" + price);
                case COURSE_AWAY_2 -> totalPrice2Label.setText("£" + price);
                case COURSE_AWAY_3 -> totalPrice3Label.setText("£" + price);
                default -> {}
            }

            order.moveNextCourse();
            controller.saveOrder(course.getType());
            orderModel.clear();
        }
    }

    /**
    * This method changes the course status labels to whatever the actual course object's CourseStatus state is.
    * See the Course class.
    */
    private void updateStatusLabels() {
        course1StatusLabel.setText(controller.getTable().getOrder().getCourses().get(0).getState().toString());
        course2StatusLabel.setText(controller.getTable().getOrder().getCourses().get(1).getState().toString());
        course3StatusLabel.setText(controller.getTable().getOrder().getCourses().get(2).getState().toString());
    }
}
