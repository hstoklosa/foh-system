package main.integration;


import main.entity.Menu;
import main.entity.Order;
import main.enums.CourseStatus;
import main.entity.Recipe;

import java.util.ArrayList;

/**
 *
 * IKitchen.java
 * A required interface to connect to the "Kitchen" subsystem.
 *
 */

public interface IKitchen {

    /**
     * @param order The order containing the course to check for readiness.
     * @return CourseStatus Enum indicating the current status of the course.
     */
    CourseStatus isCourseReady(Order order);

    /**
     * @param dishes The current menu to check for availability.
     * @return ArrayList<Recipe> A list of dishes that are unavailable.
     */
    ArrayList<Recipe> sendUnavailableDish(Menu dishes);

}

