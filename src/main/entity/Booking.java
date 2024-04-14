package main.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Name: %s, Phone: %s, Tables for %d, Time: %s",
                name,
                phoneNumber,
                tableSize,
                bookingTime.format(formatter));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }


    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getTableSize() { return tableSize; }
    public LocalDateTime getBookingTime() { return bookingTime; }
}
