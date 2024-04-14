package main.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.enums.TableStatus;

public class Table {

    private int tableId;
    private TableStatus status;
    private Bill bill;
    private Staff waiter;

    public Table(int tableId) {
        this.tableId = tableId;
        this.status = TableStatus.FREE;
        this.waiter = null;
        this.bill = null;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public Staff getWaiter() {
        return waiter;
    }

    public void setWaiter(Staff waiter) {
        this.waiter = waiter;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
