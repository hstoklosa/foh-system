package main.database;

import main.interfaces.IDBConnection;

import java.sql.*;
import java.util.Properties;

public class DBConnection implements IDBConnection {
    private final String CONNECTION_URL = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t04";

    private final String DB_USERNAME;
    private final String DB_PASSWORD;

    public DBConnection(String username, String password) {
        this.DB_USERNAME = username;
        this.DB_PASSWORD = password;
    }

    @Override
    public Connection connect(String sql) {
        Connection conn = null;

        Properties connectionProps = new Properties();
        connectionProps.put("user", this.DB_USERNAME);
        connectionProps.put("password", this.DB_PASSWORD);

        try {
            conn = DriverManager.getConnection(CONNECTION_URL, connectionProps);
            System.out.println("Database connection has been established.");
        } catch (SQLException sqle) {
            System.err.println("DB_CONNECT_ERROR: " + sqle.getMessage());
        }

        return conn;
    }

    @Override
    public int write(String sql, Connection conn) {
        int rowsAffected = 0;

        try (Statement stmt = conn.createStatement()) {
            rowsAffected = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("DB_WRITE_ERROR: " + e.getMessage());
        }

        return rowsAffected;
    }

    @Override
    public ResultSet read(String sql, Connection conn) {
        ResultSet rs = null;

        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);
        } catch (SQLException sqle) {
            System.err.println("DB_READ_ERROR: " + sqle.getMessage());
        }

        return rs;
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqle) {
            System.err.println("DB_CLOSE_ERROR: " + sqle.getMessage());
        }
    }
}
