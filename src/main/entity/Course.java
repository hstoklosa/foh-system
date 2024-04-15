package main.entity;

import main.enums.CourseStatus;
import main.enums.CourseType;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final CourseType type;
    private CourseStatus state;
    private List<Dish> dishes;

    public Course(CourseType type, ArrayList<Dish> dishes) {
        this.type = type;
        this.state = CourseStatus.PENDING;
        this.dishes = new ArrayList<>();
    }

    public CourseType getType() {
        return type;
    }

    public CourseStatus getState() {
        return state;
    }

    public void setState(CourseStatus state) {
        this.state = state;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
