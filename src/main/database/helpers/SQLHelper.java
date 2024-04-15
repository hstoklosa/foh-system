package main.database.helpers;

public abstract class SQLHelper {
    private String sql;

    public abstract String generate(Object o, String direction);

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
