package main.interfaces.internal;


import main.entity.Menu;
import main.entity.Order;
import main.enums.CourseStatus;
import main.entity.Dish;

import java.util.ArrayList;

/**
 *
 * IKitchen.java
 * A required interface to connect to the "Kitchen" subsystem.
 *
 */

public interface IKitchen {

    /**
     * The method sends an order object to the kitchen subsystem. It has
     * all the information required for the kitchen to prepare the dishes.
     * @param order   Parameter coming from an Enum class.
     */
    void submitOrder(Order order);

    /**
     * @param order The order containing the course to check for readiness.
     * @return CourseStatus Enum indicating the current status of the course.
     */
    CourseStatus isCourseReady(Order order);

    /**
     * @param dishes The current menu to check for availability.
     * @return ArrayList<Recipe> A list of dishes that are unavailable.
     */
    ArrayList<Dish> sendUnavailableDish(Menu dishes);
}

