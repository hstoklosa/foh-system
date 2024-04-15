package main.controller;

import main.database.DBConnection;
import main.entity.*;
import main.enums.WeekDay;
import main.interfaces.internal.IFrontHouse;
import main.ui.GUI;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class FOHController implements IFrontHouse {
    private final int DEFAULT_TABLES_AMOUNT = 15;
    private final DBConnection db;

    private Menu currentMenu;
    private List<Booking> bookings;
    private List<Table> tables;

    public FOHController() {
        this.db = new DBConnection(
                "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t04",
                "in2033t04_a", "FREK6PsYUCw"
        );

        this.currentMenu = null;
        this.bookings = initBookings();
        this.tables = initTables();
    }

    private Vector<Booking> initBookings() {
        Vector<Booking> tempBookings = new Vector<>();
        Connection conn = db.connect();

        try {
            db.startTransaction(conn);

            ResultSet rs1 = db.read("SELECT * FROM Booking", conn);
            while (rs1.next()) {
                int id = rs1.getInt("booking_id");
                int staff_id = rs1.getInt("staff_id");
                String firstName = rs1.getString("first_name");
                String lastName = rs1.getString("last_name");
                String phoneNo = rs1.getString("phone_no");
                int seats = rs1.getInt("seats");
                LocalDateTime date = (LocalDateTime) rs1.getObject("date");

                tempBookings.add(new Booking(id, firstName, lastName, phoneNo, seats, date));
            }

//            ResultSet rs2 = db.read("SELECT * FROM Staff", conn);
//            while (rs2.next()) {
//                int id = rs2.getInt("staff_id");
//                String firstName = rs2.getString("first_name");
//                String lastName = rs2.getString("last_name");
//
//                System.out.println(id + " " + firstName + " " + lastName);
//            }

            db.commit(conn);
            db.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tempBookings;
    }

    private Vector<Table> initTables() {
        Vector<Table> tempTables = new Vector<>();

        for (int i = 1; i <= DEFAULT_TABLES_AMOUNT; i++) {
            tempTables.add(new Table(i));
        }

        return tempTables;
    }

    public void addBooking(String firstName, String lastName, String phoneNo, int seats) {
        try {
            // Create connection and initialise PreparedStatement
            Connection conn = db.connect();
            PreparedStatement psta = conn.prepareStatement("INSERT INTO Booking (first_name, last_name, phone_no, date, seats) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            // insert params into PreparedStatement
            psta.setString(1, firstName);
            psta.setString(2, lastName);
            psta.setString(3, phoneNo);
            psta.setObject(4, LocalDateTime.now());
            psta.setInt(5, seats);
            int affectedRows = psta.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            int id;
            try (ResultSet generatedKeys = psta.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            bookings.add(new Booking(id, firstName, lastName, phoneNo, seats, LocalDateTime.now()));
            conn.close();
        } catch (SQLException e) {
            System.out.println("An error occurred when inserting a booking: " + e.getMessage());
        }
    }

    public void updateBooking(Booking bk) {
        // Create connection and initialise PreparedStatement
        try {
            Connection conn = db.connect();
            PreparedStatement psta = conn.prepareStatement("UPDATE Booking SET first_name = ?, last_name = ?, phone_no = ?, seats = ?, date = ? WHERE booking_id = ?");

            psta.setString(1, bk.getFirstName());
            psta.setString(2, bk.getLastName());
            psta.setString(3, bk.getPhoneNumber());
            psta.setInt(4, bk.getTableSize());
            psta.setObject(5, bk.getBookingTime());
            psta.setInt(6, bk.getId());
            int affectedRows = psta.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("An error occurred when updating a booking: " + e.getMessage());
        }
    }

    public void removeBooking(Booking bk) {
        try {
            Connection conn = db.connect();
            PreparedStatement psta = conn.prepareStatement("DELETE FROM Booking WHERE booking_id = ?");

            psta.setInt(1, bk.getId());

            int affectedRows = psta.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            bookings.remove(bk);
            conn.close();
        } catch (SQLException e) {
            System.out.println("An error occurred when removing a booking: " + e.getMessage());
        }
    }

    public List<Booking> getTodaysBookings() {
        return bookings.stream()
                .filter(booking -> booking.getBookingTime().toLocalDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
    }

    @Override
    public void notifyNextCourse(int orderId) {}

    @Override
    public HashMap<Dish, Integer> getSales(WeekDay day) {
        return null;
    }

    @Override
    public HashMap<Integer, Integer> getBookings() {
        return null;
    }

    public List<Booking> getCurrentBookings() {
        return bookings;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
