package main.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBTransaction {

    void startTransaction(Connection conn) throws SQLException;

    boolean commit(Connection conn);

    boolean rollback(Connection conn);

    boolean setIsolationLevel(String level, Connection conn) throws SQLException;

}
