package main.ui;

import java.time.LocalDateTime;

public class Booking {
    private String name;
    private String phoneNumber;
    private int tableSize;
    private LocalDateTime bookingTime;

    public Booking(String name, String phoneNumber, int tableSize, LocalDateTime bookingTime) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.tableSize = tableSize;
        this.bookingTime = bookingTime;
    }
}
