CREATE TABLE Staff (
    staff_id    INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    role        VARCHAR(255) NOT NULL
)

CREATE TABLE Booking (
    booking_id  INTEGER AUTO_INCREMENT PRIMARY KEY,
    staff_id    INTEGER NULL DEFAULT NULL,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    phone_no    VARCHAR(20) NOT NULL,
    seats       INTEGER NOT NULL,
    date        DATETIME NOT NULL,
    FOREIGN KEY (staff_id)
        REFERENCES Staff(staff_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE Order (
    order_id    INTEGER AUTO_INCREMENT PRIMARY KEY,
    booking_id  INTEGER NOT NULL,
    FOREIGN KEY (staff_id)
        REFERENCES Staff(staff_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
)

CREATE TABLE OrderDish (
    order_id    INTEGER,
    dish_id     INTEGER,
    PRIMARY KEY (order_id, dish_id),
    FOREIGN KEY (order_id)
        REFERENCES Order(order_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
)

CREATE TABLE Payment (
    payment_id  INTEGER AUTO_INCREMENT PRIMARY KEY,
    booking_id  INTEGER NOT NULL,
    amount      DECIMAL(4, 2) NOT NULL,
    type        ENUM('card','cash') NOT NULL,
    FOREIGN KEY (booking_id)
        REFERENCES Booking(booking_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)