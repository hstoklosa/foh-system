package main.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import main.entity.Table;

public class Booking {
    private final int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int tableSize;
    private LocalDateTime bookingTime;
    private Table table;

    public Booking(int id, String firstName, String lastName, String phoneNumber, int tableSize, LocalDateTime bookingTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.tableSize = tableSize;
        this.bookingTime = bookingTime;
        this.table = null;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Name: %s, Phone: %s, Tables for %d, Time: %s",
                firstName + " " + lastName,
                phoneNumber,
                tableSize,
                bookingTime.format(formatter));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public int getTableSize() { return tableSize; }

    public LocalDateTime getBookingTime() { return bookingTime; }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getId() {
        return id;
    }
}
