package main.integration;

import main.Dish;
import main.Wine;
import main.enums.Day;

import java.util.HashMap;


/**
 *
 * IFrontHouse.java
 * Provided interface of the "Front of House" subsystem. It lists the methods via which the
 * functionality of the "FOH" subsystem is accessible by the other subsystems.
 *
 */

public interface IFrontHouse {

    void updateOrderStatus(int orderId);


    // Returns the number of sales per dish on a specific day
    HashMap<Dish, Integer> getSales(Day day);

    // Returns the number of booking per table
    HashMap<Integer, Integer> getTableBookings();

    // Returns staff availability on a specific day
    HashMap<String, Boolean> getStaffAvailability(Day day);

    int getWineOrder(Wine wine);
}
