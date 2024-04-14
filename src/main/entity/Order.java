package main.entity;


import main.entity.Course;
import main.enums.CourseType;
import main.enums.OrderState;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Order.java
 * An order is associated with a specific table, contains a list of
 * courses to be served, and has an overall state reflecting its progress.
 *
 */
public class Order {
    private final int id;
    private int tableNumber;
    private List<Course> courses;
    private int currentCourseIndex;
    private OrderState state;


    /**
     * Constructs an Order by acting as a default parameter for courses.
     *
     * @param id            The unique identifier for the order.
     * @param tableNumber   The table number associated with the order.
     */
    public Order(int id, int tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.courses = new ArrayList<>();

        this.courses.add(new Course(CourseType.COURSE_AWAY_1, new ArrayList<>()));
        this.courses.add(new Course(CourseType.COURSE_AWAY_2, new ArrayList<>()));
        this.courses.add(new Course(CourseType.COURSE_AWAY_3, new ArrayList<>()));
        this.currentCourseIndex = 0;
        this.state = OrderState.PREPARING;
    }

    /**
     * Constructs an Order with a specified ID, table number, and a list of courses.
     *
     * @param id The unique identifier for the order.
     * @param tableNumber The table number associated with the order.
     * @param courses The initial list of courses for the order.
     */
    public Order(int id, int tableNumber, ArrayList<Course> courses) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.courses = courses;
        this.currentCourseIndex = -1;
        this.state = OrderState.PREPARING;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Gets the current course being served.
     *
     * @return The current course, or null if no more courses are left.
     */
    public Course getCurrentCourse() {
        // ...
        return null;
    }

    /**
     * Method that advances to the next course internally.
     */
    public void moveNextCourse() {
        // ...
    }

    /**
     * Updates the state of the order based on the presence/absence of remaining courses.
     */
    private void updateOrderState() {
        if (courses.isEmpty()) {
            this.state = OrderState.COMPLETED;
        } else {
            this.state = OrderState.IN_PROGRESS;
        }
    }

    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public int getCurrentCourseIndex() {
        return currentCourseIndex;
    }

    public void setCurrentCourseIndex(int currentCourseIndex) {
        this.currentCourseIndex = currentCourseIndex;
    }
}