package main.controller;

import main.database.DBConnection;

public class PaymentController {
    private DBConnection db;

    public PaymentController(DBConnection db) {
        this.db = db;
    }

    public DBConnection getDb() {
        return db;
    }

    public void setDb(DBConnection db) {
        this.db = db;
    }
}
