package integration;

/**
 * 
 * IManagement.java
 *
 * Provided interface of the FOH subsystem. It lists the methods via which the 
 * functionality of the FOH subsystem is accessible by the Management subsystem.
 * 
 */

public interface IManagement {

    public void getSales();

    public void getBookings();

    public void getStaffOnHoliday();

    public void getTableBookings(Date date);

}
