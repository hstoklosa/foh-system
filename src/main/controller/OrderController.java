package main.controller;

import main.database.DBConnection;
import main.entity.Course;
import main.entity.Order;
import main.entity.Table;
import main.enums.CourseType;

public class OrderController {
    private DBConnection db;
    private final Table table;

    public OrderController(DBConnection db, Table table) {
        this.db = db;
        this.table = table;

        if (this.table.getOrder() == null) {
            this.table.setOrder(new Order());
        }
    }

    public void saveOrder(CourseType courseType) {
//        Course c = table.getOrder().

        for (Course c : table.getOrder().getCourses()) {
            if (c.getType().equals(courseType)) {
//                try {
//                    // Create connection and initialise PreparedStatement
//                    Connection conn = db.connect();
//                    PreparedStatement psta = conn.prepareStatement("INSERT INTO Order (booking_id, total, date) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//
//                    // insert params into PreparedStatement
//                    psta.setString(1, firstName);
//                    psta.setString(2, lastName);
//                    psta.setString(3, phoneNo);
//                    psta.setObject(4, LocalDateTime.now());
//                    psta.setInt(5, seats);
//                    int affectedRows = psta.executeUpdate();
//
//                    if (affectedRows == 0) {
//                        throw new SQLException("Creating user failed, no rows affected.");
//                    }
//
//                    int id;
//                    try (ResultSet generatedKeys = psta.getGeneratedKeys()) {
//                        if (generatedKeys.next()) {
//                            id = (int) generatedKeys.getLong(1);
//                        } else {
//                            throw new SQLException("Creating user failed, no ID obtained.");
//                        }
//                    }
//
//                    bookings.add(new Booking(id, firstName, lastName, phoneNo, seats, LocalDateTime.now()));
//                    conn.close();
//                } catch (SQLException e) {
//                    System.out.println("An error occurred when inserting a booking: " + e.getMessage());
//                }
            }
        }
    }

    public Table getTable() {
        return table;
    }

    public DBConnection getDb() {
        return db;
    }

    public void setDb(DBConnection db) {
        this.db = db;
    }
}
