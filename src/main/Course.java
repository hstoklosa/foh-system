package main;

import main.enums.CourseState;
import main.enums.CourseType;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final CourseType type;
    private CourseState state;
    private List<Dish> dishes;

    public Course(CourseType type, ArrayList<Dish> dishes) {
        this.type = type;
        this.state = CourseState.PENDING;
        this.dishes = new ArrayList<>();
    }

    public CourseType getType() {
        return type;
    }

    public CourseState getState() {
        return state;
    }

    public void setState(CourseState state) {
        this.state = state;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
