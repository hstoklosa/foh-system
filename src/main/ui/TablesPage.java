package main.ui;

import main.entity.Table;
import main.enums.TableStatus;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// This can be a GUI with the table layout, and you can click on each table to
// open up its
public class TablesPage extends JPanel implements PropertyChangeListener {
    private GUI parentFrame;
    private DefaultListModel<Table> tablesModel;

    public TablesPage(GUI parentFrame){
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());

        JPanel floorPlanPanel = new JPanel();
        tablesModel = new DefaultListModel<>();

//        tablesModel.addListDataListener(new ListDataListener() {
//            public void intervalAdded(ListDataEvent e) {
//                for (int i = e.getIndex0(); i <= e.getIndex1(); i++) {
//                    Table currTable = tablesModel.getElementAt(i);
//                    TableButton tableButton = new TableButton(currTable.getTableId(), currTable.getStatus());
//                    tableButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
//                    floorPlanPanel.add(tableButton);
//                }
//
////                parentFrame.pack();
//            }
//
//            public void intervalRemoved(ListDataEvent e) {
//                for (int i = e.getIndex0(); i <= e.getIndex1(); i++) {
//                    floorPlanPanel.remove(e.getIndex0());
//                }
////                parentFrame.pack();
//            }
//
//            public void contentsChanged(ListDataEvent e) {
//                for (int i = e.getIndex0(); i <= e.getIndex1(); i++) {
//                    TableButton button = (TableButton) floorPlanPanel.getComponent(i);
////                    button.setText(floorPlanPanel.getElementAt(i));
//                }
//            }
//        });

        for (int i = 1; i <= 15; i++) {
            TableButton tableButton = new TableButton(String.valueOf(i), TableStatus.FREE);

            tableButton.setFont(new Font("Open Sans", Font.PLAIN, 16).deriveFont(16f));
            floorPlanPanel.add(tableButton);
        }

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
}
