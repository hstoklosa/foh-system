package main.view;

import main.controller.FOHController;
import main.controller.OrderController;
import main.entity.Booking;
import main.entity.Table;
import main.enums.TableStatus;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


// This can be a GUI with the table layout, and you can click on each table to
// open up its
public class TableSelectorPage extends JPanel implements PropertyChangeListener {
    private MainView parentFrame;
    private FOHController mainControl;

    private JList<Booking> bookingsList;
    private DefaultListModel<Booking> bookingsModel;

    private JPanel floorPlanPanel;
    private JButton deallocateButton;
    private Map<JButton, Table> buttonTableMap;
    private Map<JButton, MouseListener> buttonMouseListenerMap;
    private Map<JButton, MouseMotionListener> buttonMotionListenerMap;

    public TableSelectorPage(MainView parentFrame, FOHController mainControl) {
        this.parentFrame = parentFrame;
        this.mainControl = mainControl;
        this.bookingsModel = new DefaultListModel<>();
        this.bookingsList = new JList<>(bookingsModel);
        this.buttonTableMap = new HashMap<>();
        this.buttonMouseListenerMap = new HashMap<>();
        this.buttonMotionListenerMap = new HashMap<>();

        setLayout(new BorderLayout());

        floorPlanPanel = new JPanel(null);
        floorPlanPanel.setPreferredSize(new Dimension(800, 600));
        floorPlanPanel.setBackground(Color.WHITE);
        initTable();

        JPanel footerPanel = new JPanel(new FlowLayout());
        deallocateButton = new JButton("Deallocate Table");
        deallocateButton.addActionListener(e -> System.out.println("XD"));
        footerPanel.add(deallocateButton);
        add(footerPanel, BorderLayout.SOUTH);

        JButton backButton = new JButton("Home");
        backButton.setBackground(new Color(208, 207, 207));
        backButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        backButton.addActionListener(e -> parentFrame.showCard("HomePage"));
        add(backButton, BorderLayout.NORTH);

        JLabel label = new JLabel("Tables Page", SwingConstants.CENTER);
        label.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
        add(floorPlanPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void initTable() {
        int cols = 5;
        int tableWidth = 78;
        int tableHeight = 73;
        int horizontalSpacing = 78;
        int verticalSpacing = 73;
        int offsetX = 0;
        int offsetY = 0;

        for (Table table : mainControl.getTables()) {
            JButton tableButton = new JButton(String.valueOf(table.getTableId()));

            tableButton.setBackground(Color.WHITE);

            int col = (table.getTableId() - 1) % cols;
            int row = (table.getTableId() - 1) / cols;
            int x = offsetX + col * (tableWidth + horizontalSpacing);
            int y = offsetY + row * (tableHeight + verticalSpacing);

            tableButton.setBounds(x, y, tableWidth, tableHeight);

            if (table.getStatus().equals(TableStatus.FREE)) {
                makeComponentDraggable(tableButton);
            }

            tableButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));

            MouseListener tableButtonListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {  // Check for double-click
                        popUp((JButton) e.getSource());
                    }
                }
            };

            buttonMouseListenerMap.put(tableButton, tableButtonListener);
            tableButton.addMouseListener(tableButtonListener);

            floorPlanPanel.add(tableButton);
            buttonTableMap.put(tableButton, table);  // Map the button to the Table object
        }

        add(new JScrollPane(floorPlanPanel), BorderLayout.CENTER);
    }

    private void popUp(JButton targetButton) {
        bookingsModel.clear();
        for (Booking bk : mainControl.getCurrentBookings()) {
            bookingsModel.addElement(bk);
        }

        JScrollPane scrollPane = new JScrollPane(bookingsList);
        scrollPane.setPreferredSize(new Dimension(500, 100));

        JPopupMenu popup = new JPopupMenu();
        popup.add(scrollPane);
        popup.setFocusable(false);

        bookingsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Table clickedTable = buttonTableMap.get(targetButton);
                    Booking selectedBooking = bookingsList.getSelectedValue();

                    selectedBooking.setTable(clickedTable);
                    targetButton.setBackground(Color.RED);

                    MouseListener ml = buttonMouseListenerMap.get(targetButton);
                    MouseMotionListener mml = buttonMotionListenerMap.get(targetButton);
                    targetButton.removeMouseListener(ml);
                    targetButton.removeMouseMotionListener(mml);

                    MouseListener tableButtonListener = new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {  // Check for double-click
                                OrderPage op = new OrderPage(parentFrame, new OrderController(clickedTable));
                                parentFrame.addCard(op, "OrdersPage");
                                parentFrame.showCard("OrdersPage");
                            }
                        }
                    };
                    targetButton.addMouseListener(tableButtonListener);
                    buttonMouseListenerMap.put(targetButton, tableButtonListener);

                    System.out.println(selectedBooking.getTable());
                    popup.setVisible(false);
                }
            }
        });

        int x = (parentFrame.getWidth() - popup.getPreferredSize().width) / 2;
        int y = (parentFrame.getHeight() - popup.getPreferredSize().height) / 2;

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

        MouseMotionListener motionListener = new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = comp.getX() + e.getX() - anchorPoint[0].x;
                int y = comp.getY() + e.getY() - anchorPoint[0].y;

                x = Math.round((float) x / 78) * 78;
                y = Math.round((float) y / 73) * 73;

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
        };

        comp.addMouseMotionListener(motionListener);
        buttonMotionListenerMap.put((JButton) comp, motionListener);
    }
}
