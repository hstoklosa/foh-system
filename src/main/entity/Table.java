package main.entity;

import main.enums.TableStatus;

public class Table {
    private int tableId;
    private TableStatus status;

    private Bill bill;
    private Staff waiter;
    private Order currOrder;
    private Booking currBooking;

    public Table(int tableId) {
        this.tableId = tableId;
        this.status = TableStatus.FREE;

        this.waiter = null;
        this.bill = null;
        this.currOrder = null;
        this.currBooking = null;
    }

    public void freeUpTable() {
        this.waiter = null;
        this.bill = null;
        this.currOrder = null;
        this.currBooking = null;
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

    public Order getOrder() {
        return currOrder;
    }

    public void setOrder(Order order) {
        this.currOrder = order;
    }

    public Order getCurrOrder() {
        return currOrder;
    }

    public void setCurrOrder(Order currOrder) {
        this.currOrder = currOrder;
    }

    public Booking getCurrBooking() {
        return currBooking;
    }

    public void setCurrBooking(Booking currBooking) {
        this.currBooking = currBooking;
    }
}
