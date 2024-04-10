package main.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Dish> dishes;
    private double price;
    private String desc;

    public Menu() {
        dishes = new ArrayList<>();
    }

    public void addDish(Dish dish) {
    }

    private boolean isIdExists(int id) {
        return false;
    }

    // Method to remove a dish from the menu
    public void removeDish(Dish dish) {
    }

    // Method to get all dishes in the menu
    public List<Dish> getDishes() {
        return null;
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
