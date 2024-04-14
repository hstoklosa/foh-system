package main.interfaces.internal;

import main.entity.Dish;
import main.entity.Order;
import main.enums.WeekDay;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * IFrontHouse.java
 * Provided interface of the "Front of House" subsystem. It lists the methods via which
 * the functionality of the "FOH" subsystem is accessible by the other subsystems.
 *
 */

public interface IFrontHouse {

    /**
     * The method notifies the kitchen whenever a table is ready to move onto the next course.
     * @param orderId   The order ID associated with the previously provided Order object.
     */
    void notifyNextCourse(int orderId);

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
