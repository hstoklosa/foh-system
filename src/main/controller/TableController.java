package main.controller;

import main.entity.Table;

public class TableController {
    private final Table table;

    public TableController(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
}
