package main.integration;

import main.Dish;
import main.enums.WeekDay;

import java.util.HashMap;

/**
 *
 * FOHToAdmin.java
 * An interface to interact with the "Admin" subsystem.
 *
 */

public interface FOHToAdmin {

    /**
     * Returns the number of sales per dish on a specific day
     * @param  day         A parameter coming from an Enum class.
     * @return HashMap     Returns a HashMap containing DISH_OBJECT:SALES_AMOUNT
     */
    HashMap<Dish, Integer> getSales(WeekDay day);

    /**
     * Returns the number of bookings per table.
     * @return HashMap     Returns a HashMap containing TABLE_NUMBER:AMOUNT
     */
    HashMap<Integer, Integer> getBookings();

}
