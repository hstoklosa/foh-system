package main.integration;

import main.entity.Menu;
import main.enums.WeekDay;


/**
 * 
 * IManagement.java
 * A required interface to connect to the "Management" subsystem.
 * 
 */

public interface IManagement {

    /**
     * The method finds and returns the most recent menu.
     * @return      Returns a main.integration.management.Menu object with dishes, pricing, allergen information.
     */
    Menu getMenu();

    /**
     * The method finds and returns the most recent menu.
     * @return int  Returns average number as an integer.
     */
    int getAnnualTableBookings();

    /**
     * The method finds and returns the most recent menu.
     * @return int  Returns average number as an integer.
     */
    int getAnnualCoverBookings();

    /**
     * The method finds and returns the most recent menu.
     * @param day   Parameter coming from an Enum class.
     * @return int  Returns average number as an integer.
     */
    int getAverageCoversBooked(WeekDay day);

    /**
     * The method finds and returns the most recent menu.
     * @param day   Parameter coming from an Enum class.
     * @return int  Returns the main.integration.management.Menu object with dishes.
     */
    int getAverageTablesBooked(WeekDay day);

}
