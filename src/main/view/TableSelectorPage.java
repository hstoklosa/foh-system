package main.view;

import main.controller.FOHController;
import main.controller.OrderController;
import main.entity.Booking;
import main.entity.Staff;
import main.entity.Table;
import main.enums.TableStatus;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TableSelectorPage extends JPanel implements PropertyChangeListener {
    private MainView parentFrame;
    private FOHController mainControl;

    private JList<Booking> bookingsList;
    private DefaultListModel<Booking> bookingsModel;

    private JPanel floorPlanPanel;
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
    public void propertyChange(PropertyChangeEvent evt) {}

    public void initTable() {
        int cols = 5;                   // Total number of columns
        int tableWidth = 78;
        int tableHeight = 73;
        int horizontalSpacing = 78;     // Space between tables horizontally
        int verticalSpacing = 73;       // Space between tables vertically
        int offsetX = 0;                // Initial offset from left
        int offsetY = 0;                // Initial offset from top

        for (Table table : mainControl.getTables()) {
            JButton tableButton = new JButton(String.valueOf(table.getTableId()));

            int col = (table.getTableId() - 1) % cols;
            int row = (table.getTableId() - 1) / cols;
            int x = offsetX + col * (tableWidth + horizontalSpacing);
            int y = offsetY + row * (tableHeight + verticalSpacing);

            tableButton.setBounds(x, y, tableWidth, tableHeight);
            tableButton.setBackground(Color.WHITE);

            if (table.getStatus().equals(TableStatus.FREE)) {
                makeComponentDraggable(tableButton);
            }

            tableButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));

            MouseListener tableButtonListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {  // Check for double-click
                        openAllocationPopUp((JButton) e.getSource());
                    }
                }
            };

            buttonMouseListenerMap.put(tableButton, tableButtonListener);
            tableButton.addMouseListener(tableButtonListener);

            floorPlanPanel.add(tableButton);
            buttonTableMap.put(tableButton, table);  // Map the button to the Table object
        }

        JScrollPane tableLayout = new JScrollPane(floorPlanPanel);
        add(tableLayout, BorderLayout.CENTER);
    }

    private void openAllocationPopUp(JButton targetButton) {
        bookingsModel.clear();

        for (Booking bk : mainControl.getCurrentBookings()) {
            bookingsModel.addElement(bk);
        }

        List<Staff> staff = mainControl.fetchStaff();
        DefaultListModel<Staff> staffModel = new DefaultListModel<>();

        JList<Staff> staffList = new JList<>(staffModel);
        for (Staff s : staff) {
            staffModel.addElement(s);
        }

        JScrollPane bookingScrollPane = new JScrollPane(bookingsList);
        bookingScrollPane.setPreferredSize(new Dimension(200, 100));

        JScrollPane staffScrollPane = new JScrollPane(staffList);
        staffScrollPane.setPreferredSize(new Dimension(200, 100));

        JDialog jd = new JDialog(parentFrame, "Table allocation", Dialog.ModalityType.APPLICATION_MODAL);
        jd.setSize(new Dimension(550, 250));
        jd.setLocationRelativeTo(parentFrame); // ensure dialog is centered on the parent frame

        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS)); // set the layout to vertical
        jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel bookingPanel = new JPanel(new BorderLayout());
        bookingPanel.add(new JLabel("Select booking: "), BorderLayout.NORTH); // aligns label to the left
        bookingPanel.add(bookingScrollPane, BorderLayout.CENTER); // ensures the scroll pane fills the rest
        jp.add(bookingPanel);

        JPanel waiterPanel = new JPanel(new BorderLayout());
        waiterPanel.add(new JLabel("Assign waiter: "), BorderLayout.NORTH); // aligns label to the left
        waiterPanel.add(staffScrollPane, BorderLayout.CENTER); // ensures the scroll pane fills the rest
        jp.add(waiterPanel);

        JButton allocateTableBtn = new JButton("Allocate");
        allocateTableBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        allocateTableBtn.addActionListener(e -> {
            Table clickedTable = buttonTableMap.get(targetButton);

            Booking selectedBooking = bookingsList.getSelectedValue();
            Staff selectedWaiter = staffList.getSelectedValue();

            if (selectedBooking == null || selectedWaiter == null) {
                JOptionPane.showMessageDialog(this, "You must assign both table and waiter.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

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
                        OrderController oc = new OrderController(mainControl.getDb(), clickedTable);
                        OrderPage op = new OrderPage(parentFrame, oc);

                        parentFrame.addCard(op, "OrdersPage");
                        parentFrame.showCard("OrdersPage");
                    }
                }
            };

            targetButton.addMouseListener(tableButtonListener);
            buttonMouseListenerMap.put(targetButton, tableButtonListener);
            jd.setVisible(false);
        });
        jp.add(allocateTableBtn);

        jd.add(jp);
        jd.setVisible(true);
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
