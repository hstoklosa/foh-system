package main.interfaces;

import java.sql.*;

public interface IDBConnection {

    Connection connect();

    void closeConnection(Connection conn);

    int write(String sql, Connection conn) throws SQLException;

    ResultSet read(String sql, Connection conn) throws SQLException;

}
