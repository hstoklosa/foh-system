package main.database;

import main.interfaces.IDBConnection;
import main.interfaces.IDBTransaction;

import java.sql.*;
import java.util.Properties;

public class DBConnection implements IDBConnection, IDBTransaction {
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

    @Override
    public void startTransaction(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
    }

    @Override
    public boolean commit(Connection conn) {
        try {
            conn.commit();
            conn.setAutoCommit(true);
            System.out.println("Transaction committed successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("TRANSACTION_COMMIT_ERROR: Failed to commit transaction, " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean rollback(Connection conn) {
        try {
            conn.rollback();
            System.out.println("Transaction rolled back successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("TRANSACTION_ROLLBACK_ERROR: Failed to rollback transaction: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean setIsolationLevel(String level, Connection conn) throws SQLException {
        int isolationLevel = switch (level.toUpperCase()) {
            case "READ_UNCOMMITTED" -> Connection.TRANSACTION_READ_UNCOMMITTED;
            case "READ_COMMITTED" -> Connection.TRANSACTION_READ_COMMITTED;
            case "REPEATABLE_READ" -> Connection.TRANSACTION_REPEATABLE_READ;
            case "SERIALIZABLE" -> Connection.TRANSACTION_SERIALIZABLE;
            default -> throw new IllegalArgumentException("Invalid isolation level: " + level);
        };

        conn.setTransactionIsolation(isolationLevel);
        return true;
    }
}
