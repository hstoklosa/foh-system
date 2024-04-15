package main.util;

import main.entity.Dish;
import main.enums.WeekDay;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class FakeAPI {

    public static List<Dish> createMenu(){
        List<Dish> menu = new ArrayList<>();

        menu.add(new Dish(1, "Warm Onion Tart", 12.00, "Quickes Goats Cheese, Worcestershire and Shallots"));
        menu.add(new Dish(2, "Venison Pâté en Croûte", 13.00, "Hedgerow Jelly, Mustard Fruit and Pistachio"));
        menu.add(new Dish(3, "Lasagne of Rabbit Shoulder", 12.00, "Mushrooms, Riesling and Thyme"));
        menu.add(new Dish(9, "Grilled Beef Tongue", 14.00, "Quince, Aged Vinegar and Beetroot"));
        menu.add(new Dish(4, "Roast Cornfish Monkfish", 28.00, "Cheek, Butternut Squash and Sage"));
        menu.add(new Dish(5, "Our Iberian Pork", 32.00, "Jerusalem Artichoke and Pickled Walnuts"));
        menu.add(new Dish(6, "Wareham Dorset Sika Deer", 35.00, "Pale Ale, Prune and Spring Onion"));
        menu.add(new Dish(7, "Short Rib of Red Ruby Beef", 35.00, "Spinach, Chanterelles and Horseradish"));
        menu.add(new Dish(8, "Apple Parfait", 8.00, "Shortbread, Hazelnuts and Sherry"));
        menu.add(new Dish(10, "Plum Ripple Ice Cream", 7.00, "Caramelised Pastry, Almond Cream and Camomile"));
        menu.add(new Dish(11, "Custard Flan", 8.00, "Quince and Crème Fraîche"));
        menu.add(new Dish(12, "Selection of Cheese", 12.00, "Tunworth, Lincolnshire Poacher, Beauvale Blue Crackers and Homemade Chutney"));

        return menu;
    }

    public static Map<WeekDay, Integer> getAverageBookings(){
        Map<WeekDay, Integer> averageBookings = new EnumMap<>(WeekDay.class);
        averageBookings.put(WeekDay.MONDAY, 13);
        averageBookings.put(WeekDay.TUESDAY, 9);
        averageBookings.put(WeekDay.WEDNESDAY, 14);
        averageBookings.put(WeekDay.THURSDAY, 18);
        averageBookings.put(WeekDay.FRIDAY, 25);
        averageBookings.put(WeekDay.SATURDAY, 30);
        averageBookings.put(WeekDay.SUNDAY, 20);
        return averageBookings;
    }
}
