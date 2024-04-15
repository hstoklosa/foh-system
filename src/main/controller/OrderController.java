package main.controller;

import main.entity.Table;

public class OrderController {
    private final Table table;

    public OrderController(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
}
