package main.entity;

import main.entity.payment.BasePayment;

import java.util.LinkedList;
import java.util.List;

public class Bill {
    private float total;
    private List<BasePayment> payments;

    public Bill(float total) {
        this.total = total;
        this.payments = new LinkedList<>();
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<BasePayment> getPayments() {
        return payments;
    }

    public void setPayments(List<BasePayment> payments) {
        this.payments = payments;
    }
}
