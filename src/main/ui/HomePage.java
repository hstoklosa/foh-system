package main.ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomePage extends JFrame{

    private JLabel dateTimeLabel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout);

    public HomePage(){
        setTitle("Front of House System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(createMenuBar());
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel contentPanel = createContentPanel();
        TablesPage tablesPage = new TablesPage(this);
        BookingsPage bookingsPage = new BookingsPage(this);
        OrdersPage ordersPage = new OrdersPage(this);
        PaymentPage paymentPage = new PaymentPage(this);

        cardsPanel.add(contentPanel, "ContentPanel");
        cardsPanel.add(tablesPage, "TablesPage");
        cardsPanel.add(bookingsPage, "BookingsPage");
        cardsPanel.add(ordersPage, "OrdersPage");
        cardsPanel.add(paymentPage, "PaymentPage");

        getContentPane().add(cardsPanel, BorderLayout.CENTER);

        getContentPane().add(createNavigationBar(), BorderLayout.SOUTH);
    }

    public void showCard(String cardName){
        cardLayout.show(cardsPanel, cardName);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenuItem fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        return menuBar;
    }

    protected JPanel createHeaderPanel(){
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(800, 50));

        JLabel nameLabel = new JLabel("Lancaster's");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        updateDateTime();

        dateTimeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        headerPanel.add(nameLabel, BorderLayout.WEST);
        headerPanel.add(dateTimeLabel, BorderLayout.EAST);

        new Timer(100, e -> updateDateTime()).start();

        return headerPanel;
    }

    private void updateDateTime(){
        String format = "EEEE, MMMM dd, yyyy HH:mm:ss";
        SimpleDateFormat date = new SimpleDateFormat(format);

        dateTimeLabel.setText(date.format(Calendar.getInstance().getTime()));
    }

    protected JPanel createContentPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Front of House System", SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        return panel;
    }

    protected JPanel createNavigationBar(){
        JPanel navBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton tablesButton = new JButton("Tables");
        JButton bookingsButton = new JButton("Bookings");
        JButton ordersButton = new JButton("Orders");
        JButton paymentButton = new JButton("Payment");

        tablesButton.addActionListener(e -> showCard("TablesPage"));
        bookingsButton.addActionListener(e -> showCard("BookingsPage"));
        ordersButton.addActionListener(e -> showCard("OrdersPage"));
        paymentButton.addActionListener(e -> showCard("PaymentPage"));

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> showCard("ContentPanel"));

        navBarPanel.add(tablesButton);
        navBarPanel.add(bookingsButton);
        navBarPanel.add(ordersButton);
        navBarPanel.add(paymentButton);

        return navBarPanel;
    }

}
