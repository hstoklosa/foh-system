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
    private List<Course> courses;
    private CourseType currentCourseType;
    private OrderState state;

    /**
     * Constructs an Order by acting as a default parameter for courses.
     */
    public Order() {
        this.id = 0;
        this.courses = new ArrayList<>();
        this.currentCourseType = CourseType.COURSE_AWAY_1;
        this.state = OrderState.IN_PROGRESS;

        this.courses.add(new Course(CourseType.COURSE_AWAY_1, new ArrayList<>()));
        this.courses.add(new Course(CourseType.COURSE_AWAY_2, new ArrayList<>()));
        this.courses.add(new Course(CourseType.COURSE_AWAY_3, new ArrayList<>()));
    }

    /**
     * Constructs an Order with a specified ID, table number, and a list of courses.
     *
     * @param id The unique identifier for the order.
     * @param courses The initial list of courses for the order.
     */
    public Order(int id, ArrayList<Course> courses) {
        this.id = id;
        this.courses = courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course getCourseByType(CourseType ct) {
        for (Course c : courses) {
            if (c.getType().equals(ct))
                return c;
        }

        return null;
    }

    /**
     * Gets the current course being served.
     *
     * @return The current course, or null if no more courses are left.
     */
    public Course getCurrentCourse() {
        return getCourseByType(currentCourseType);
    }

    /**
     * Method that advances to the next course internally.
     */
    public void moveNextCourse() {
        switch (currentCourseType) {
            case COURSE_AWAY_1 -> setCurrentCourseType(CourseType.COURSE_AWAY_2);
            case COURSE_AWAY_2 -> setCurrentCourseType(CourseType.COURSE_AWAY_3);
            default -> { setState(OrderState.COMPLETED); }
        }
    }

    /**
     * Updates the state of the order based on the presence/absence of remaining courses.
     */
    private void updateOrderState() {

    }

    public boolean isComplete() {
        return state.equals(OrderState.COMPLETED);
    }

    public int getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setCurrentCourseType(CourseType currentCourse) {
        this.currentCourseType = currentCourse;
    }

    public CourseType getCurrentCourseType() {
        return currentCourseType;
    }

    public String getCurrentCourseString() {
        switch (currentCourseType) {
            case COURSE_AWAY_1 -> {
                return "Course 1";
            }
            case COURSE_AWAY_2 -> {
                return "Course 2";
            }
            case COURSE_AWAY_3 -> {
                return "Course 3";
            }
            default -> {}
        }

        return null;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}

