package main.interfaces.internalApi;

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
     * The method sends an order object to the kitchen subsystem. It has
     * all the information required for the kitchen to prepare the dishes.
     * @param order   Parameter coming from an Enum class.
     */
    void submitOrder(Order order);

    /**
     * The method notifies the kitchen whenever a table is ready to move onto the next course.
     * @param orderId   The order ID associated with the previously provided Order object.
     */
    void notifyNextCourse(int orderId);

    /**
     * The method notifies the kitchen on any dish alterations based on the orderId, which can be either accepted or declined.
     * @param  orderId   The order ID of the Order object that has been previously provided.
     * @return boolean   Returns true if the alteration has been accepted, false otherwise.
     */
    boolean notifyDishAlteration(int orderId, int dishId, String note);

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
