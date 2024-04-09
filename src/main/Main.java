package main;

import main.database.DBConnection;

public class Main {

    public static void main(String[] args) {
        DBConnection conn = new DBConnection();
        conn.getData();
    }
}
