package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Table extends JButton { //click the tables to change their color

    private String tableId;
    private TableStatus status;
    private int prevX, prevY;
    private static final int GRID_SIZE = 50;

    public Table(String tableId){
        super(tableId);
        this.tableId = tableId;
        this.status = TableStatus.FREE;
        updateButtonAppearance();

        this.addActionListener(e -> toggleStatus());

        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                prevX = e.getX();
                prevY = e.getY();
            }

            public void mouseDragged(MouseEvent e){
                int deltaX = e.getX() - prevX;
                int deltaY = e.getY() - prevY;

                Point p = getLocation();
                p.x += deltaX;
                p.y += deltaY;
                setLocation(p);
            }

            public void mouseReleased(MouseEvent e){
                snapToGrid();
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
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

    private void snapToGrid(){
        Point p = getLocation();

        int gridX = Math.round(p.x / (float) GRID_SIZE) *GRID_SIZE;
        int gridY = Math.round(p.y / (float) GRID_SIZE) *GRID_SIZE;

        setLocation(gridX, gridY);
    }

    enum TableStatus{
        FREE, TAKEN
    }
}
