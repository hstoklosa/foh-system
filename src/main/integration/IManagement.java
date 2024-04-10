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
     * Retrieves the current menu of the restaurant.
     *
     * @return The menu of the restaurant.
     * The method finds and returns the most recent menu.
     * @return      Returns a main.integration.management.Menu object with dishes, pricing, allergen information.
     */
    Menu getMenu();

    /**
     * Retrieves the total number of bookings made in the past year.
     *
     * @return The total number of annual bookings.
     */
    int getAnnualBooking();

    /**
     * Retrieves the total number of covers served in the past year.
     *
     * @return The total number of annual covers.
     */
    int getAnnualCovers();

    /**
     * Retrieves the average number of bookings for a specific day of the week.
     *
     * @param day a day of the week (an enum).
     * @return The average number of bookings for the specified day.
     */
    int getDayAverageBooking(WeekDay day);

    /**
     * Retrieves the average number of covers for a specific day of the week.
     *
     * @param day a day of the week (an enum).
     * @return The average number of covers for the specified day, specified through the argument.
     * The method finds and returns the most recent menu.
     * @param day   Parameter coming from an Enum class.
     * @return int  Returns the main.integration.management.Menu object with dishes.
     */
    int getDayAverageCovers(WeekDay day);

    /**
     * Sends an email notification when the number of bookings exceeds a predefined limit.
     *
     * @return void
     */


}
