package main.util;

import main.entity.Dish;
import java.util.ArrayList;
import java.util.List;

public class FakeAPI {

    public static List<Dish> createMenu(){
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish(1, "Spaghetti Bolognese", 12.50, "Classic Italian Pasta with Bolognese Sauce"));
        menu.add(new Dish(2, "Margherita Pizza", 10.00, "Classic Tomato Sauce Pizza"));
        menu.add(new Dish(3, "Beef Burger", 11.00, "Beef Patty, Lettuce, Tomato, Onion, Cheese"));
        return menu;
    }
}
