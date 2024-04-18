package main.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Dish> dishes;

    public Menu(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Menu() {
        this(new ArrayList<>());
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {}

    public List<Dish> getDishes() {
        return dishes;
    }

    public void printMenu() {
    }

    public void setPrice(double p){
    }

    public double getPrice(){
        return 0;
    }

    public void setDesc(String d){
    }

    public String getDesc(){
        return null;
    }
}
