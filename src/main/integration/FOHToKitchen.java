package main.integration;

import main.Order;


/**
 *
 * FOHToKitchen.java
 * An interface to interact with the "Kitchen" subsystem.
 *
 */

public interface FOHToKitchen {

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

}
