package main.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Table extends JButton { //click the tables to change their color

    private String tableId;
    private TableStatus status;

    public Table(String tableId){
        super(tableId);
        this.tableId = tableId;
        this.status = TableStatus.FREE;
        updateButtonAppearance();

        this.addActionListener(e -> toggleStatus());
    }

    private void toggleStatus(){
        if (this.status == TableStatus.FREE){
            this.status = TableStatus.TAKEN;
        }
        else{
            this.status = TableStatus.FREE;
        }
        updateButtonAppearance();
    }

    private void updateButtonAppearance(){
        switch (this.status) {
            case FREE:
                this.setBackground(Color.GREEN);
                break;
            case TAKEN:
                this.setBackground(Color.RED);
                break;
        }
    }

    enum TableStatus{
        FREE, TAKEN
    }
}
