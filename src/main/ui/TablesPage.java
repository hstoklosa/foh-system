package main.ui;

import main.entity.Booking;
import main.entity.Table;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDateTime;


// This can be a GUI with the table layout, and you can click on each table to
// open up its
public class TablesPage extends JPanel implements PropertyChangeListener {
    private GUI parentFrame;

    private DefaultListModel<Table> tablesModel;

    private JList<Booking> bookingsList;
    private DefaultListModel<Booking> bookingsModel;


    public TablesPage(GUI parentFrame){
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());
        bookingsModel = new DefaultListModel<>();
        bookingsList = new JList<>(bookingsModel);

        JPanel floorPlanPanel = new JPanel(null);
        floorPlanPanel.setPreferredSize(new Dimension(800, 600));

        int cols = 5;  // Total number of columns
        int tableWidth = 78;
        int tableHeight = 73;
        int horizontalSpacing = 78; // Space between tables horizontally
        int verticalSpacing = 73;   // Space between tables vertically
        int offsetX = 0;           // Initial offset from left
        int offsetY = 0;           // Initial offset from top

        for (int i = 1; i <= 15; i++){
            JButton tableButton = new JButton("T" + i);
            int col = (i - 1) % cols;
            int row = (i - 1) / cols;
            int x = offsetX + col * (tableWidth + horizontalSpacing);
            int y = offsetY + row * (tableHeight + verticalSpacing);

            tableButton.setBounds(x, y, tableWidth, tableHeight);
            makeComponentDraggable(tableButton);

            tableButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
            tableButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3){
                        popUp();
                    }
                }
            });
            floorPlanPanel.add(tableButton);
        }

        add(new JScrollPane(floorPlanPanel), BorderLayout.CENTER);

        JButton backButton = new JButton("Home");
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Tables Page", SwingConstants.CENTER);
        label.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        add(floorPlanPanel, BorderLayout.CENTER);
    }

    public void addTable(Table table) {
        table.addPropertyChangeListener(this);
        tablesModel.addElement(table);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void popUp(){
//        JDialog popUp = new JDialog(parentFrame);
//        popUp.setSize(400, 300);
//        Booking booking = new Booking("Asad", "328910381", 3, LocalDateTime.now());
//        popUp.add(new JScrollPane(bookingsList), BorderLayout.CENTER);
//        popUp.setVisible(true);
//        bookingsModel.addElement(booking);
//        bookingsList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2 && !e.isConsumed()) {
//                    System.out.println(e.getClickCount());
//                    e.consume();
//                    Booking selectedBooking = bookingsList.getSelectedValue();
//                    popUp.setVisible(false);
//                }
//            }
//        });
        // Scroll pane for JList
        JScrollPane scrollPane = new JScrollPane(bookingsList);
        scrollPane.setPreferredSize(new Dimension(500, 100));

        // Create the popup
        JPopupMenu popup = new JPopupMenu();
        popup.add(scrollPane);
        popup.setFocusable(false);

        Booking booking = new Booking("Asad", "328910381", 3, LocalDateTime.now());
        bookingsModel.addElement(booking);

        // Mouse listener to handle double-clicks
        bookingsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    popup.setVisible(false);  // Close the popup
                }
            }
        });

        // Show the popup relative to the parent component
        int x = (parentFrame.getWidth() - popup.getPreferredSize().width) / 2;
        int y = (parentFrame.getHeight() - popup.getPreferredSize().height) / 2;

        // Show the popup at the calculated position relative to the frame
        popup.show(parentFrame, x, y);
    }

    private void makeComponentDraggable(Component comp){
        final Point[] anchorPoint = new Point[1];

        comp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                anchorPoint[0] = e.getPoint();
                comp.getParent().setComponentZOrder(comp, 0);
            }
        });

        comp.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = comp.getX() + e.getX() - anchorPoint[0].x;
                int y = comp.getY() + e.getY() - anchorPoint[0].y;

                x = Math.round((float)x / 78) * 78;
                y = Math.round((float)y / 73) * 73;

                x = Math.max(0, Math.min(x, comp.getParent().getWidth() - comp.getWidth()));
                y = Math.max(0, Math.min(y, comp.getParent().getHeight() - comp.getHeight()));

                int overlapHeight = 20;
                Rectangle boundingBox = new Rectangle(x, y + overlapHeight / 2, comp.getWidth(), comp.getHeight() - overlapHeight);

                for(Component other : comp.getParent().getComponents()){
                    if (other != comp && other.getBounds().intersects(boundingBox)){
                        return;
                    }
                }

                comp.setLocation(x, y);
                comp.getParent().repaint();
            }
        });
    }
}
