package main.interfaces;

import java.sql.*;

public interface IDBConnection {

    Connection connect(String sql);

    void closeConnection(Connection conn);

    int write(String sql, Connection conn);

    ResultSet read(String sql, Connection conn);

}
