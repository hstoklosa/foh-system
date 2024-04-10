package main.integration;

import main.entity.Order;

/**
 *
 * IKitchen.java
 * A required interface to connect to the "Kitchen" subsystem.
 *
 */

public interface IKitchen {

    /**
     * The method finds and returns the most recent menu.
     * @param dishId    Parameter coming from an Enum class.
     * @return boolean  Returns true if dish is available and false otherwise.
     */
    boolean checkDishAvailability(int dishId);


    /**
     * The method finds and returns the most recent menu.
     * @param order   The order object that has all necessary information.
     */
    void submitOrder(Order order);

}

