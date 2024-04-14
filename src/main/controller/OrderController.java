package main.controller;

import main.entity.Table;

public class OrderController {
    private final Table table;

    public OrderController(Table table) {
        this.table = table;
    }

    public void createOrder() {
        if (table.getOrder() == null) {
//            table.setOrder();
        }
    }

    public Table getTable() {
        return table;
    }
}
