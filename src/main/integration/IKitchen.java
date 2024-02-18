package integration;

/**
 *
 * IKitchen.java
 *
 * Provided interface of the FOH subsystem. It lists the methods via which the
 * functionality of the FOH subsystem is accessible by the Kitchen subsystem.
 *
 */

public interface IKitchen {

    public void getOrderDetails();
	    
    public void updateOrderStatus(); 

}
